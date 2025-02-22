SUMMARY = "Pando systemd mount services"

include pando-mounts.inc

RDEPENDS:${PN} += "os-helpers-fs"

SRC_URI += " \
	file://pando-nonencboot.service \
	file://pando-boot.service \
	file://pando-data.service \
	file://pando-state.service \
	file://mnt-sysroot-active.service \
	file://mnt-sysroot-inactive.automount \
	file://mnt-sysroot-inactive.mount \
	file://pando-partition-mounter \
	file://etc-fake-hwclock.mount \
	"

SYSTEMD_SERVICE:${PN} += " \
	pando-boot.service \
	pando-data.service \
	pando-state.service \
	mnt-sysroot-active.service \
	mnt-sysroot-inactive.automount \
	mnt-sysroot-inactive.mount \
	"

SYSTEMD_SERVICE:${PN} += "${@oe.utils.conditional('SIGN_API','','','${PANDO_NONENC_BOOT_LABEL}.service',d)}"

FILES:${PN} += " \
	${PANDO_BOOT_MOUNT} \
	${@oe.utils.conditional('SIGN_API','','','${PANDO_NONENC_BOOT_MOUNT}',d)} \
	/mnt/data \
	/mnt/state \
	/mnt/sysroot/active \
	/mnt/sysroot/inactive \
	"


### /etc/pando-supervisor

BINDMOUNTS += " \
	/etc/docker \
	/home/root/.docker \
	/var/log/journal \
	/var/lib/systemd \
	/var/lib/chrony \
	"

do_install:prepend () {
	# These are mountpoints for various mount services/units
	install -d ${D}/etc/docker
	ln -sf docker ${D}/etc/balena
	ln -sf docker ${D}/etc/balena-engine
	install -d ${D}${PANDO_BOOT_MOUNT}
	install -d ${D}/mnt/data
	install -d ${D}/mnt/state
	install -d ${D}/mnt/sysroot/active
	install -d ${D}/mnt/sysroot/inactive

	install -d ${D}${bindir}
	install -m 755 ${WORKDIR}/pando-partition-mounter ${D}${bindir}

	install -d ${D}${systemd_unitdir}/system
	if [ "x${SIGN_API}" != "x" ]; then
		install -d "${D}${PANDO_NONENC_BOOT_MOUNT}"
		install -m 0644 pando-nonencboot.service ${D}${systemd_unitdir}/system/${PANDO_NONENC_BOOT_LABEL}.service
		sed -i -e "s/@@PANDO_NONENC_BOOT_LABEL@@/${PANDO_NONENC_BOOT_LABEL}/g" "${D}${systemd_unitdir}/system/${PANDO_NONENC_BOOT_LABEL}.service"
		if ${@bb.utils.contains('MACHINE_FEATURES','efi','true','false',d)}; then
			sed -i '/^\[Unit\]/a ConditionPathIsSymbolicLink=/mnt/boot/EFI' "${D}${systemd_unitdir}/system/${PANDO_NONENC_BOOT_LABEL}.service"
		fi
	fi
	for service in ${SYSTEMD_SERVICE:pando-mounts}; do
		if [ -f $service ]; then
			install -m 0644 $service ${D}${systemd_unitdir}/system/
		fi
	done
	install -m 0644 ${WORKDIR}/etc-fake-hwclock.mount ${D}${systemd_unitdir}/system/etc-fake\\x2dhwclock.mount
}
