#
# In order to be able to configure kernel in a specific way for all the resin
# platforms, we define three tasks and inject them in between configure and
# compile tasks.
#
#        *
#        |
#  +-----v------+
#  |do_configure|
#  +-----+------+
#        |
#        <-----------------------------+
#        |                             |
#        +-----------------------+     | depends
#        |                       |     |
#        |         +-------------v-----+--------+
#        |         |do_kernel_resin_injectconfig|
#        |         +-------------+-----^--------+
#        |                       |     | depends
# ++-----v-----++  +-------------+-----+--------+
# |>other tasks<|  |do_kernel_resin_reconfigure |
# ++-----+-----++  +-------------+-----^--------+
#        |                       |     | depends
#        |         +-------------+-----+--------+
#        |         |do_kernel_resin_checkconfig |
#        |         +-------------+-----^--------+
#        |                       |     |
#        <-----------------------+     | depends
#        |                             |
#        |                             |
#        |                             |
#   +----v-----+                       |
#   |do_compile+-----------------------+
#   +----+-----+
#        |
#        v
#        *
#
#   This flow exdends a kernel recipe with the ability to:
#       a) Inject a specific kernel configuration
#       b) Reconfigure kernel
#       c) Check that the defined/requested configuration was actually
#          activated by the kernel reconfiguration mechanism
#
#   The Resin specific kernel configuration can be done in two different ways:
#   1. Defining configuration blocks and activating the specific flag
#       a) Define a new kernel configuration block using
#          PANDO_CONFIGS[mynewconfigblock].
#       b) [optional] Define a kernel configuration block which is a dependency
#          for PANDO_CONFIGS[mynewconfigblock] using
#          PANDO_CONFIGS_DEPS[mynewconfigblock]. This block won't be checked at
#          do_kernel_resin_checkconfig but will be configured before the configs
#          in PANDO_CONFIGS[mynewconfigblock].
#       c) Activate the new block appending the config block name to
#          PANDO_CONFIGS.
#          Ex: PANDO_CONFIGS:append = " mynewconfigblock"
#          Mind the space!
#   2. Using a special filename defined as PANDO_DEFCONFIG_NAME
#       a) [optional] Define PANDO_DEFCONFIG_NAME. Default: "pando-defconfig"
#       b) Add PANDO_DEFCONFIG_NAME to SRC_URI.

inherit kernel-pando-noimage sign-efi sign-gpg

PANDO_DEFCONFIG_NAME ?= "pando-defconfig"

def get_kernel_version(d):
    import os, re
    kernelsource = d.getVar('S', True)

    # get the kernel version from the top Makefile in the kernel source tree
    topmakefile = kernelsource + "/Makefile"
    if not os.path.exists(topmakefile):
        return None
    with open(topmakefile, 'r') as makefile:
        lines = makefile.readlines()

    kernelversion = ""
    for s in lines:
        m = re.match("VERSION = (\d+)", s)
        if m:
            kernelversion = kernelversion + m.group(1)
        m = re.match("PATCHLEVEL = (\d+)", s)
        if m:
            kernelversion = kernelversion + '.' + m.group(1)
        m = re.match("SUBLEVEL = (\d+)", s)
        if m:
            kernelversion = kernelversion + '.' + m.group(1)
        m = re.match("EXTRAVERSION = (\d+)", s)
        if m:
            kernelversion = kernelversion + '.' + m.group(1)
    return kernelversion

# Return passed value if the kernel version is equal or above the one provided
def configure_from_version(version, passvalue, failvalue, d):
    kv = get_kernel_version(d)
    if kv is None:
        return failvalue
    kv_major = kv.split('.')[0]
    kv_minor = kv.split('.')[1]
    major =  version.split('.')[0]
    minor = version.split('.')[1]
    if int(kv_major) > int(major):
        return passvalue
    elif int(kv_major) == int(major):
        if int(kv_minor) >= int(minor):
            return passvalue
    return failvalue

PANDO_CONFIGS ?= " \
    ad5446 \
    balena \
    brcmfmac \
    cdc-acm \
    ralink \
    rtl8192cu \
    r8188eu \
    systemd \
    leds-gpio \
    proc-config \
    no-logo \
    hid-multitouch \
    ip6tables_nat \
    ip_set \
    seccomp \
    wd-nowayout \
    wd \
    xtables \
    audit \
    governor \
    mbim \
    qmi \
    misc \
    panic \
    reduce-size \
    security \
    usb-serial \
    zram \
    ${BALENA_STORAGE} \
    fatfs \
    nfsfs \
    apple_hfs \
    nf_tables \
    dummy \
    uinput \
    no-debug-info \
    uprobes \
    task-accounting \
    ipv6_mroute \
    disable_hung_panic \
    ${RAID} \
    no_gcc_plugins \
    ${FIRMWARE_COMPRESS} \
    ${MODULE_COMPRESS} \
    ${WIREGUARD} \
    "

