LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${PANDO_COREBASE}/COPYING.Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI += "file://bind-path.service.in"

S = "${WORKDIR}"

inherit systemd allarch

SYSTEMD_SERVICE:${PN} += "${@bindmounts_systemd_services(d)}"

FILES:${PN} += " \
	${sysconfdir} \
	${systemd_unitdir} \
	/home/root/.ssh \
	"


###	/usr/share/ca-certificates/balena

BINDMOUNTS += " \
	${sysconfdir}/ssh/hostkeys \
	/etc/hostname \
	/etc/NetworkManager/conf.d \
	/etc/NetworkManager/system-connections \
	/etc/NetworkManager/dispatcher.d \
	/etc/udev/rules.d \
	/etc/ssl/certs \
	/home/root/.rnd \
	/var/lib/bluetooth \
	/var/lib/NetworkManager \
	/home/root/.ssh \
	/usr/share/ca-certificates/balena \
	"

PACKAGES = "${PN}"

RDEPENDS:${PN} += "util-linux bindmount"

def bindmounts_systemd_services(d):
    services = []
    for bindmount in d.getVar("BINDMOUNTS", True).split():
        services.append("bind-%s.service" % bindmount[1:].replace("/", "-"))
    return " ".join(services)

do_compile () {
	for bindmount in ${BINDMOUNTS}; do
		servicefile="bind$(echo "$bindmount" | tr / -).service"
		sed -e "s#@target@#$bindmount#g" bind-path.service.in > "$servicefile"

		# Service specific changes
		if [ "$bindmount" = "/var/lib/systemd" ]; then
			# Systemd services need to write to /var/lib/systemd so make sure
			# that is mounted.
			sed -i -e "/^Before=/s/\$/ systemd-random-seed.service systemd-rfkill.service/" \
			-e "/^WantedBy=/s/\$/ systemd-random-seed.service systemd-rfkill.service/" \
			"$servicefile"
		elif [ "$bindmount" = "/var/log/journal" ]; then
			# This bind mount is only invoked manually in persistent logs
			# service. No need to run it with default target.
			sed -i -e "/^WantedBy=/ d" "$servicefile"
			# Mount persistent logs on data partition
			sed -i -e 's/pando-state.service/pando-data.service/g' "$servicefile"
			sed -i -e 's#/mnt/state#/mnt/data#g' "$servicefile"
		elif [ "$bindmount" = "/etc/NetworkManager/conf.d" ]; then
			# This bind mount needs to be running before the NetworkManager service starts for os-networkmanager
			sed -i -e "/^Before=/s/\$/ NetworkManager.service/" "$servicefile"
		elif [ "$bindmount" = "/etc/udev/rules.d" ]; then
			# This bind mount needs to be running before the udev service starts for os-udevrules
			sed -i -e "/^Before=/s/\$/ systemd-udevd.service/" "$servicefile"
		### elif [ "$bindmount" = "/etc/ssl/certs" ]; then
		### 	# This bind should only be started when a custom CA file exists
		### 	sed -i -e "/^Conflicts=/s,\$,\nConditionDirectoryNotEmpty=/usr/share/ca-certificates/balena," "$servicefile"
		### 	# The custom CA file itself is in a bind-mounted directory, make
		### 	# sure that is mounted as well
		### 	sed -i -e "/^Requires=/s/\$/ bind-usr-share-ca-certificates-balena.service/" "$servicefile"
		### 	sed -i -e "/^After=/s/\$/ bind-usr-share-ca-certificates-balena.service/" "$servicefile"
		fi
	done
}
do_compile[dirs] = "${WORKDIR}"

do_install () {
	install -d "${D}${sysconfdir}"
	touch "${D}/${sysconfdir}/hostname"
	install -d "${D}/home/root/.ssh"
	install -d "${D}/${sysconfdir}/ssh/hostkeys"
}
