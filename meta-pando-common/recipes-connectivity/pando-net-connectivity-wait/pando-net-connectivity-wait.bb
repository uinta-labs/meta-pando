DESCRIPTION = "balena full network connectivity checker"
SECTION = "console/utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${PANDO_COREBASE}/COPYING.Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " \
    file://pando-net-connectivity-wait \
    file://pando-net-connectivity-wait.service \
    file://pando-net-connectivity-wait.target \
    "
S = "${WORKDIR}"

inherit allarch systemd

PACKAGES = "${PN}"

SYSTEMD_SERVICE:${PN} = " \
	pando-net-connectivity-wait.service \
	pando-net-connectivity-wait.target \
	"
RDEPENDS:${PN} = "bash"

do_install() {
    install -d ${D}${bindir}
    install -m 0775 ${WORKDIR}/pando-net-connectivity-wait ${D}${bindir}/pando-net-connectivity-wait

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -c -m 0644 ${WORKDIR}/pando-net-connectivity-wait.service ${D}${systemd_unitdir}/system
        install -c -m 0644 ${WORKDIR}/pando-net-connectivity-wait.target ${D}${systemd_unitdir}/system
        sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
        -e 's,@BINDIR@,${bindir},g' \
            ${D}${systemd_unitdir}/system/pando-net-connectivity-wait.service
    fi
}