#
# Balena specific kernel configuration
# Keep these updated with
# https://raw.githubusercontent.com/balena-os/balena-engine/master/contrib/check-config.sh
#
# False negatives:
#
# CONFIG_NF_NAT_NEEDED was removed in v5.2 (sha1 f319ca6557c10a711facc4dd60197470796d3ec1)
# CONFIG_NF_NAT_IPV4 was merged with NF_NAT in v5.1 (sha1 3bf195ae6037e310d693ff3313401cfaf1261b71)
#
PANDO_CONFIGS_DEPS[balena] ?= " \
    CONFIG_IP_NF_NAT=y \
    CONFIG_IPV6=y \
    CONFIG_IP_NF_IPTABLES=y \
    CONFIG_NF_CONNTRACK=y \
    CONFIG_NF_CONNTRACK_IPV4=y \
    CONFIG_NETFILTER=y \
    CONFIG_IP_VS=y \
    CONFIG_NETFILTER_XT_MATCH_IPVS=y \
    CONFIG_NF_NAT_NEEDED=y \
    CONFIG_DEVPTS_MULTIPLE_INSTANCES=y \
    CONFIG_NF_NAT_IPV4=y \
    "
PANDO_CONFIGS[balena] ?= " \
    CONFIG_ADVISE_SYSCALLS=y \
    CONFIG_MEMCG=y \
    CONFIG_NAMESPACES=y \
    CONFIG_NET_NS=y \
    CONFIG_PID_NS=y \
    CONFIG_IPC_NS=y \
    CONFIG_UTS_NS=y \
    CONFIG_USER_NS=y \
    CONFIG_CGROUPS=y \
    CONFIG_CGROUP_CPUACCT=y \
    CONFIG_CGROUP_DEVICE=y \
    CONFIG_CGROUP_FREEZER=y \
    CONFIG_CGROUP_SCHED=y \
    CONFIG_CPUSETS=y \
    CONFIG_MACVLAN=y \
    CONFIG_VETH=y \
    CONFIG_BRIDGE=y \
    CONFIG_IP_NF_FILTER=y \
    CONFIG_IP6_NF_FILTER=m \
    CONFIG_IP_NF_TARGET_REJECT=m \
    CONFIG_IP6_NF_TARGET_REJECT=m \
    CONFIG_IP_NF_TARGET_MASQUERADE=m \
    CONFIG_IP6_NF_TARGET_MASQUERADE=m \
    CONFIG_NETFILTER_XT_MATCH_ADDRTYPE=y \
    CONFIG_NETFILTER_XT_MATCH_CONNTRACK=y \
    CONFIG_NETFILTER_XT_MATCH_COMMENT=y \
    CONFIG_NF_NAT=y \
    CONFIG_POSIX_MQUEUE=y \
    CONFIG_TUN=y \
    CONFIG_EXT4_FS=y \
    CONFIG_EXT4_FS_POSIX_ACL=y \
    CONFIG_EXT4_FS_SECURITY=y \
    CONFIG_KEYS=y \
    CONFIG_MEMCG=y \
    CONFIG_OVERLAY_FS=y \
    "


PANDO_CONFIGS:append = " ${@configure_from_version("6.1", "", " memcfg_swap", d)}"
PANDO_CONFIGS[memcfg_swap] = "CONFIG_MEMCG_SWAP=y"
FIRMWARE_COMPRESS = "${@configure_from_version("5.3", "firmware_compress", "", d)}"
PANDO_CONFIGS[firmware_compress] = " \
    CONFIG_FW_LOADER_COMPRESS=y \
"

MODULE_COMPRESS = "${@configure_from_version("5.13", "module_compress", "", d)}"
PANDO_CONFIGS[module_compress] = " \
    CONFIG_MODULE_COMPRESS_ZSTD=y \
"

WIREGUARD = "${@configure_from_version("5.10", "wireguard", "", d)}"
PANDO_CONFIGS[wireguard] = " \
    CONFIG_WIREGUARD=m \
"

KERNEL_ZSTD = "${@configure_from_version("5.9", "kernel_zstd", "", d)}"
PANDO_CONFIGS:append = "${@bb.utils.contains('MACHINE_FEATURES','efi'," ${KERNEL_ZSTD}",'',d)}"
PANDO_CONFIGS[kernel_zstd] = " \
    CONFIG_KERNEL_ZSTD=y \
    CONFIG_CRYPTO_ZSTD=y \
"

