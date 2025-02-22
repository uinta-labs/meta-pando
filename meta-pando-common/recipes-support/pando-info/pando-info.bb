DESCRIPTION = "Pando info ascii art"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${PANDO_COREBASE}/COPYING.Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

ALLOW_EMPTY:${PN} = "1"

SRC_URI = " \
    file://pando-info \
    file://pando-info@.service \
    "
S = "${WORKDIR}"

inherit allarch

TTYS = "tty1"

RDEPENDS:${PN} = "bash"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_build[noexec] = "1"

do_install() {
    install -d ${D}${sbindir}/
    install -m 0755 ${WORKDIR}/pando-info ${D}${sbindir}/

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
        install -m 0644 ${WORKDIR}/pando-info@.service ${D}${systemd_unitdir}/system/

        # Enable services
        for ttydev in ${TTYS}; do
            ln -sf ${systemd_unitdir}/system/pando-info@.service \
                ${D}${sysconfdir}/systemd/system/multi-user.target.wants/pando-info@$ttydev.service
        done

        sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
            -e 's,@SBINDIR@,${sbindir},g' \
            -e 's,@BINDIR@,${bindir},g' \
            ${D}${systemd_unitdir}/system/*.service
    fi
}

FILES:${PN} += "${systemd_unitdir}/system/*.service ${sysconfdir}"
