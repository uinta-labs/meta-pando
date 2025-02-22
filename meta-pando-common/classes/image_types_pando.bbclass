inherit image_types

#
# Create a raw image that can by written onto a storage device using dd/etcher.
#
# PANDO_IMAGE_BOOTLOADER        - bootloader
# PANDO_BOOT_PARTITION_FILES    - list of items describing files to be deployed
#                                 on boot partition
#                               - items need to be in the 'src:dst' format
#                               - src needs to be relative to DEPLOY_DIR_IMAGE
#                               - dst needs to be an absolute path
#                               - if dst is ommited ('src:' format used),
#                                 absolute path of src will be used as dst
# PANDO_ROOT_FSTYPE             - rootfs image type to be used for integrating
#                                 in the final raw image
# PANDO_BOOT_SIZE               - size of boot partition in KiB
# PANDO_RAW_IMG_COMPRESSION     - define this to compress the final raw image
#                                 with gzip, xz or bzip2
# PARTITION_TABLE_TYPE          - defines partition table type to use: gpt or
#                                 msdos. Defaults to msdos
# DEVICE_SPECIFIC_SPACE         - total amount of extra space that a device needs
#                                 for its configuration
#
#
#
# Partition table:
#
#   +-------------------+
#   |                   |  ^
#   | Reserved          |  |PANDO_IMAGE_ALIGNMENT
#   |                   |  v
#   +-------------------+
#   |                   |  ^
#   | Boot partition    |  |PANDO_BOOT_SIZE
#   |                   |  v
#   +-------------------+
#   |                   |  ^
#   | Root partition A  |  |PANDO_ROOTA_SIZE
#   |                   |  v
#   +-------------------+
#   |                   |  ^
#   | Root partition B  |  |PANDO_ROOTB_SIZE
#   |                   |  v
#   +-------------------+
#   |-------------------|
#   ||                 ||  ^
#   || Reserved        ||  |PANDO_IMAGE_ALIGNMENT
#   ||                 ||  v
#   |-------------------|
#   ||                 ||  ^
#   || State partition ||  |PANDO_STATE_SIZE
#   ||                 ||  v
#   |-------------------|
#   ||                 ||  ^
#   || Reserved        ||  |PANDO_IMAGE_ALIGNMENT
#   ||                 ||  v
#   |-------------------|
#   ||                 ||  ^
#   || Data partition  ||  |PANDO_DATA_SIZE
#   ||                 ||  v
#   |-------------------|
#   +-------------------+
#

PANDO_ROOT_FSTYPE ?= "hostapp-ext4"

python() {
    # Check if we are running on a poky version which deploys to IMGDEPLOYDIR
    # instead of DEPLOY_DIR_IMAGE (poky morty introduced this change)
    # "Recipes inheriting the image class should copy files to be deployed into IMGDEPLOYDIR, and the class will take care of copying them into DEPLOY_DIR_IMAGE afterwards."
    d.setVar('PANDO_ROOT_FS', '${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.${PANDO_ROOT_FSTYPE}')
    d.setVar('PANDO_RAW_IMG', '${IMGDEPLOYDIR}/${IMAGE_NAME}.pandoos-img')
    d.setVar('PANDO_RAW_BMAP', '${IMGDEPLOYDIR}/${IMAGE_NAME}.bmap')
    d.setVar('PANDO_DOCKER_IMG', '${IMGDEPLOYDIR}/${IMAGE_NAME}.docker')
    d.setVar('PANDO_HOSTAPP_IMG', '${IMGDEPLOYDIR}/${IMAGE_NAME}.${PANDO_ROOT_FSTYPE}')

    d.setVar('PANDO_IMAGE_BOOTLOADER_DEPLOY_TASK', ' '.join(bootloader + ':do_populate_sysroot' for bootloader in d.getVar("PANDO_IMAGE_BOOTLOADER", True).split()))
}

def disk_aligned(d, rootfs_size):
    saved_rootfs_size = rootfs_size
    rfs_alignment = int(d.getVar("IMAGE_ROOTFS_ALIGNMENT"))
    rootfs_size += rfs_alignment - 1
    rootfs_size -= rootfs_size % rfs_alignment
    bb.debug(1, 'requested rootfs size %d, aligned %d' % (saved_rootfs_size, rootfs_size) )
    return rootfs_size