PANDO_CONFIGS[apple_hfs] = " \
    CONFIG_HFS_FS=m \
    CONFIG_HFSPLUS_FS=m \
    "

PANDO_CONFIGS[nfsfs] = " \
    CONFIG_NFS_FS=m \
    CONFIG_NFS_V2=m \
    CONFIG_NFS_V3=m \
    CONFIG_NFS_V4=m \
    CONFIG_NFSD=m \
    CONFIG_NFSD_V4=y \
"

PANDO_CONFIGS:append = " ${@configure_from_version("5.18", "", " nfsd_v3", d)}"
PANDO_CONFIGS[nfsd_v3] = "CONFIG_NFSD_V3=y"
#
# systemd specific kernel configuration options
# see https://github.com/systemd/systemd/blob/master/README for an up-to-date list
#
PANDO_CONFIGS_DEPS[systemd] ?= " \
    CONFIG_DMIID=y \
    CONFIG_DEVPTS_MULTIPLE_INSTANCES=y \
    "
PANDO_CONFIGS[systemd] ?= " \
    CONFIG_DEVTMPFS=y \
    CONFIG_CGROUPS=y \
    CONFIG_INOTIFY_USER=y \
    CONFIG_SIGNALFD=y \
    CONFIG_TIMERFD=y \
    CONFIG_EPOLL=y \
    CONFIG_NET=y \
    CONFIG_SYSFS=y \
    CONFIG_PROC_FS=y \
    CONFIG_FHANDLE=y \
    CONFIG_SYSFS_DEPRECATED=n \
    CONFIG_UEVENT_HELPER=n \
    CONFIG_FW_LOADER_USER_HELPER=n \
    CONFIG_FW_LOADER_USER_HELPER_FALLBACK=n \
    CONFIG_BLK_DEV_BSG=y \
    CONFIG_NET_NS=y \
    CONFIG_IPV6=y \
    CONFIG_AUTOFS4_FS=y \
    CONFIG_TMPFS_POSIX_ACL=y \
    CONFIG_TMPFS_XATTR=y \
    CONFIG_SECCOMP=y \
    CONFIG_CGROUP_SCHED=y \
    CONFIG_FAIR_GROUP_SCHED=y \
    CONFIG_CFS_BANDWIDTH=y"

#
# We use an out-of-tree kernel module for RTL8192CU WiFi devices
# Deactivate in-tree driver and add all the dependencies of the out-of-the tree
# one
#
PANDO_CONFIGS[rtl8192cu] ?= "\
    CONFIG_RTL8192CU=n \
    CONFIG_HOSTAP=m \
    CONFIG_WIRELESS=y \
    CONFIG_USB=y \
    CONFIG_MAC80211=m \
    CONFIG_CFG80211=m \
    CONFIG_CFG80211_WEXT=y \
    CONFIG_WIRELESS_EXT=y \
    CONFIG_WEXT_PRIV=y \
    "

# Activate R8188EU driver
PANDO_CONFIGS_DEPS[r8188eu] ?= "\
    CONFIG_STAGING=y \
    "
PANDO_CONFIGS[r8188eu] ?= "\
    CONFIG_R8188EU=m \
    "

PANDO_CONFIGS:append = " ${@configure_from_version("5.16", "", " 88eu_ap_mode", d)}"
PANDO_CONFIGS[88eu_ap_mode] = "CONFIG_88EU_AP_MODE=y"

# rt53xx wireless chipset family to the rt2800usb driver.
# Supported chips: RT5370 RT5572
PANDO_CONFIGS_DEPS[ralink] ?= "\
    CONFIG_CFG80211=m \
    CONFIG_MAC80211=m \
    CONFIG_RT2X00=m \
    CONFIG_RT2800USB=m \
    "
PANDO_CONFIGS[ralink] ?= "\
    CONFIG_RT2800USB_RT53XX=y \
    CONFIG_RT2800USB_RT55XX=y \
    "

#
# Official RPI WiFi adapter
# http://thepihut.com/collections/new-products/products/official-raspberry-pi-wifi-adapter
#
PANDO_CONFIGS_DEPS[brcmfmac] ?= " \
    CONFIG_CFG80211=m \
    CONFIG_BRCMFMAC_USB=y \
    "
PANDO_CONFIGS[brcmfmac] ?= " \
    CONFIG_BRCMFMAC=m \
    "

#
# Most of the resin supported boards have user controllable LEDs
#
PANDO_CONFIGS_DEPS[leds-gpio] ?= " \
    CONFIG_NEW_LEDS=y \
    CONFIG_LEDS_CLASS=y \
    CONFIG_GPIOLIB=y \
    "
