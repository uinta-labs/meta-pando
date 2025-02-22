DESCRIPTION = "This minimal image is executed by a vendor provided bootloader. \
It contains all the Pando OS bootloader specific features, and will kexec the \
Pando OS initramfs"

PACKAGE_INSTALL = " \
    base-passwd \
    busybox \
    glibc-gconv \
    glibc-gconv-ibm437 \
    glibc-gconv-ibm850 \
    initramfs-module-abroot \
    initramfs-module-debug \
    initramfs-module-fsck \
    initramfs-module-kexec \
    initramfs-module-udev \
    initramfs-framework-base \
    udev \
"

PACKAGE_INSTALL:append = "${@oe.utils.conditional('SIGN_API','','',' initramfs-module-cryptsetup initramfs-module-pandodataexpander',d)}"

BAD_RECOMMENDATIONS += "busybox-syslog"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "pando-image-bootloader-initramfs"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit image kernel-pando-noimage

# Pulled in via PREFERRED_PROVIDER_virtual/kernel
PACKAGE_EXCLUDE += "kernel-module-* "

IMAGE_ROOTFS_SIZE = "8192"
