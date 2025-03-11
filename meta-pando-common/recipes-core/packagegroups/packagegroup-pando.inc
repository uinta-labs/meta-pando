SUMMARY = "Pando Package Group"
LICENSE = "Apache-2.0"

PR = "r1"

inherit packagegroup

## Previously:
### os-config
### os-fan-profile
### os-power-mode
### ${PANDO_REGISTER}

RDEPENDS:${PN} = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd-analyze', '', d)} \
    ${PANDO_INIT_PACKAGE} \
    ${PANDO_MOUNTS} \
    chrony \
    chronyc \
    extra-udev-rules \
    rsync \
    kernel-modules \
    kernel-image-initramfs \
    os-release \
    less \
    development-features \
    ldd \
    "
