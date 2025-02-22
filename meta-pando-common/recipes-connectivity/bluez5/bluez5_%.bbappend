FILESEXTRAPATHS:append := ":${THISDIR}/files"

SRC_URI += " \
    file://10-local-bt-hci-up.rules \
    file://bluetooth.conf.systemd \
    file://main.conf \
    "

do_install:append() {
    install -D -m 0644 ${WORKDIR}/10-local-bt-hci-up.rules ${D}${libdir}/udev/rules.d/10-local-bt-hci-up.rules

    install -d ${D}/${systemd_unitdir}/system/bluetooth.service.d
    install -m 0644 ${WORKDIR}/bluetooth.conf.systemd ${D}/${systemd_unitdir}/system/bluetooth.service.d/bluetooth.conf
    sed -i "s,@pkglibexecdir@,${libexecdir},g" ${D}/${systemd_unitdir}/system/bluetooth.service.d/bluetooth.conf

    install -d ${D}/var/lib/bluetooth
    install -d ${D}${sysconfdir}/bluetooth
    install -m 0644 ${WORKDIR}/main.conf ${D}${sysconfdir}/bluetooth/main.conf
}

PACKAGECONFIG:append = " sixaxis"
