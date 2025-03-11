SUMMARY = "Pando image flasher"
IMAGE_LINGUAS = " "
LICENSE = "Apache-2.0"

inherit core-image image-pando features_check

REQUIRED_DISTRO_FEATURES += " systemd"

PANDO_FLAG_FILE = "${PANDO_FLASHER_FLAG_FILE}"

IMAGE_FSTYPES = "pandoos-img"

PANDO_ROOT_FSTYPE = "ext4"

# Make sure you have the pando image ready
do_image_pandoos_img[depends] += "pando-image:do_image_complete"

# Ensure we have the raw image ready in DEPLOY_DIR_IMAGE
do_image[depends] += "pando-image:do_image_complete"

IMAGE_FEATURES:append = " \
    splash \
    read-only-rootfs \
    ssh-server-openssh \
    "

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    packagegroup-pando-connectivity \
    packagegroup-pando-flasher \
    "

# Avoid useless space - no data or state on flasher
PANDO_DATA_FS = ""
PANDO_STATE_FS = ""

# We do not use a second root fs partition for the flasher image, so just default to PANDO_IMAGE_ALIGNMENT
PANDO_ROOTB_SIZE = "${PANDO_IMAGE_ALIGNMENT}"

# Avoid naming clash with pando image labels
PANDO_BOOT_FS_LABEL = "flash-boot"
PANDO_ROOTA_FS_LABEL = "flash-rootA"
PANDO_ROOTB_FS_LABEL = "flash-rootB"
PANDO_STATE_FS_LABEL = "flash-state"
PANDO_DATA_FS_LABEL = "flash-data"

# add the secure boot keys if needed
# PANDO_BOOT_PARTITION_FILES:append = "${@oe.utils.conditional('SIGN_API','','',' pando-keys:/pando-keys/',d)}"

# add the LUKS variant of GRUB config if needed
PANDO_BOOT_PARTITION_FILES:append = "${@bb.utils.contains('MACHINE_FEATURES','efi',' grub.cfg_internal_luks:','',d)}"

### # Put the boot logo, uEnv.txt files inside the boot partition
### PANDO_BOOT_PARTITION_FILES:append = " boot-logo.png:/splash/boot-logo.png"

# add the generated <machine-name>.json to the flash-boot partition, renamed as device-type.json
### PANDO_BOOT_PARTITION_FILES:append = " ${PANDO_COREBASE}/../../../${MACHINE}.json:/device-type.json"

# Put pando-image in the flasher rootfs
add_pando_image_to_flasher_rootfs() {
    mkdir -p ${WORKDIR}/rootfs/opt
    cp ${DEPLOY_DIR_IMAGE}/pando-image${IMAGE_MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.pandoos-img ${WORKDIR}/rootfs/opt
    if [ -n "${SIGN_API}" ]; then
        cp "${DEPLOY_DIR_IMAGE}/pando-image${IMAGE_MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.pandoos-img.sig" "${WORKDIR}/rootfs/opt/"
    fi
}

IMAGE_PREPROCESS_COMMAND += " add_pando_image_to_flasher_rootfs; "

# # example NetworkManager config file
# PANDO_BOOT_PARTITION_FILES:append = " \
#     system-connections/balena-sample.ignore:/system-connections/balena-sample.ignore \
#     system-connections/README.ignore:/system-connections/README.ignore \
#     "

# Flasher flag file
PANDO_BOOT_PARTITION_FILES:append = " ${PANDO_FLASHER_FLAG_FILE}:/${PANDO_FLASHER_FLAG_FILE}"