PANDO_CONFIGS[leds-gpio] ?= " \
    CONFIG_LEDS_GPIO=y \
    "

#
# Expose kernel config via procfs
#
PANDO_CONFIGS_DEPS[proc-config] ?= " \
    CONFIG_IKCONFIG=y \
    CONFIG_PROC_FS=y \
    CONFIG_EXPERT=y \
    "
PANDO_CONFIGS[proc-config] ?= " \
    CONFIG_IKCONFIG_PROC=y \
    "

#
# For a flawless boot experience deactivate logo - we have splash screen providers
#
PANDO_CONFIGS[no-logo] ?= " \
    CONFIG_LOGO=n \
    "

#
# Compress Kernel modules
#
PANDO_CONFIGS[compress-kmodules] ?= " \
    CONFIG_MODULE_COMPRESS=y \
    CONFIG_MODULE_COMPRESS_GZIP=y \
    "

#
# Do not include debugging info in kernel and modules
#
PANDO_CONFIGS[no-debug-info] ?= " \
    CONFIG_DEBUG_INFO=n \
    "

#
# Support for touchscreens using generic multitouch driver
#
PANDO_CONFIGS_DEPS[hid-multitouch] ?= " \
    CONFIG_INPUT=y \
    CONFIG_HID=y \
    "
PANDO_CONFIGS[hid-multitouch] ?= " \
    CONFIG_HID_MULTITOUCH=m \
    CONFIG_HIDRAW=y \
    "

PANDO_CONFIGS[ip_set] = " \
    CONFIG_IP_SET=m \
    CONFIG_IP_SET_BITMAP_IP=m \
    CONFIG_IP_SET_BITMAP_IPMAC=m \
    CONFIG_IP_SET_BITMAP_PORT=m \
    CONFIG_IP_SET_HASH_IP=m \
    CONFIG_IP_SET_HASH_IPPORT=m \
    CONFIG_IP_SET_HASH_IPPORTIP=m \
    CONFIG_IP_SET_HASH_IPPORTNET=m \
    CONFIG_IP_SET_HASH_NET=m \
    CONFIG_IP_SET_HASH_NETIFACE=m \
    CONFIG_IP_SET_HASH_NETPORT=m \
    CONFIG_IP_SET_LIST_SET=m \
    "

# enable ip6table_nat and nf_nat_ipv6 as modules (we only add CONFIG_IP6_NF_NAT here as that will also bring in CONFIG_NF_NAT_IPV6)

PANDO_CONFIGS_DEPS[ip6tables_nat] = " \
    CONFIG_NF_CONNTRACK_IPV6=m \
    CONFIG_IP6_NF_IPTABLES=m \
    "
PANDO_CONFIGS[ip6tables_nat] = " \
    CONFIG_IP6_NF_NAT=m \
    "

PANDO_CONFIGS[ipv6_mroute] = " \
    CONFIG_IPV6_MROUTE=y \
    CONFIG_IPV6_MROUTE_MULTIPLE_TABLES=y \
    "

PANDO_CONFIGS[seccomp] = " \
    CONFIG_SECCOMP=y \
    "

#
# The flasher images relies on shutdown at the end of the flashing process.
# Having no way out we might end up rebooting the board before shutdown because
# systemd is disabling watchdog before killing the other processes which might
# take more than the watchdog timer.
#
PANDO_CONFIGS[wd-nowayout] = " \
    CONFIG_WATCHDOG_NOWAYOUT=n \
    "

PANDO_CONFIGS[wd] = " \
    CONFIG_WATCHDOG_SYSFS=y \
    "

PANDO_CONFIGS_DEPS[xtables] = " \
    CONFIG_NETFILTER_ADVANCED=m \
    CONFIG_IP_SET=m \
    "

PANDO_CONFIGS[xtables] = " \
    CONFIG_NETFILTER_XT_SET=m \
    "

# Deactivate the audit susbsystem and the audit syscall
PANDO_CONFIGS_DEPS[audit] = " \
    CONFIG_HAVE_AUDITSYSCALL=n \
    "

PANDO_CONFIGS[audit] = " \
    CONFIG_AUDIT=n \
    CONFIG_AUDITSYSCALL=n \
    "

PANDO_CONFIGS_DEPS[governor] ?= " \
    CONFIG_CPU_FREQ_DEFAULT_GOV_ONDEMAND=y \
    "

# support for mbim cell modems
PANDO_CONFIGS_DEPS[mbim] = " \
    CONFIG_USB_NET_DRIVERS=m \
    CONFIG_USB_USBNET=m \
"

PANDO_CONFIGS[mbim] = " \
    CONFIG_USB_NET_CDC_MBIM=m \
    "

