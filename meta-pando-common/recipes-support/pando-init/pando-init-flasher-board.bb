DESCRIPTION = "Board custom INIT file"
SECTION = "console/utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${PANDO_COREBASE}/COPYING.Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://pando-init-flasher-board"
S = "${WORKDIR}"

inherit allarch

RDEPENDS:${PN} = "bash"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/pando-init-flasher-board ${D}${bindir}
}
