DESCRIPTION = "Pando custom init file"
SECTION = "console/utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${PANDO_COREBASE}/COPYING.Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PR = "r6"

SRC_URI = " \
    file://pando-init \
    file://pando-init.service \
    "
S = "${WORKDIR}"

inherit allarch systemd

SYSTEMD_SERVICE:${PN} = "pando-init.service"

RDEPENDS:${PN} = " \
    bash \
    pando-init-board \
    iw \
    "

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/pando-init ${D}${bindir}

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -c -m 0644 ${WORKDIR}/pando-init.service ${D}${systemd_unitdir}/system
        sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
            -e 's,@BASE_SBINDIR@,${base_sbindir},g' \
            -e 's,@SBINDIR@,${sbindir},g' \
            -e 's,@BINDIR@,${bindir},g' \
            -e 's,@SYS_CONFDIR@,${sysconfdir},g' \
            ${D}${systemd_unitdir}/system/pando-init.service
    fi
}