# support for qmi cell modems
PANDO_CONFIGS_DEPS[qmi] = " \
    CONFIG_USB_NET_DRIVERS=m \
    CONFIG_USB_USBNET=m \
"

PANDO_CONFIGS[qmi] = " \
    CONFIG_USB_NET_QMI_WWAN=m \
    "

# various other configurations
PANDO_CONFIGS[misc] = " \
    CONFIG_USB_SERIAL_CP210X=m \
    CONFIG_DEBUG_FS=y \
    "

# IP_NF_TARGET_LOG is replaced by NETFILTER_XT_TARGET_LOG from v3.10 (see sha1 6939c33a757bd006c5e0b8b5fd429fc587a4d0f4)
# NF_NAT_REDIRECT is a dependency for other modules so it is selected automatically
PANDO_CONFIGS_DEPS[misc] = " \
    CONFIG_IP_NF_TARGET_LOG=m \
    CONFIG_NETFILTER_XT_TARGET_LOG=m \
    CONFIG_NF_NAT_REDIRECT=m \
"

# Reset on oops
PANDO_CONFIGS[panic] = " \
    CONFIG_PANIC_ON_OOPS=y \
    CONFIG_PANIC_TIMEOUT=1 \
"

## configs needed for our usage of redsocks
#PANDO_CONFIGS[redsocks] = " \
#    CONFIG_NETFILTER_ADVANCED=y \
#    CONFIG_NETFILTER_XT_MATCH_OWNER=m \
#    CONFIG_NETFILTER_XT_TARGET_REDIRECT=m \
#    "

# disable some large and commonly enabled configs to reduce image size
PANDO_CONFIGS[reduce-size] = " \
    CONFIG_OCFS2_FS=n \
    CONFIG_GFS2_FS=n \
    CONFIG_REISERFS_FS=n \
    CONFIG_NTFS_FS=n \
    CONFIG_JFS_FS=n \
    CONFIG_HFS_FS=n \
    CONFIG_HFSPLUS_FS=n \
    CONFIG_UDF_FS=n \
    CONFIG_BLK_DEV_DRBD=n \
    CONFIG_XFS_FS=n \
    "

# security features
# From v4.18 these are renamed and are automatically selected with the architecture (sha1 d148eac0e70f06485dbd4cce6ed01cb07c650cec)
PANDO_CONFIGS_DEPS[security] = " \
    CONFIG_CC_STACKPROTECTOR=y \
    CONFIG_CC_STACKPROTECTOR_STRONG=y \
    "

# zram provides a compressed in-memory swap device
PANDO_CONFIGS[zram] = " \
    CONFIG_ZSMALLOC=y \
    CONFIG_ZRAM=y \
    CONFIG_CRYPTO=y \
    CONFIG_CRYPTO_LZ4=y \
    "

# Kernel versions between 4.0 and 4.9
# need this for lz4 support
PANDO_CONFIGS_DEPS[zram] = " \
    CONFIG_ZRAM_LZ4_COMPRESS=y \
"

# USB Modem (CDC ACM) support
PANDO_CONFIGS[cdc-acm] = " \
    CONFIG_USB_ACM=m \
    "

# USB serial device drivers
PANDO_CONFIGS_DEPS[usb-serial] = " \
    CONFIG_USB_SERIAL_WWAN=m \
    "
PANDO_CONFIGS[usb-serial] = " \
    CONFIG_USB_SERIAL=y \
    CONFIG_USB_SERIAL_GENERIC=y \
    CONFIG_USB_SERIAL_OPTION=m \
    CONFIG_USB_SERIAL_QUALCOMM=m \
    CONFIG_USB_SERIAL_CH341=m \
    CONFIG_USB_SERIAL_FTDI_SIO=m \
    CONFIG_USB_SERIAL_PL2303=m \
    "

PANDO_CONFIGS[fatfs] = " \
    CONFIG_MSDOS_FS=y \
    CONFIG_VFAT_FS=y \
    CONFIG_NLS_ASCII=y \
    CONFIG_NLS_CODEPAGE_437=y \
    "

