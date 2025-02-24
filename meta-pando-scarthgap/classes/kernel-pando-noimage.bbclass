# Don't trigger in the kernel image without initramfs
# Boards should:
# a) use kernel-image-initramfs and deploy in in the rootfs (ex bbb)
# b) use boot deployment using PANDO_BOOT_PARTITION_FILES mechanism to deploy
#    the initramfs bundled kernel image
python __anonymous() {
    kernel_image_type = d.getVar('KERNEL_IMAGETYPE')
    d.appendVar('PACKAGE_EXCLUDE', ' kernel-image-%s-*' % kernel_image_type.lower())
}