# The rootfs size is calculated by substracting from the maximum Pando OS image
# 700 MiB size, the size  of all other partitions except the data partition,
# dividing by 2, and substracting filesystem metadata and reserved allocations
def pando_rootfs_size(d):
    boot_part_size = int(d.getVar("PANDO_BOOT_SIZE"))
    state_part_size = int(d.getVar("PANDO_STATE_SIZE"))
    pando_rootfs_size = int(((700 * 1024) - boot_part_size - state_part_size) / 2)
    return int(disk_aligned(d, pando_rootfs_size))

PANDO_BOOT_FS_LABEL ?= "pando-boot"
PANDO_ROOTA_FS_LABEL ?= "pando-rootA"
PANDO_ROOTB_FS_LABEL ?= "pando-rootB"
PANDO_STATE_FS_LABEL ?= "pando-state"
PANDO_DATA_FS_LABEL ?= "pando-data"

# By default boot partition is a fat16
PANDO_BOOT_FAT32 ?= "0"

# Sizes in KiB
PANDO_BOOT_SIZE ?= "40960"
PANDO_ROOTB_SIZE ?= ""
PANDO_STATE_SIZE ?= "20480"
PANDO_IMAGE_ALIGNMENT ?= "4096"
IMAGE_ROOTFS_ALIGNMENT = "${PANDO_IMAGE_ALIGNMENT}"
DEVICE_SPECIFIC_SPACE ?= "${PANDO_IMAGE_ALIGNMENT}"
DEVICE_SPECIFIC_BOOTFS_OPTS ?= ""

PANDO_BOOT_WORKDIR ?= "${WORKDIR}/${PANDO_BOOT_FS_LABEL}"

PANDO_BOOT_FINGERPRINT_PATH ?= "${WORKDIR}/${PANDO_FINGERPRINT_FILENAME}.${PANDO_FINGERPRINT_EXT}"
PANDO_IMAGE_BOOTLOADER ?= "virtual/bootloader"
PANDO_RAW_IMG_COMPRESSION ?= ""
PANDO_DATA_FS ?= "${DEPLOY_DIR_IMAGE}/${PANDO_DATA_FS_LABEL}.img"
PANDO_BOOT_FS = "${WORKDIR}/${PANDO_BOOT_FS_LABEL}.img"
PANDO_ROOTB_FS = "${WORKDIR}/${PANDO_ROOTB_FS_LABEL}.img"
PANDO_STATE_FS ?= "${WORKDIR}/${PANDO_STATE_FS_LABEL}.img"

# pandoos-img depends on the rootfs image
IMAGE_TYPEDEP:pandoos-img = "${PANDO_ROOT_FSTYPE}"
do_image_pandoos_img[depends] = " \
    coreutils-native:do_populate_sysroot \
    docker-disk:do_deploy \
    dosfstools-native:do_populate_sysroot \
    e2fsprogs-native:do_populate_sysroot \
    mtools-native:do_populate_sysroot \
    parted-native:do_populate_sysroot \
    bmaptool-native:do_populate_sysroot \
    virtual/kernel:do_deploy \
    ${PANDO_IMAGE_BOOTLOADER_DEPLOY_TASK} \
    "

do_image_pandoos_img[depends] += "${@ ' virtual/bootloader:do_deploy ' if (d.getVar('UBOOT_CONFIG') or d.getVar('UBOOT_MACHINE')) else ''}"

device_specific_configuration() {
    echo "No device specific configuration"
}