PANDO_CONFIGS[nf_tables] = " \
    CONFIG_NF_TABLES=m \
    CONFIG_NF_TABLES_INET=y \
    CONFIG_NF_TABLES_NETDEV=y \
    CONFIG_NFT_NUMGEN=m \
    CONFIG_NFT_CT=m \
    CONFIG_NFT_CONNLIMIT=m \
    CONFIG_NFT_LOG=m \
    CONFIG_NFT_LIMIT=m \
    CONFIG_NFT_MASQ=m \
    CONFIG_NFT_REDIR=m \
    CONFIG_NFT_NAT=m \
    CONFIG_NFT_TUNNEL=m \
    CONFIG_NFT_OBJREF=m \
    CONFIG_NFT_QUOTA=m \
    CONFIG_NFT_REJECT=m \
    CONFIG_NFT_REJECT_INET=m \
    CONFIG_NFT_COMPAT=m \
    CONFIG_NFT_HASH=m \
    CONFIG_NFT_FIB=m \
    CONFIG_NFT_FIB_INET=m \
    CONFIG_NFT_SOCKET=m \
    CONFIG_NFT_OSF=m \
    CONFIG_NFT_TPROXY=m \
    CONFIG_NF_DUP_NETDEV=m \
    CONFIG_NFT_DUP_NETDEV=m \
    CONFIG_NFT_FWD_NETDEV=m \
    CONFIG_NFT_FIB_NETDEV=m \
    CONFIG_NF_SOCKET_IPV4=m \
    CONFIG_NF_TPROXY_IPV4=m \
    CONFIG_NF_TABLES_IPV4=y \
    CONFIG_NFT_REJECT_IPV4=m \
    CONFIG_NFT_DUP_IPV4=m \
    CONFIG_NFT_FIB_IPV4=m \
    CONFIG_NF_TABLES_ARP=y \
    CONFIG_NF_DUP_IPV4=m \
    CONFIG_NF_SOCKET_IPV6=m \
    CONFIG_NF_TPROXY_IPV6=m \
    CONFIG_NF_TABLES_IPV6=y \
    CONFIG_NFT_REJECT_IPV6=m \
    CONFIG_NFT_DUP_IPV6=m \
    CONFIG_NFT_FIB_IPV6=m \
    CONFIG_NF_DUP_IPV6=m \
    "
PANDO_CONFIGS:append = " ${@configure_from_version("5.10", "", " nf_tables_set", d)}"
PANDO_CONFIGS[nf_tables_set] = "CONFIG_NF_TABLES_SET=m"

PANDO_CONFIGS:append = " ${@configure_from_version("5.17", "", " nft_counter", d)}"
PANDO_CONFIGS[nft_counter] = "CONFIG_NFT_COUNTER=m"

PANDO_CONFIGS[task-accounting] = " \
    CONFIG_TASKSTATS=y \
    CONFIG_TASK_DELAY_ACCT=y \
    CONFIG_TASK_IO_ACCOUNTING=y \
    "

# This adds support for creating
# dummy net devices
PANDO_CONFIGS[dummy] = " \
    CONFIG_DUMMY=m \
    "

# enable uinput kernel module
PANDO_CONFIGS[uinput] = " \
    CONFIG_INPUT_UINPUT=m \
    "

# enable Analog Devices AD5446 and similar single channel DACs driver
PANDO_CONFIGS[ad5446] = " \
    CONFIG_AD5446=m \
"

PANDO_CONFIGS_DEPS[uprobes] = " \
    CONFIG_FTRACE=y \
"

# enable user space probes support
PANDO_CONFIGS[uprobes] = " \
    CONFIG_UPROBE_EVENTS=y \
"

PANDO_CONFIGS[disable_hung_panic] = " \
    CONFIG_BOOTPARAM_HUNG_TASK_PANIC=n \
    "

PANDO_CONFIGS[no_gcc_plugins] = " \
    CONFIG_GCC_PLUGINS=n \
"

# enable rootfs on RAID1
RAID = "${@bb.utils.contains('MACHINE_FEATURES','raid','mdraid','',d)}"
PANDO_CONFIGS[mdraid] = " \
    CONFIG_MD=y \
    CONFIG_BLK_DEV_MD=y \
    CONFIG_MD_RAID1=y \
    CONFIG_MD_AUTODETECT=y \
"

# Enable dmcrypt/LUKS
PANDO_CONFIGS:append = "${@oe.utils.conditional('SIGN_API','','',' dmcrypt',d)}"
PANDO_CONFIGS_DEPS[dmcrypt] = " \
    CONFIG_BLK_DEV_DM=y \
"
PANDO_CONFIGS[dmcrypt] = " \
    CONFIG_CRYPTO_XTS=y \
    CONFIG_DM_CRYPT=y \
"

PANDO_CONFIGS[kexec] = " \
    CONFIG_KEXEC=y \
    CONFIG_KEXEC_FILE=y \
    CONFIG_KEXEC_SIG=y \
"
PANDO_CONFIGS:append = "${@bb.utils.contains('MACHINE_FEATURES','efi',' kexec','',d)}"

