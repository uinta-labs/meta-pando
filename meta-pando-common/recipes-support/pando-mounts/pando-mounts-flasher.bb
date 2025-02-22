SUMMARY = "Pando flasher systemd mount services"

include pando-mounts.inc

SRC_URI += " \
	file://mnt-boot.mount \
	file://mnt-boottmp.mount \
	file://pando-boot.service \
	file://temp-conf.service \
	"

SYSTEMD_SERVICE:${PN} += " \
	mnt-boot.mount \
	mnt-boottmp.mount \
	pando-boot.service \
	temp-conf.service \
	"

FILES:${PN} += " \
	/mnt/boot \
	/mnt/boottmp \
	"

BINDMOUNTS += " \
	/etc/hostname \
	/etc/NetworkManager/system-connections \
	/etc/NetworkManager/dispatcher.d \
	/home/root/.rnd \
	"

do_install:prepend () {
	# These are mountpoints for various mount services/units
	install -d ${D}/mnt/boot
	install -d ${D}/mnt/boottmp

	install -d ${D}${sysconfdir}/systemd/system/
	for service in ${SYSTEMD_SERVICE:pando-mounts-flasher}; do
		# Use sysconfdir so it won't conflict with pando-mounts units
		# This was fixed in later yocto versions were sysroot is per recipe
		install -m 0644 $service ${D}${sysconfdir}/systemd/system/
	done
}
