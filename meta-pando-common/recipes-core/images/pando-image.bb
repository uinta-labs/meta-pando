SUMMARY = "Pando image"
IMAGE_LINGUAS = " "
LICENSE = "Apache-2.0"

REQUIRED_DISTRO_FEATURES += " systemd"

PANDO_FLAG_FILE = "${PANDO_IMAGE_FLAG_FILE}"

#
# The default root filesystem partition size is set in such a way that the
# entire space taken by Pando OS would not exceed 700 MiB. This  can be
# overwritten by board specific layers.
#
IMAGE_ROOTFS_SIZE = "${@pando_rootfs_size(d)}"
IMAGE_OVERHEAD_FACTOR = "1.0"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
IMAGE_ROOTFS_MAXSIZE = "${IMAGE_ROOTFS_SIZE}"

IMAGE_FSTYPES = "pandoos-img"

inherit core-image image-pando features_check sign-digest

### SPLASH += "plymouth-pando-theme"

IMAGE_FEATURES:append = " \
    splash \
    ssh-server-openssh \
    read-only-rootfs \
    empty-root-password \
    "


###     libnss-ato 

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    packagegroup-pando-debugtools \
    packagegroup-pando-connectivity \
    packagegroup-pando \
    "

# add packages for LUKS operations if necessary
IMAGE_INSTALL:append = "${@oe.utils.conditional('SIGN_API','','',' cryptsetup lvm2-udevrules',d)}"
#### IMAGE_INSTALL:append = "${@bb.utils.contains('MACHINE_FEATURES', 'tpm', ' tpm2-tools libtss2-tcti-device os-helpers-tpm2', '',d)}"

generate_rootfs_fingerprints () {
    # Generate fingerprints file for root filesystem
    # We exclude some entries that are bind mounted to state partition
    # and modified at runtime.
    find ${IMAGE_ROOTFS} -xdev -type f \
        -not -name ${PANDO_FINGERPRINT_FILENAME}.${PANDO_FINGERPRINT_EXT} \
        -not -name hostname \
        -not -name machine-id \
        -not -name .rnd \
        -exec md5sum {} \; | sed "s#${IMAGE_ROOTFS}##g" | \
        sort -k2 > ${IMAGE_ROOTFS}/${PANDO_FINGERPRINT_FILENAME}.${PANDO_FINGERPRINT_EXT}
}

generate_hostos_version () {
    echo "${HOSTOS_VERSION}" > ${DEPLOY_DIR_IMAGE}/VERSION_HOSTOS
}

symlink_image_signature () {
    # This is probably not the correct way to do it, but it works.
    # We sign PANDO_RAW_IMG, which ends up in IMGDEPLOYDIR
    # and has a timestamp in the file name. We need to get rid
    # of the timestamp for the final deploy, so that the file
    # ends up in a predictable location.

    if [ -n "${SIGN_API}" ]; then
        ln -sf "${PANDO_RAW_IMG}.sig" "${DEPLOY_DIR_IMAGE}/pando-image-${MACHINE}.pandoos-img.sig"
    fi
}

DEPENDS += "jq-native"

IMAGE_PREPROCESS_COMMAND:append = " generate_rootfs_fingerprints ; "
IMAGE_POSTPROCESS_COMMAND += " \
    generate_hostos_version ; \
    symlink_image_signature ; \
"

###     boot-logo.png:/splash/boot-logo.png

PANDO_BOOT_PARTITION_FILES:append = " \
    os-release:/os-release \
"

# add the secure boot keys if needed
PANDO_BOOT_PARTITION_FILES:append = "${@oe.utils.conditional('SIGN_API','','','pando-keys:/pando-keys/',d)}"

# add the LUKS variant of GRUB config if needed
PANDO_BOOT_PARTITION_FILES:append = "${@bb.utils.contains('MACHINE_FEATURES','efi',' grub.cfg_internal_luks:/EFI/BOOT/grub-luks.cfg','',d)}"

# add the generated <machine-name>.json to the pando-boot partition, renamed as device-type.json
### PANDO_BOOT_PARTITION_FILES:append = " ${PANDO_COREBASE}/../../../${MACHINE}.json:/device-type.json"

### example NetworkManager config file
###PANDO_BOOT_PARTITION_FILES:append = " \
###    system-connections/pando-sample.ignore:/system-connections/pando-sample.ignore \
###    system-connections/README.ignore:/system-connections/README.ignore \
### "

PANDO_BOOT_PARTITION_FILES:append = "${@ ' extra_uEnv.txt:/extra_uEnv.txt ' if d.getVar('UBOOT_MACHINE') or d.getVar('UBOOT_CONFIG') else ''}"

# image flag file
PANDO_BOOT_PARTITION_FILES:append = " ${PANDO_IMAGE_FLAG_FILE}:/${PANDO_IMAGE_FLAG_FILE}"

addtask image_size_check after do_image_pandoos_img before do_image_complete
addtask do_pandoos_boot_dirgen_and_deploy after do_image before do_image_complete

SIGNING_ARTIFACTS = "${PANDO_RAW_IMG}"
addtask sign_digest after do_image_pandoos_img before do_image_complete