PANDO_CONFIGS:append = "${@oe.utils.conditional('SIGN_API','','',' secureboot',d)}"
PANDO_CONFIGS_DEPS[secureboot] = " \
    CONFIG_INTEGRITY_SIGNATURE=y \
    CONFIG_INTEGRITY_ASYMMETRIC_KEYS=y \
    CONFIG_SYSTEM_BLACKLIST_KEYRING=y \
"
PANDO_CONFIGS[secureboot] = " \
    CONFIG_INTEGRITY_PLATFORM_KEYRING=y \
    CONFIG_MODULE_SIG=y \
    CONFIG_MODULE_SIG_ALL=y \
    CONFIG_MODULE_SIG_SHA512=y \
    CONFIG_SECURITY_LOCKDOWN_LSM=y \
    CONFIG_SECURITY_LOCKDOWN_LSM_EARLY=y \
    CONFIG_SYSTEM_TRUSTED_KEYS="certs/kmod.crt" \
"
PANDO_CONFIGS[efi-secureboot] = " \
    CONFIG_LOAD_UEFI_KEYS=y \
    CONFIG_KEXEC_SIG_FORCE=y \
    CONFIG_KEXEC_BZIMAGE_VERIFY_SIG=y \
"
PANDO_CONFIGS:append = "${@bb.utils.contains('MACHINE_FEATURES','efi',' efi-secureboot','',d)}"

###########
# HELPERS #
###########

# Returns a set of all activated configs in srcpath
def getKernelSetConfigs(srcpath):
    import os.path
    allSetConfigs = set()
    if os.path.isfile(srcpath):
        with open(srcpath, 'r') as f:
            lines = f.readlines();
            for line in lines:
                if not line.startswith('#'):
                    allSetConfigs.add(line.strip())
    return allSetConfigs

# Appends a line to a file
def appendLineToFile (filepath, line):
    import os.path
    if os.path.isfile(filepath):
        with open(filepath, 'a') as f:
            f.write(line.strip()+'\n')

#########
# TASKS #
#########

#
# Inject resin configs
#
python do_kernel_resin_injectconfig() {
    activatedflags = d.getVar("PANDO_CONFIGS", True).split()
    if not activatedflags:
        bb.warn("No resin specific kernel configuration flags selected.")
        return

    # This is after configure so we are sure there is a .config file
    configfilepath = d.getVar("B", True) + '/.config'

    # Configs added with flaged dictionaries
    configs = d.getVarFlags("PANDO_CONFIGS") or {}
    configsdep = d.getVarFlags("PANDO_CONFIGS_DEPS") or {}

    for activatedflag in activatedflags:
        bb.note("Configure kernel for %s." %activatedflag)

        # Address dependencies
        if activatedflag in configsdep:
            bb.note("Configure kernel for %s [configs dependencies]."
                %activatedflag)
            for c in configsdep[activatedflag].split():
                appendLineToFile(filepath=configfilepath, line=c)
        else:
            bb.note("No dependent configs for %s." %activatedflag)

        # Address configs
        if activatedflag in configs:
            bb.note("Configure kernel for %s [configs]." %activatedflag)
            for c in configs[activatedflag].split():
                appendLineToFile(filepath=configfilepath, line=c)
        else:
            bb.note("No configs for %s." %activatedflag)

    # Configs added with resin defconfig
    resinDefconfig = d.getVar("PANDO_DEFCONFIG_NAME", True)
    resinDefconfigPath = d.getVar("WORKDIR", True) + '/' +  resinDefconfig
    resinDefconfigs = getKernelSetConfigs(resinDefconfigPath)
    if resinDefconfigs:
        bb.note("Configure kernel from %s." %resinDefconfig)
        for c in resinDefconfigs:
            appendLineToFile(filepath=configfilepath, line=c)
    else:
        bb.note("No kernel configuration found from %s." %resinDefconfig)
}
addtask kernel_resin_injectconfig after do_configure before do_compile
do_kernel_resin_injectconfig[vardeps] += "PANDO_CONFIGS PANDO_CONFIGS_DEPS"
do_kernel_resin_injectconfig[deptask] += "do_configure"
do_kernel_resin_injectconfig[dirs] += "${WORKDIR} ${B}"

#
# Reconfigure kernel after we inject resin configs
#
do_kernel_resin_reconfigure() {
    ${KERNEL_CONFIG_COMMAND}
}
addtask kernel_resin_reconfigure after do_kernel_resin_injectconfig before do_compile
do_kernel_resin_reconfigure[vardeps] += "PANDO_CONFIGS PANDO_CONFIGS_DEPS"
do_kernel_resin_reconfigure[deptask] += "do_kernel_resin_injectconfig"
do_kernel_resin_reconfigure[dirs] += "${B}"