IMAGE_CMD:pandoos-img () {
    #
    # Partition size computation (aligned to PANDO_IMAGE_ALIGNMENT)
    #

    # pando-boot
    PANDO_BOOT_SIZE_ALIGNED=$(expr ${PANDO_BOOT_SIZE} \+ ${PANDO_IMAGE_ALIGNMENT} - 1)
    PANDO_BOOT_SIZE_ALIGNED=$(expr ${PANDO_BOOT_SIZE_ALIGNED} \- ${PANDO_BOOT_SIZE_ALIGNED} \% ${PANDO_IMAGE_ALIGNMENT})

    # pando-rootA
    PANDO_ROOTA_SIZE=$(du -Lbks ${PANDO_ROOT_FS} | awk '{print $1}')
    PANDO_ROOTA_SIZE_ALIGNED=$(expr ${PANDO_ROOTA_SIZE} \+ ${PANDO_IMAGE_ALIGNMENT} \- 1)
    PANDO_ROOTA_SIZE_ALIGNED=$(expr ${PANDO_ROOTA_SIZE_ALIGNED} \- ${PANDO_ROOTA_SIZE_ALIGNED} \% ${PANDO_IMAGE_ALIGNMENT})

    # pando-rootB
    if [ -n "${PANDO_ROOTB_SIZE}" ]; then
        PANDO_ROOTB_SIZE_ALIGNED=$(expr ${PANDO_ROOTB_SIZE} \+ ${PANDO_IMAGE_ALIGNMENT} \- 1)
        PANDO_ROOTB_SIZE_ALIGNED=$(expr ${PANDO_ROOTB_SIZE_ALIGNED} \- ${PANDO_ROOTB_SIZE_ALIGNED} \% ${PANDO_IMAGE_ALIGNMENT})
    else
        PANDO_ROOTB_SIZE_ALIGNED=${PANDO_ROOTA_SIZE_ALIGNED}
    fi

    # pando-state
    if [ -n "${PANDO_STATE_FS}" ]; then
        PANDO_STATE_SIZE_ALIGNED=$(expr ${PANDO_STATE_SIZE} \+ ${PANDO_IMAGE_ALIGNMENT} \- 1)
        PANDO_STATE_SIZE_ALIGNED=$(expr ${PANDO_STATE_SIZE_ALIGNED} \- ${PANDO_STATE_SIZE_ALIGNED} \% ${PANDO_IMAGE_ALIGNMENT})
    else
        PANDO_STATE_SIZE_ALIGNED=${PANDO_IMAGE_ALIGNMENT}
    fi

    # pando-data
    if [ -n "${PANDO_DATA_FS}" ]; then
        PANDO_DATA_SIZE=`du -bks ${PANDO_DATA_FS} | awk '{print $1}'`
        PANDO_DATA_SIZE_ALIGNED=$(expr ${PANDO_DATA_SIZE} \+ ${PANDO_IMAGE_ALIGNMENT} \- 1)
        PANDO_DATA_SIZE_ALIGNED=$(expr ${PANDO_DATA_SIZE_ALIGNED} \- ${PANDO_DATA_SIZE_ALIGNED} \% ${PANDO_IMAGE_ALIGNMENT})
    else
        PANDO_DATA_SIZE_ALIGNED=${PANDO_IMAGE_ALIGNMENT}
    fi

    if [ $(expr ${DEVICE_SPECIFIC_SPACE} % ${PANDO_IMAGE_ALIGNMENT}) -ne 0  ]; then
        bbfatal "The space reserved for your specific device is not aligned to ${PANDO_IMAGE_ALIGNMENT}."
    fi

    PANDO_RAW_IMG_SIZE=$(expr \
        ${DEVICE_SPECIFIC_SPACE} \+ \
        ${PANDO_IMAGE_ALIGNMENT} \+ \
        ${PANDO_BOOT_SIZE_ALIGNED} \+ \
        ${PANDO_ROOTA_SIZE_ALIGNED} \+ \
        ${PANDO_ROOTB_SIZE_ALIGNED} \+ \
        ${PANDO_IMAGE_ALIGNMENT} \+ \
        ${PANDO_STATE_SIZE_ALIGNED} \+ \
        ${PANDO_IMAGE_ALIGNMENT} \+ \
        ${PANDO_DATA_SIZE_ALIGNED} \
    )
    echo "Creating raw image as it follow:"
    echo "  Boot partition ${PANDO_BOOT_SIZE_ALIGNED} KiB [$(expr ${PANDO_BOOT_SIZE_ALIGNED} \/ 1024) MiB]"
    echo "  Root A partition ${PANDO_ROOTA_SIZE_ALIGNED} KiB [$(expr ${PANDO_ROOTA_SIZE_ALIGNED} \/ 1024) MiB]"
    echo "  Root B partition ${PANDO_ROOTA_SIZE_ALIGNED} KiB [$(expr ${PANDO_ROOTB_SIZE_ALIGNED} \/ 1024) MiB]"
    echo "  State partition ${PANDO_STATE_SIZE_ALIGNED} KiB [$(expr ${PANDO_STATE_SIZE_ALIGNED} \/ 1024) MiB]"
    echo "  Data partition ${PANDO_DATA_SIZE_ALIGNED} KiB [$(expr ${PANDO_DATA_SIZE_ALIGNED} \/ 1024) MiB]"
    echo "---"
    echo "Total raw image size ${PANDO_RAW_IMG_SIZE} KiB [$(expr ${PANDO_RAW_IMG_SIZE} \/ 1024) MiB]"

    #
    # Generate the raw image with partition table
    #

    truncate -s "$(expr ${PANDO_RAW_IMG_SIZE} \* 1024)" "${PANDO_RAW_IMG}"

    if [ "${PARTITION_TABLE_TYPE}" != "msdos" ] && [ "${PARTITION_TABLE_TYPE}" != "gpt" ]; then
        bbfatal "Unrecognized partition table: ${PARTITION_TABLE_TYPE}"
    fi

    parted ${PANDO_RAW_IMG} mklabel ${PARTITION_TABLE_TYPE}

    device_specific_configuration

    # pando-boot
    #
    if [ "${PARTITION_TABLE_TYPE}" = "msdos" ]; then
        if [ "${PANDO_BOOT_FAT32}" = "1" ]; then
            OPTS="primary fat32"
        else
            OPTS="primary fat16"
        fi
    elif [ "${PARTITION_TABLE_TYPE}" = "gpt" ]; then
        OPTS="${PANDO_BOOT_FS_LABEL}"
    fi
    START=${DEVICE_SPECIFIC_SPACE}
    END=$(expr ${START} \+ ${PANDO_BOOT_SIZE_ALIGNED})
    parted -s ${PANDO_RAW_IMG} unit KiB mkpart ${OPTS} ${START} ${END}
    PANDO_BOOT_PN=$(parted -s ${PANDO_RAW_IMG} print | tail -n 2 | tr '\n' ' ' | awk '{print $1}')
    parted -s ${PANDO_RAW_IMG} set ${PANDO_BOOT_PN} boot on

    # pando-rootA
    if [ "${PARTITION_TABLE_TYPE}" = "msdos" ]; then
        OPTS="primary"
    elif [ "${PARTITION_TABLE_TYPE}" = "gpt" ]; then
        OPTS="${PANDO_ROOTA_FS_LABEL}"
    fi
    START=${END}
    END=$(expr ${START} \+ ${PANDO_ROOTA_SIZE_ALIGNED})
    parted -s ${PANDO_RAW_IMG} unit KiB mkpart ${OPTS} ${START} ${END}

    # pando-rootB
    if [ "${PARTITION_TABLE_TYPE}" = "msdos" ]; then
        OPTS="primary"
    elif [ "${PARTITION_TABLE_TYPE}" = "gpt" ]; then
        OPTS="${PANDO_ROOTB_FS_LABEL}"
    fi
    START=${END}
    END=$(expr ${START} \+ ${PANDO_ROOTB_SIZE_ALIGNED})
    parted -s ${PANDO_RAW_IMG} unit KiB mkpart ${OPTS} ${START} ${END}
    PANDO_ROOTB_PN=$(parted -s ${PANDO_RAW_IMG} print | tail -n 2 | tr '\n' ' ' | awk '{print $1}')

    # extended partition
    if [ "${PARTITION_TABLE_TYPE}" = "msdos" ]; then
        START=${END}
        END=$(expr ${START} \+ ${PANDO_IMAGE_ALIGNMENT})
        parted -s ${PANDO_RAW_IMG} -- unit KiB mkpart extended ${START} -1s
    fi

    # pando-state
    if [ "${PARTITION_TABLE_TYPE}" = "msdos" ]; then
        OPTS="logical"
    elif [ "${PARTITION_TABLE_TYPE}" = "gpt" ]; then
        OPTS="${PANDO_STATE_FS_LABEL}"
    fi
    START=${END}
    END=$(expr ${START} \+ ${PANDO_STATE_SIZE_ALIGNED})
    parted -s ${PANDO_RAW_IMG} unit KiB mkpart ${OPTS} ${START} ${END}
    PANDO_STATE_PN=$(parted -s ${PANDO_RAW_IMG} print | tail -n 2 | tr '\n' ' ' | awk '{print $1}')

    # pando-data
    if [ "${PARTITION_TABLE_TYPE}" = "msdos" ]; then
        OPTS="logical"
        START=$(expr ${END} \+ ${PANDO_IMAGE_ALIGNMENT})
    elif [ "${PARTITION_TABLE_TYPE}" = "gpt" ]; then
        OPTS="${PANDO_DATA_FS_LABEL}"
        START=${END}
    fi
    parted -s ${PANDO_RAW_IMG} -- unit KiB mkpart ${OPTS} ${START} 100%

    #
    # Generate partitions
    #

    # pando-boot
    PANDO_BOOT_BLOCKS=$(LC_ALL=C parted -s ${PANDO_RAW_IMG} unit b print | grep -E "^(| )${PANDO_BOOT_PN} " | awk '{ print substr($4, 1, length($4 -1)) / 512 /2 }')
    rm -rf ${PANDO_BOOT_FS}
    OPTS="-n ${PANDO_BOOT_FS_LABEL} -S 512 -C"
    if [ "${PANDO_BOOT_FAT32}" = "1" ]; then
        OPTS="$OPTS -F 32"
    fi
    OPTS="$OPTS ${DEVICE_SPECIFIC_BOOTFS_OPTS}"
    eval mkfs.vfat "$OPTS" "${PANDO_BOOT_FS}" "${PANDO_BOOT_BLOCKS}"
    if [ "$(ls -A ${PANDO_BOOT_WORKDIR})" ]; then
        mcopy -i ${PANDO_BOOT_FS} -svm ${PANDO_BOOT_WORKDIR}/* ::
    else
        bbwarn "Boot partition was detected empty."
    fi

    # pando-rootB
    PANDO_ROOTB_BLOCKS=$(LC_ALL=C parted -s ${PANDO_RAW_IMG} unit b print | grep -E "^(| )${PANDO_ROOTB_PN} " | awk '{ print substr($4, 1, length($4 -1)) / 512 /2 }')
    rm -rf ${PANDO_ROOTB_FS}
    truncate -s "$(expr ${PANDO_ROOTB_BLOCKS} \* 1024 )" "${PANDO_ROOTB_FS}"
    mkfs.ext4 -E lazy_itable_init=0,lazy_journal_init=0 -i 8192 -F -L "${PANDO_ROOTB_FS_LABEL}" ${PANDO_ROOTB_FS}

    # pando-state
    if [ -n "${PANDO_STATE_FS}" ]; then
        PANDO_STATE_BLOCKS=$(LC_ALL=C parted -s ${PANDO_RAW_IMG} unit b print | grep -E "^(| )${PANDO_STATE_PN} " | awk '{ print substr($4, 1, length($4 -1)) / 512 /2 }')
        rm -rf ${PANDO_STATE_FS}
        truncate -s "$(expr ${PANDO_STATE_BLOCKS} \* 1024 )" "${PANDO_STATE_FS}"
        mkfs.ext4 -F -L "${PANDO_STATE_FS_LABEL}" ${PANDO_STATE_FS}
    fi

    # Label what is not labeled
    if case "${PANDO_ROOT_FSTYPE}" in *ext4) true;; *) false;; esac; then # can be ext4 or hostapp-ext4
        e2label ${PANDO_ROOT_FS} ${PANDO_ROOTA_FS_LABEL}
    else
        bbfatal "Rootfs labeling for type '${PANDO_ROOT_FSTYPE}' has not been implemented!"
    fi

    if [ -n "${PANDO_DATA_FS}" ]; then
        e2label ${PANDO_DATA_FS} ${PANDO_DATA_FS_LABEL}
    fi

    #
    # Burn partitions
    #
    offset=${DEVICE_SPECIFIC_SPACE}
    dd if=${PANDO_BOOT_FS} of=${PANDO_RAW_IMG} conv=notrunc,sparse seek=${offset} bs=1024
    offset=$(expr ${offset} \+ ${PANDO_BOOT_SIZE_ALIGNED})
    dd if=${PANDO_ROOT_FS} of=${PANDO_RAW_IMG} conv=notrunc,sparse seek=${offset} bs=1024
    offset=$(expr ${offset} \+ ${PANDO_ROOTA_SIZE_ALIGNED})
    dd if=${PANDO_ROOTB_FS} of=${PANDO_RAW_IMG} conv=notrunc,sparse seek=${offset} bs=1024
    offset=$(expr ${offset} \+ ${PANDO_ROOTB_SIZE_ALIGNED})
    if [ -n "${PANDO_STATE_FS}" ]; then
        if [ "${PARTITION_TABLE_TYPE}" = "msdos" ]; then
            offset=$(expr ${offset} \+ ${PANDO_IMAGE_ALIGNMENT})
            dd if=${PANDO_STATE_FS} of=${PANDO_RAW_IMG} conv=notrunc,sparse seek=${offset} bs=1024
        elif [ "${PARTITION_TABLE_TYPE}" = "gpt" ]; then
            dd if=${PANDO_STATE_FS} of=${PANDO_RAW_IMG} conv=notrunc,sparse seek=${offset} bs=1024
        fi
    fi
    if [ -n "${PANDO_DATA_FS}" ]; then
        if [ "${PARTITION_TABLE_TYPE}" = "msdos" ]; then
            offset=$(expr ${offset} \+ ${PANDO_STATE_SIZE_ALIGNED} \+ ${PANDO_IMAGE_ALIGNMENT})
            dd if=${PANDO_DATA_FS} of=${PANDO_RAW_IMG} conv=notrunc,sparse seek=${offset} bs=1024
        elif [ "${PARTITION_TABLE_TYPE}" = "gpt" ]; then
            offset=$(expr ${offset} \+ ${PANDO_STATE_SIZE_ALIGNED})
            dd if=${PANDO_DATA_FS} of=${PANDO_RAW_IMG} conv=notrunc,sparse seek=${offset} bs=1024
        fi
    fi

    # create bmap to enable recreating sparse image after full allocation
    bmaptool create ${PANDO_RAW_IMG} > ${PANDO_RAW_BMAP}

    # Optionally apply compression
    case "${PANDO_RAW_IMG_COMPRESSION}" in
    "gzip")
        gzip -k9 "${PANDO_RAW_IMG}"
        ;;
    "bzip2")
        bzip2 -k9 "${PANDO_RAW_IMG}"
        ;;
    "xz")
        xz -k "${PANDO_RAW_IMG}"
        ;;
    esac

### Perhaps not needed if name updates (above) work
###    ln -sf $(basename ${PANDO_RAW_IMG}) ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.pandoos-img
}

# Make sure we regenerate images if we modify the files that go in the boot
# partition
do_rootfs[vardeps] += "PANDO_BOOT_PARTITION_FILES"

# XXX(petrosagg): This should be eventually implemented using a docker-native daemon
IMAGE_CMD:docker () {
    DOCKER_IMAGE=$(${IMAGE_CMD_TAR} -cv -C ${IMAGE_ROOTFS} . | DOCKER_API_VERSION=${BALENA_API_VERSION} docker import -)
    DOCKER_API_VERSION=${BALENA_API_VERSION} docker save ${DOCKER_IMAGE} > ${PANDO_DOCKER_IMG}
    bbwarn "[BANANA] Wrote ${DOCKER_IMAGE} to ${PANDO_DOCKER_IMG}"
}

IMAGE_TYPEDEP:hostapp-ext4 = "docker"

do_image_hostapp_ext4[depends] = " \
    mkfs-hostapp-native:do_populate_sysroot \
    "

IMAGE_CMD:hostapp-ext4 () {
    truncate -s "$(expr ${ROOTFS_SIZE} \* 1024)" "${PANDO_HOSTAPP_IMG}"
    mkfs.hostapp -t "${TMPDIR}" -s "${STAGING_DIR_NATIVE}" -i ${PANDO_DOCKER_IMG} -o ${PANDO_HOSTAPP_IMG}
    ln -sf $(basename ${PANDO_HOSTAPP_IMG}) ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.${PANDO_ROOT_FSTYPE}
}
