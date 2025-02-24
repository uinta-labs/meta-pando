DESCRIPTION = "Pando hostname configuration"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${PANDO_COREBASE}/COPYING.Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " \
    file://pando-hostname \
    file://pando-hostname.service \
    "
S = "${WORKDIR}"

inherit allarch systemd pando-configurable

SYSTEMD_SERVICE:${PN} = "pando-hostname.service"

RDEPENDS:${PN} = " \
    coreutils \
    jq \
    pando-unique-key \
    "

do_install() {
    install -d ${D}${bindir}
    install -m 0775 ${WORKDIR}/pando-hostname ${D}${bindir}

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -c -m 0644 ${WORKDIR}/pando-hostname.service ${D}${systemd_unitdir}/system
        sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
            -e 's,@SBINDIR@,${sbindir},g' \
            -e 's,@BINDIR@,${bindir},g' \
            ${D}${systemd_unitdir}/system/*.service
        
    fi
}