#
# Check that all the wanted configs got activated in kernel
#
python do_kernel_resin_checkconfig() {
    activatedflags = d.getVar("PANDO_CONFIGS", True).split()
    if not activatedflags:
        bb.warn("No resin specific kernel configuration flags selected.")
        return

    configfilepath = d.getVar("B", True) + '/.config'
    allSetKernelConfigs = getKernelSetConfigs(configfilepath)
    configs = d.getVarFlags("PANDO_CONFIGS") or {}
    firmware_compression = d.getVar('FIRMWARE_COMPRESSION', True)

    if firmware_compression == "1" and \
        'firmware_compress' not in activatedflags:
            bb.fatal("Firmware compression is enabled for this device but" \
                " the kernel does not have support for it")

    for activatedflag in activatedflags:
        if activatedflag in configs:
            bb.note("Checking kernel configs for %s." %activatedflag)
            wantedConfigs = set(configs[activatedflag].split())
            configured = wantedConfigs.intersection(allSetKernelConfigs)
            notconfigured = wantedConfigs.difference(configured)

            for config in notconfigured:
                if not config.endswith('=n'):
                    bb.warn("Checking for %s in the kernel configs failed for %s."
                        % (config, activatedflag))

    # Check configs added with resin defconfig
    resinDefconfig = d.getVar("PANDO_DEFCONFIG_NAME", True)
    resinDefconfigPath = d.getVar("WORKDIR", True) + '/' +  resinDefconfig
    wantedConfigs = getKernelSetConfigs(resinDefconfigPath)
    if wantedConfigs:
        configured = wantedConfigs.intersection(allSetConfigs)
        notconfigured = wantedConfigs.difference(configured)
        for config in notconfigured:
            if not config.endswith('=n'):
                bb.warn("Checking for %s in the resin kernel configs failed from %s."
                    % (config, resinDefconfig))
}
addtask kernel_resin_checkconfig after do_kernel_resin_reconfigure before do_compile
do_kernel_resin_checkconfig[vardeps] += "PANDO_CONFIGS PANDO_CONFIGS_DEPS"
do_kernel_resin_checkconfig[deptask] += "do_kernel_resin_reconfigure"
do_kernel_resin_checkconfig[dirs] += "${WORKDIR} ${B}"

do_configure:append () {
    if [ -f "${DEPLOY_DIR_IMAGE}/pando-keys/kmod.crt" ]; then
        install -d certs
        install -m 0655 "${DEPLOY_DIR_IMAGE}/pando-keys/kmod.crt" "certs/"
    fi

}
do_configure[depends] += "${@oe.utils.conditional('SIGN_API','','',' pando-keys:do_deploy',d)}"
# Force compile to depend on the last resin task in the chain
do_compile[deptask] += "do_kernel_resin_checkconfig"
# Remove kernel module certificates generated during previous build
do_configure[cleandirs] += "${@oe.utils.conditional('SIGN_API','','','${B}/certs',d)}"
do_configure[vardeps] += " \
    SIGN_API \
    "

# Because we chain signatures here, the signed artifact is different for each
# and defined in :prepend for each task
SIGNING_ARTIFACTS_BASE = "${B}/${KERNEL_OUTPUT_DIR}/${KERNEL_IMAGETYPE}.initramfs"
addtask sign_efi before do_deploy after do_bundle_initramfs
addtask sign_gpg before do_deploy after do_sign_efi

do_sign_efi:prepend() {
    SIGNING_ARTIFACTS="${SIGNING_ARTIFACTS_BASE}"
}

do_sign_gpg:prepend () {
    SIGNING_ARTIFACTS=""
    for SIGNING_ARTIFACT in ${SIGNING_ARTIFACTS_BASE}; do
        SIGNING_ARTIFACTS="${SIGNING_ARTIFACTS} ${SIGNING_ARTIFACT}.signed"
    done
}

do_sign_gpg:append () {
    for SIGNING_ARTIFACT in ${SIGNING_ARTIFACTS_BASE}; do
        mv "${SIGNING_ARTIFACT}.signed.sig" "${SIGNING_ARTIFACT}.sig"
    done
}

# Parallel builds sharing state cache will mismatch singed kernel and modules
# Avoid using cache for signed kernel modules to avoid this
## do_compile_kernelmodules[nostamp] = "${@oe.utils.conditional('SIGN_API','','','1',d)}"

do_deploy:prepend () {
    SIGNING_ARTIFACTS="${SIGNING_ARTIFACTS_BASE}"
}

# copy to deploy dir latest .config and Module.symvers (after kernel modules have been built)
do_deploy:append () {
    install -m 0644 ${D}/boot/Module.symvers-* ${DEPLOYDIR}/Module.symvers
    install -m 0644 ${D}/boot/config-* ${DEPLOYDIR}/.config
}
