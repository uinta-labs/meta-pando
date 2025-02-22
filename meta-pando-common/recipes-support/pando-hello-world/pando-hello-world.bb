DESCRIPTION = "Pando Hello World"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${PANDO_COREBASE}/COPYING.Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " \
    file://pando-hello-world.service \
    "
S = "${WORKDIR}"

inherit allarch systemd

SYSTEMD_SERVICE:${PN} = "pando-hello-world.service"

RDEPENDS:${PN} = " \
    coreutils \
    "

do_install() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -c -m 0644 ${WORKDIR}/pando-hello-world.service ${D}${systemd_unitdir}/system
        
    fi
}
