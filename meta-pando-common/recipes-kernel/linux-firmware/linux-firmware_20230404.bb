SUMMARY = "Firmware files for use with Linux kernel"
HOMEPAGE = "https://www.kernel.org/"
DESCRIPTION = "Linux firmware is a package distributed alongside the Linux kernel \
that contains firmware binary blobs necessary for partial or full functionality \
of certain hardware devices."
SECTION = "kernel"

LICENSE = "\
    Firmware-Abilis \
    & Firmware-adsp_sst \
    & Firmware-agere \
    & Firmware-amdgpu \
    & Firmware-amd-ucode \
    & Firmware-amlogic_vdec \
    & Firmware-atheros_firmware \
    & Firmware-atmel \
    & Firmware-broadcom_bcm43xx \
    & Firmware-ca0132 \
    & Firmware-cavium \
    & Firmware-chelsio_firmware \
    & Firmware-cw1200 \
    & Firmware-cypress \
    & Firmware-dib0700 \
    & Firmware-e100 \
    & Firmware-ene_firmware \
    & Firmware-fw_sst_0f28 \
    & Firmware-go7007 \
    & Firmware-GPLv2 \
    & Firmware-hfi1_firmware \
    & Firmware-i915 \
    & Firmware-ibt_firmware \
    & Firmware-ice \
    & Firmware-it913x \
    & Firmware-iwlwifi_firmware \
    & Firmware-IntcSST2 \
    & Firmware-kaweth \
    & Firmware-Lontium \
    & Firmware-Marvell \
    & Firmware-moxa \
    & Firmware-myri10ge_firmware \
    & Firmware-netronome \
    & Firmware-nvidia \
    & Firmware-OLPC \
    & Firmware-ath9k-htc \
    & Firmware-phanfw \
    & Firmware-qat \
    & Firmware-qcom \
    & Firmware-qcom-yamato \
    & Firmware-qla1280 \
    & Firmware-qla2xxx \
    & Firmware-qualcommAthos_ar3k \
    & Firmware-qualcommAthos_ath10k \
    & Firmware-r8a779x_usb3 \
    & Firmware-radeon \
    & Firmware-ralink_a_mediatek_company_firmware \
    & Firmware-ralink-firmware \
    & Firmware-rtlwifi_firmware \
    & Firmware-imx-sdma_firmware \
    & Firmware-siano \
    & Firmware-ti-connectivity \
    & Firmware-ti-keystone \
    & Firmware-ueagle-atm4-firmware \
    & Firmware-via_vt6656 \
    & Firmware-wl1251 \
    & Firmware-xc4000 \
    & Firmware-xc5000 \
    & Firmware-xc5000c \
    & WHENCE \
"

LIC_FILES_CHKSUM = "file://LICENCE.Abilis;md5=b5ee3f410780e56711ad48eadc22b8bc \
                    file://LICENCE.adsp_sst;md5=615c45b91a5a4a9fe046d6ab9a2df728 \
                    file://LICENCE.agere;md5=af0133de6b4a9b2522defd5f188afd31 \
                    file://LICENSE.amdgpu;md5=a2589a05ea5b6bd2b7f4f623c7e7a649 \
                    file://LICENSE.amd-ucode;md5=6ca90c57f7b248de1e25c7f68ffc4698 \
                    file://LICENSE.amlogic_vdec;md5=dc44f59bf64a81643e500ad3f39a468a \
                    file://LICENCE.atheros_firmware;md5=30a14c7823beedac9fa39c64fdd01a13 \
                    file://LICENSE.atmel;md5=aa74ac0c60595dee4d4e239107ea77a3 \
                    file://LICENCE.broadcom_bcm43xx;md5=3160c14df7228891b868060e1951dfbc \
                    file://LICENCE.ca0132;md5=209b33e66ee5be0461f13d31da392198 \
                    file://LICENCE.cadence;md5=009f46816f6956cfb75ede13d3e1cee0 \
                    file://LICENCE.cavium;md5=c37aaffb1ebe5939b2580d073a95daea \
                    file://LICENCE.chelsio_firmware;md5=819aa8c3fa453f1b258ed8d168a9d903 \
                    file://LICENCE.cw1200;md5=f0f770864e7a8444a5c5aa9d12a3a7ed \
                    file://LICENCE.cypress;md5=48cd9436c763bf873961f9ed7b5c147b \
                    file://LICENSE.dib0700;md5=f7411825c8a555a1a3e5eab9ca773431 \
                    file://LICENCE.e100;md5=ec0f84136766df159a3ae6d02acdf5a8 \
                    file://LICENCE.ene_firmware;md5=ed67f0f62f8f798130c296720b7d3921 \
                    file://LICENCE.fw_sst_0f28;md5=6353931c988ad52818ae733ac61cd293 \
                    file://LICENCE.go7007;md5=c0bb9f6aaaba55b0529ee9b30aa66beb \
                    file://GPL-2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://LICENSE.hfi1_firmware;md5=5e7b6e586ce7339d12689e49931ad444 \
                    file://LICENSE.i915;md5=2b0b2e0d20984affd4490ba2cba02570 \
                    file://LICENCE.ibt_firmware;md5=fdbee1ddfe0fb7ab0b2fcd6b454a366b \
                    file://LICENSE.ice;md5=742ab4850f2670792940e6d15c974b2f \
                    file://LICENCE.IntcSST2;md5=9e7d8bea77612d7cc7d9e9b54b623062 \
                    file://LICENCE.it913x;md5=1fbf727bfb6a949810c4dbfa7e6ce4f8 \
                    file://LICENCE.iwlwifi_firmware;md5=2ce6786e0fc11ac6e36b54bb9b799f1b \
                    file://LICENCE.kaweth;md5=b1d876e562f4b3b8d391ad8395dfe03f \
                    file://LICENSE.Lontium;md5=4ec8dc582ff7295f39e2ca6a7b0be2b6 \
                    file://LICENCE.Marvell;md5=28b6ed8bd04ba105af6e4dcd6e997772 \
                    file://LICENCE.mediatek;md5=7c1976b63217d76ce47d0a11d8a79cf2 \
                    file://LICENCE.moxa;md5=1086614767d8ccf744a923289d3d4261 \
                    file://LICENCE.myri10ge_firmware;md5=42e32fb89f6b959ca222e25ac8df8fed \
                    file://LICENCE.Netronome;md5=4add08f2577086d44447996503cddf5f \
                    file://LICENCE.nvidia;md5=4428a922ed3ba2ceec95f076a488ce07 \
                    file://LICENCE.NXP;md5=58bb8ba632cd729b9ba6183bc6aed36f \
                    file://LICENCE.OLPC;md5=5b917f9d8c061991be4f6f5f108719cd \
                    file://LICENCE.open-ath9k-htc-firmware;md5=1b33c9f4d17bc4d457bdb23727046837 \
                    file://LICENCE.phanfw;md5=954dcec0e051f9409812b561ea743bfa \
                    file://LICENCE.qat_firmware;md5=72de83dfd9b87be7685ed099a39fbea4 \
                    file://LICENSE.qcom;md5=164e3362a538eb11d3ac51e8e134294b \
                    file://LICENSE.qcom_yamato;md5=d0de0eeccaf1843a850bf7a6777eec5c \
                    file://LICENCE.qla1280;md5=d6895732e622d950609093223a2c4f5d \
                    file://LICENCE.qla2xxx;md5=505855e921b75f1be4a437ad9b79dff0 \
                    file://LICENSE.QualcommAtheros_ar3k;md5=b5fe244fb2b532311de1472a3bc06da5 \
                    file://LICENSE.QualcommAtheros_ath10k;md5=cb42b686ee5f5cb890275e4321db60a8 \
                    file://LICENCE.r8a779x_usb3;md5=4c1671656153025d7076105a5da7e498 \
                    file://LICENSE.radeon;md5=68ec28bacb3613200bca44f404c69b16 \
                    file://LICENCE.ralink_a_mediatek_company_firmware;md5=728f1a85fd53fd67fa8d7afb080bc435 \
                    file://LICENCE.ralink-firmware.txt;md5=ab2c269277c45476fb449673911a2dfd \
                    file://LICENCE.rtlwifi_firmware.txt;md5=00d06cfd3eddd5a2698948ead2ad54a5 \
                    file://LICENSE.sdma_firmware;md5=51e8c19ecc2270f4b8ea30341ad63ce9 \
                    file://LICENCE.siano;md5=4556c1bf830067f12ca151ad953ec2a5 \
                    file://LICENCE.ti-connectivity;md5=c5e02be633f1499c109d1652514d85ec \
                    file://LICENCE.ti-keystone;md5=3a86335d32864b0bef996bee26cc0f2c \
                    file://LICENCE.ueagle-atm4-firmware;md5=4ed7ea6b507ccc583b9d594417714118 \
                    file://LICENCE.via_vt6656;md5=e4159694cba42d4377a912e78a6e850f \
                    file://LICENCE.wl1251;md5=ad3f81922bb9e197014bb187289d3b5b \
                    file://LICENCE.xc4000;md5=0ff51d2dc49fce04814c9155081092f0 \
                    file://LICENCE.xc5000;md5=1e170c13175323c32c7f4d0998d53f66 \
                    file://LICENCE.xc5000c;md5=12b02efa3049db65d524aeb418dd87ca \
                    file://WHENCE;md5=${WHENCE_CHKSUM} \
                    "
# WHENCE checksum is defined separately to ease overriding it if
# class-devupstream is selected.
WHENCE_CHKSUM  = "0782deea054d4b1b7f10c92c3a245da4"

# These are not common licenses, set NO_GENERIC_LICENSE for them
# so that the license files will be copied from fetched source
NO_GENERIC_LICENSE[Firmware-Abilis] = "LICENCE.Abilis"
NO_GENERIC_LICENSE[Firmware-adsp_sst] = "LICENCE.adsp_sst"
NO_GENERIC_LICENSE[Firmware-agere] = "LICENCE.agere"
NO_GENERIC_LICENSE[Firmware-amdgpu] = "LICENSE.amdgpu"
NO_GENERIC_LICENSE[Firmware-amd-ucode] = "LICENSE.amd-ucode"
NO_GENERIC_LICENSE[Firmware-amlogic_vdec] = "LICENSE.amlogic_vdec"
NO_GENERIC_LICENSE[Firmware-atheros_firmware] = "LICENCE.atheros_firmware"
NO_GENERIC_LICENSE[Firmware-atmel] = "LICENSE.atmel"
NO_GENERIC_LICENSE[Firmware-broadcom_bcm43xx] = "LICENCE.broadcom_bcm43xx"
NO_GENERIC_LICENSE[Firmware-ca0132] = "LICENCE.ca0132"
NO_GENERIC_LICENSE[Firmware-cadence] = "LICENCE.cadence"
NO_GENERIC_LICENSE[Firmware-cavium] = "LICENCE.cavium"
NO_GENERIC_LICENSE[Firmware-chelsio_firmware] = "LICENCE.chelsio_firmware"
NO_GENERIC_LICENSE[Firmware-cw1200] = "LICENCE.cw1200"
NO_GENERIC_LICENSE[Firmware-cypress] = "LICENCE.cypress"
NO_GENERIC_LICENSE[Firmware-dib0700] = "LICENSE.dib0700"
NO_GENERIC_LICENSE[Firmware-e100] = "LICENCE.e100"
NO_GENERIC_LICENSE[Firmware-ene_firmware] = "LICENCE.ene_firmware"
NO_GENERIC_LICENSE[Firmware-fw_sst_0f28] = "LICENCE.fw_sst_0f28"
NO_GENERIC_LICENSE[Firmware-go7007] = "LICENCE.go7007"
NO_GENERIC_LICENSE[Firmware-GPLv2] = "GPL-2"
NO_GENERIC_LICENSE[Firmware-hfi1_firmware] = "LICENSE.hfi1_firmware"
NO_GENERIC_LICENSE[Firmware-i915] = "LICENSE.i915"
NO_GENERIC_LICENSE[Firmware-ibt_firmware] = "LICENCE.ibt_firmware"
NO_GENERIC_LICENSE[Firmware-ice] = "LICENSE.ice"
NO_GENERIC_LICENSE[Firmware-IntcSST2] = "LICENCE.IntcSST2"
NO_GENERIC_LICENSE[Firmware-it913x] = "LICENCE.it913x"
NO_GENERIC_LICENSE[Firmware-iwlwifi_firmware] = "LICENCE.iwlwifi_firmware"
NO_GENERIC_LICENSE[Firmware-kaweth] = "LICENCE.kaweth"
NO_GENERIC_LICENSE[Firmware-Lontium] = "LICENSE.Lontium"
NO_GENERIC_LICENSE[Firmware-Marvell] = "LICENCE.Marvell"
NO_GENERIC_LICENSE[Firmware-mediatek] = "LICENCE.mediatek"
NO_GENERIC_LICENSE[Firmware-moxa] = "LICENCE.moxa"
NO_GENERIC_LICENSE[Firmware-myri10ge_firmware] = "LICENCE.myri10ge_firmware"
NO_GENERIC_LICENSE[Firmware-netronome] = "LICENCE.Netronome"
NO_GENERIC_LICENSE[Firmware-nvidia] = "LICENCE.nvidia"
NO_GENERIC_LICENSE[Firmware-OLPC] = "LICENCE.OLPC"
NO_GENERIC_LICENSE[Firmware-ath9k-htc] = "LICENCE.open-ath9k-htc-firmware"
NO_GENERIC_LICENSE[Firmware-phanfw] = "LICENCE.phanfw"
NO_GENERIC_LICENSE[Firmware-qat] = "LICENCE.qat_firmware"
NO_GENERIC_LICENSE[Firmware-qcom] = "LICENSE.qcom"
NO_GENERIC_LICENSE[Firmware-qcom-yamato] = "LICENSE.qcom_yamato"
NO_GENERIC_LICENSE[Firmware-qla1280] = "LICENCE.qla1280"
NO_GENERIC_LICENSE[Firmware-qla2xxx] = "LICENCE.qla2xxx"
NO_GENERIC_LICENSE[Firmware-qualcommAthos_ar3k] = "LICENSE.QualcommAtheros_ar3k"
NO_GENERIC_LICENSE[Firmware-qualcommAthos_ath10k] = "LICENSE.QualcommAtheros_ath10k"
NO_GENERIC_LICENSE[Firmware-r8a779x_usb3] = "LICENCE.r8a779x_usb3"
NO_GENERIC_LICENSE[Firmware-radeon] = "LICENSE.radeon"
NO_GENERIC_LICENSE[Firmware-ralink_a_mediatek_company_firmware] = "LICENCE.ralink_a_mediatek_company_firmware"
NO_GENERIC_LICENSE[Firmware-ralink-firmware] = "LICENCE.ralink-firmware.txt"
NO_GENERIC_LICENSE[Firmware-rtlwifi_firmware] = "LICENCE.rtlwifi_firmware.txt"
NO_GENERIC_LICENSE[Firmware-siano] = "LICENCE.siano"
NO_GENERIC_LICENSE[Firmware-imx-sdma_firmware] = "LICENSE.sdma_firmware"
NO_GENERIC_LICENSE[Firmware-ti-connectivity] = "LICENCE.ti-connectivity"
NO_GENERIC_LICENSE[Firmware-ti-keystone] = "LICENCE.ti-keystone"
NO_GENERIC_LICENSE[Firmware-ueagle-atm4-firmware] = "LICENCE.ueagle-atm4-firmware"
NO_GENERIC_LICENSE[Firmware-via_vt6656] = "LICENCE.via_vt6656"
NO_GENERIC_LICENSE[Firmware-wl1251] = "LICENCE.wl1251"
NO_GENERIC_LICENSE[Firmware-xc4000] = "LICENCE.xc4000"
NO_GENERIC_LICENSE[Firmware-xc5000] = "LICENCE.xc5000"
NO_GENERIC_LICENSE[Firmware-xc5000c] = "LICENCE.xc5000c"
NO_GENERIC_LICENSE[WHENCE] = "WHENCE"

PE = "1"

SRC_URI = "\
  ${KERNELORG_MIRROR}/linux/kernel/firmware/${BPN}-${PV}.tar.xz \
"

BBCLASSEXTEND = "devupstream:target"
SRC_URI:class-devupstream = "git://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git;protocol=https;branch=main"
# Pin this to the 20220509 release, override this in local.conf
SRCREV:class-devupstream ?= "b19cbdca78ab2adfd210c91be15a22568e8b8cae"

SRC_URI[sha256sum] = "c3f9ad2bb5311cce2490f37a8052f836703d6936aabd840246b6576f1f71f607"

inherit allarch

CLEANBROKEN = "1"

do_compile() {
	:
}

do_install() {
        oe_runmake 'DESTDIR=${D}' 'FIRMWAREDIR=${libdir}/firmware' install
        cp GPL-2 LICEN[CS]E.* WHENCE ${D}${libdir}/firmware/
}


PACKAGES =+ "${PN}-ralink-license ${PN}-ralink \
             ${PN}-mt7601u-license ${PN}-mt7601u \
             ${PN}-radeon-license ${PN}-radeon \
             ${PN}-amdgpu-license ${PN}-amdgpu \
             ${PN}-marvell-license ${PN}-pcie8897 ${PN}-pcie8997 \
             ${PN}-sd8686 ${PN}-sd8688 ${PN}-sd8787 ${PN}-sd8797 ${PN}-sd8801 \
             ${PN}-sd8887 ${PN}-sd8897 ${PN}-sd8997 ${PN}-usb8997 \
             ${PN}-ti-connectivity-license ${PN}-wlcommon ${PN}-wl12xx ${PN}-wl18xx \
             ${PN}-vt6656-license ${PN}-vt6656 \
             ${PN}-rs9113 ${PN}-rs9116 \
             ${PN}-rtl-license ${PN}-rtl8188 ${PN}-rtl8192cu ${PN}-rtl8192ce ${PN}-rtl8192su ${PN}-rtl8723 ${PN}-rtl8821 \
             ${PN}-rtl8761 \
             ${PN}-rtl8168 \
             ${PN}-cypress-license \
             ${PN}-broadcom-license \
             ${PN}-bcm-0bb4-0306 \
             ${PN}-bcm43143 \
             ${PN}-bcm43236b \
             ${PN}-bcm43241b0 \
             ${PN}-bcm43241b4 \
             ${PN}-bcm43241b5 \
             ${PN}-bcm43242a \
             ${PN}-bcm4329 \
             ${PN}-bcm4329-fullmac \
             ${PN}-bcm4330 \
             ${PN}-bcm4334 \
             ${PN}-bcm43340 \
             ${PN}-bcm4335 \
             ${PN}-bcm43362 \
             ${PN}-bcm4339 \
             ${PN}-bcm43430 \
             ${PN}-bcm43430a0 \
             ${PN}-bcm43455 \
             ${PN}-bcm4350 \
             ${PN}-bcm4350c2 \
             ${PN}-bcm4354 \
             ${PN}-bcm4356 \
             ${PN}-bcm4356-pcie \
             ${PN}-bcm43569 \
             ${PN}-bcm43570 \
             ${PN}-bcm4358 \
             ${PN}-bcm43602 \
             ${PN}-bcm4366b \
             ${PN}-bcm4366c \
             ${PN}-bcm4371 \
             ${PN}-bcm4373 \
             ${PN}-bcm43xx \
             ${PN}-bcm43xx-hdr \
             ${PN}-atheros-license ${PN}-ar9170 ${PN}-ath6k ${PN}-ath9k ${PN}-ath3k \
             ${PN}-gplv2-license ${PN}-carl9170 \
             ${PN}-ar3k-license ${PN}-ar3k ${PN}-ath10k-license ${PN}-ath10k ${PN}-ath11k ${PN}-qca \
             \
             ${PN}-imx-sdma-license ${PN}-imx-sdma-imx6q ${PN}-imx-sdma-imx7d \
             \
             ${PN}-iwlwifi-license ${PN}-iwlwifi \
             ${PN}-iwlwifi-135-6 \
             ${PN}-iwlwifi-3160-17 \
             ${PN}-iwlwifi-6000-4 ${PN}-iwlwifi-6000g2a-5 ${PN}-iwlwifi-6000g2a-6 \
             ${PN}-iwlwifi-6000g2b-5 ${PN}-iwlwifi-6000g2b-6 \
             ${PN}-iwlwifi-6050-5 \
             ${PN}-iwlwifi-7260 \
             ${PN}-iwlwifi-7265 \
             ${PN}-iwlwifi-7265d ${PN}-iwlwifi-8000c ${PN}-iwlwifi-8265 \
             ${PN}-iwlwifi-9000 \
             ${PN}-iwlwifi-misc \
             ${PN}-ibt-license ${PN}-ibt \
             ${PN}-ibt-11-5 ${PN}-ibt-12-16 ${PN}-ibt-hw-37-7 ${PN}-ibt-hw-37-8 \
             ${PN}-ibt-17 \
             ${PN}-ibt-20 \
             ${PN}-ibt-misc \
             ${PN}-i915-license ${PN}-i915 \
             ${PN}-ice-license ${PN}-ice \
             ${PN}-adsp-sst-license ${PN}-adsp-sst \
             ${PN}-bnx2-mips \
             ${PN}-liquidio \
             ${PN}-nvidia-license \
             ${PN}-nvidia-tegra-k1 ${PN}-nvidia-tegra \
             ${PN}-nvidia-gpu \
             ${PN}-netronome-license ${PN}-netronome \
             ${PN}-qat ${PN}-qat-license \
             ${PN}-qcom-license ${PN}-qcom-yamato-license \
             ${PN}-qcom-venus-1.8 ${PN}-qcom-venus-4.2 ${PN}-qcom-venus-5.2 ${PN}-qcom-venus-5.4 \
             ${PN}-qcom-vpu-1.0 ${PN}-qcom-vpu-2.0 \
             ${PN}-qcom-adreno-a2xx ${PN}-qcom-adreno-a3xx ${PN}-qcom-adreno-a4xx ${PN}-qcom-adreno-a530 \
             ${PN}-qcom-adreno-a630 ${PN}-qcom-adreno-a650 ${PN}-qcom-adreno-a660 \
             ${PN}-qcom-apq8096-audio ${PN}-qcom-apq8096-modem \
             ${PN}-qcom-sc8280xp-lenovo-x13s-compat \
             ${PN}-qcom-sc8280xp-lenovo-x13s-audio \
             ${PN}-qcom-sc8280xp-lenovo-x13s-adreno \
             ${PN}-qcom-sc8280xp-lenovo-x13s-compute \
             ${PN}-qcom-sc8280xp-lenovo-x13s-sensors \
             ${PN}-qcom-sdm845-audio ${PN}-qcom-sdm845-compute ${PN}-qcom-sdm845-modem \
             ${PN}-qcom-sm8250-audio ${PN}-qcom-sm8250-compute \
             ${PN}-amlogic-vdec-license ${PN}-amlogic-vdec \
             ${PN}-lt9611uxc ${PN}-lontium-license \
             ${PN}-whence-license \
             ${PN}-license \
             "

# For atheros
LICENSE:${PN}-ar9170 = "Firmware-atheros_firmware"
LICENSE:${PN}-ath3k = "Firmware-atheros_firmware"
LICENSE:${PN}-ath6k = "Firmware-atheros_firmware"
LICENSE:${PN}-ath9k = "Firmware-atheros_firmware"
LICENSE:${PN}-atheros-license = "Firmware-atheros_firmware"

FILES:${PN}-atheros-license = "${libdir}/firmware/LICENCE.atheros_firmware*"
FILES:${PN}-ar9170 = " \
  ${libdir}/firmware/ar9170*.fw* \
"
FILES:${PN}-ath3k = " \
  ${libdir}/firmware/ath3k*fw* \
"
FILES:${PN}-ath6k = " \
  ${libdir}/firmware/ath6k \
"
FILES:${PN}-ath9k = " \
  ${libdir}/firmware/ar9271.fw* \
  ${libdir}/firmware/ar7010*.fw* \
  ${libdir}/firmware/htc_9271.fw* \
  ${libdir}/firmware/htc_7010.fw* \
  ${libdir}/firmware/ath9k_htc/htc_7010-1.4.0.fw* \
  ${libdir}/firmware/ath9k_htc/htc_9271-1.4.0.fw* \
"

RDEPENDS:${PN}-ar9170 += "${PN}-atheros-license"
RDEPENDS:${PN}-ath6k += "${PN}-atheros-license"
RDEPENDS:${PN}-ath9k += "${PN}-atheros-license"

# For carl9170
LICENSE:${PN}-carl9170 = "Firmware-GPLv2"
LICENSE:${PN}-gplv2-license = "Firmware-GPLv2"

FILES:${PN}-gplv2-license = "${libdir}/firmware/GPL-2"
FILES:${PN}-carl9170 = " \
  ${libdir}/firmware/carl9170*.fw* \
"

RDEPENDS:${PN}-carl9170 += "${PN}-gplv2-license"

# For QualCommAthos
LICENSE:${PN}-ar3k = "Firmware-qualcommAthos_ar3k & Firmware-atheros_firmware"
LICENSE:${PN}-ar3k-license = "Firmware-qualcommAthos_ar3k"
LICENSE:${PN}-ath10k = "Firmware-qualcommAthos_ath10k"
LICENSE:${PN}-ath10k-license = "Firmware-qualcommAthos_ath10k"
LICENSE:${PN}-qca = "Firmware-qualcommAthos_ath10k"

FILES:${PN}-ar3k-license = "${libdir}/firmware/LICENSE.QualcommAtheros_ar3k*"
FILES:${PN}-ar3k = " \
  ${libdir}/firmware/ar3k \
"

FILES:${PN}-ath10k-license = "${libdir}/firmware/LICENSE.QualcommAtheros_ath10k*"
FILES:${PN}-ath10k = " \
  ${libdir}/firmware/ath10k \
"

FILES:${PN}-ath11k = " \
  ${libdir}/firmware/ath11k \
"

FILES:${PN}-qca = " \
  ${libdir}/firmware/qca \
"

RDEPENDS:${PN}-ar3k += "${PN}-ar3k-license ${PN}-atheros-license"
RDEPENDS:${PN}-ath10k += "${PN}-ath10k-license"
RDEPENDS:${PN}-ath11k += "${PN}-ath10k-license"
RDEPENDS:${PN}-qca += "${PN}-ath10k-license"

# For ralink
LICENSE:${PN}-ralink = "Firmware-ralink-firmware"
LICENSE:${PN}-ralink-license = "Firmware-ralink-firmware"

FILES:${PN}-ralink-license = "${libdir}/firmware/LICENCE.ralink-firmware.txt*"
FILES:${PN}-ralink = " \
  ${libdir}/firmware/rt*.bin* \
"

RDEPENDS:${PN}-ralink += "${PN}-ralink-license"

# For mediatek MT7601U
LICENSE:${PN}-mt7601u = "Firmware-ralink_a_mediatek_company_firmware"
LICENSE:${PN}-mt7601u-license = "Firmware-ralink_a_mediatek_company_firmware"

FILES:${PN}-mt7601u-license = "${libdir}/firmware/LICENCE.ralink_a_mediatek_company_firmware*"
FILES:${PN}-mt7601u = " \
  ${libdir}/firmware/mt7601u.bin* \
"

RDEPENDS:${PN}-mt7601u += "${PN}-mt7601u-license"

# For radeon
LICENSE:${PN}-radeon = "Firmware-radeon"
LICENSE:${PN}-radeon-license = "Firmware-radeon"

FILES:${PN}-radeon-license = "${libdir}/firmware/LICENSE.radeon*"
FILES:${PN}-radeon = " \
  ${libdir}/firmware/radeon \
"

RDEPENDS:${PN}-radeon += "${PN}-radeon-license"

# For amdgpu
LICENSE:${PN}-amdgpu = "Firmware-amdgpu"
LICENSE:${PN}-amdgpu-license = "Firmware-amdgpu"

FILES:${PN}-amdgpu-license = "${libdir}/firmware/LICENSE.amdgpu*"
FILES:${PN}-amdgpu = " \
  ${libdir}/firmware/amdgpu \
"

RDEPENDS:${PN}-amdgpu += "${PN}-amdgpu-license"

# For lontium
LICENSE:${PN}-lt9611uxc = "Firmware-Lontium"

FILES:${PN}-lontium-license = "${libdir}/firmware/LICENSE.Lontium*"
FILES:${PN}-lt9611uxc = "${libdir}/firmware/lt9611uxc_fw.bin*"

# For marvell
LICENSE:${PN}-pcie8897 = "Firmware-Marvell"
LICENSE:${PN}-pcie8997 = "Firmware-Marvell"
LICENSE:${PN}-sd8686 = "Firmware-Marvell"
LICENSE:${PN}-sd8688 = "Firmware-Marvell"
LICENSE:${PN}-sd8787 = "Firmware-Marvell"
LICENSE:${PN}-sd8797 = "Firmware-Marvell"
LICENSE:${PN}-sd8801 = "Firmware-Marvell"
LICENSE:${PN}-sd8887 = "Firmware-Marvell"
LICENSE:${PN}-sd8897 = "Firmware-Marvell"
LICENSE:${PN}-sd8997 = "Firmware-Marvell"
LICENSE:${PN}-usb8997 = "Firmware-Marvell"
LICENSE:${PN}-marvell-license = "Firmware-Marvell"

FILES:${PN}-marvell-license = "${libdir}/firmware/LICENCE.Marvell*"
FILES:${PN}-pcie8897 = " \
  ${libdir}/firmware/mrvl/pcie8897_uapsta.bin* \
"
FILES:${PN}-pcie8997 = " \
  ${libdir}/firmware/mrvl/pcie8997_wlan_v4.bin* \
  ${libdir}/firmware/mrvl/pcieuart8997_combo_v4.bin* \
  ${libdir}/firmware/mrvl/pcieusb8997_combo_v4.bin* \
"
FILES:${PN}-sd8686 = " \
  ${libdir}/firmware/libertas/sd8686_v9* \
  ${libdir}/firmware/sd8686* \
"
FILES:${PN}-sd8688 = " \
  ${libdir}/firmware/libertas/sd8688* \
  ${libdir}/firmware/mrvl/sd8688* \
"
FILES:${PN}-sd8787 = " \
  ${libdir}/firmware/mrvl/sd8787_uapsta.bin* \
"
FILES:${PN}-sd8797 = " \
  ${libdir}/firmware/mrvl/sd8797_uapsta.bin \
"
FILES:${PN}-sd8801 = " \
  ${libdir}/firmware/mrvl/sd8801_uapsta.bin* \
"
FILES:${PN}-sd8887 = " \
  ${libdir}/firmware/mrvl/sd8887_uapsta.bin* \
"
FILES:${PN}-sd8897 = " \
  ${libdir}/firmware/mrvl/sd8897_uapsta.bin* \
"
do_install:append() {
    # The kernel 5.6.x driver still uses the old name, provide a symlink for
    # older kernels
    ln -fs sdsd8997_combo_v4.bin ${D}${libdir}/firmware/mrvl/sd8997_uapsta.bin
}
FILES:${PN}-sd8997 = " \
  ${libdir}/firmware/mrvl/sd8997_uapsta.bin* \
  ${libdir}/firmware/mrvl/sdsd8997_combo_v4.bin* \
"
FILES:${PN}-usb8997 = " \
  ${libdir}/firmware/mrvl/usbusb8997_combo_v4.bin* \
"

RDEPENDS:${PN}-sd8686 += "${PN}-marvell-license"
RDEPENDS:${PN}-sd8688 += "${PN}-marvell-license"
RDEPENDS:${PN}-sd8787 += "${PN}-marvell-license"
RDEPENDS:${PN}-sd8797 += "${PN}-marvell-license"
RDEPENDS:${PN}-sd8801 += "${PN}-marvell-license"
RDEPENDS:${PN}-sd8887 += "${PN}-marvell-license"
RDEPENDS:${PN}-sd8897 += "${PN}-marvell-license"
RDEPENDS:${PN}-sd8997 += "${PN}-marvell-license"
RDEPENDS:${PN}-usb8997 += "${PN}-marvell-license"

# For netronome
LICENSE:${PN}-netronome = "Firmware-netronome"

FILES:${PN}-netronome-license = " \
  ${libdir}/firmware/LICENCE.Netronome* \
"
FILES:${PN}-netronome = " \
  ${libdir}/firmware/netronome/nic_AMDA0081*.nffw* \
  ${libdir}/firmware/netronome/nic_AMDA0096*.nffw* \
  ${libdir}/firmware/netronome/nic_AMDA0097*.nffw* \
  ${libdir}/firmware/netronome/nic_AMDA0099*.nffw* \
  ${libdir}/firmware/netronome/nic_AMDA0058-0011_2x40.nffw* \
  ${libdir}/firmware/netronome/nic_AMDA0058-0012_2x40.nffw* \
  ${libdir}/firmware/netronome/nic_AMDA0078-0011_1x100.nffw* \
  ${libdir}/firmware/netronome/bpf* \
  ${libdir}/firmware/netronome/flower* \
  ${libdir}/firmware/netronome/nic* \
  ${libdir}/firmware/netronome/nic-sriov* \
"

RDEPENDS:${PN}-netronome += "${PN}-netronome-license"

# For Nvidia
LICENSE:${PN}-nvidia-gpu = "Firmware-nvidia"
LICENSE:${PN}-nvidia-tegra = "Firmware-nvidia"
LICENSE:${PN}-nvidia-tegra-k1 = "Firmware-nvidia"
LICENSE:${PN}-nvidia-license = "Firmware-nvidia"

FILES:${PN}-nvidia-gpu = "${libdir}/firmware/nvidia"
FILES:${PN}-nvidia-tegra = " \
  ${libdir}/firmware/nvidia/tegra* \
  ${libdir}/firmware/nvidia/gm20b \
  ${libdir}/firmware/nvidia/gp10b \
"
FILES:${PN}-nvidia-tegra-k1 = " \
  ${libdir}/firmware/nvidia/tegra124 \
  ${libdir}/firmware/nvidia/gk20a \
"
FILES:${PN}-nvidia-license = "${libdir}/firmware/LICENCE.nvidia*"

RDEPENDS:${PN}-nvidia-gpu += "${PN}-nvidia-license"
RDEPENDS:${PN}-nvidia-tegra += "${PN}-nvidia-license"
RDEPENDS:${PN}-nvidia-tegra-k1 += "${PN}-nvidia-license"

# For RSI RS911x WiFi
LICENSE:${PN}-rs9113 = "WHENCE"
LICENSE:${PN}-rs9116 = "WHENCE"

FILES:${PN}-rs9113 = " ${libdir}/firmware/rsi/rs9113*.rps* "
FILES:${PN}-rs9116 = " ${libdir}/firmware/rsi/rs9116*.rps* "

RDEPENDS:${PN}-rs9113 += "${PN}-whence-license"
RDEPENDS:${PN}-rs9116 += "${PN}-whence-license"

# For rtl
LICENSE:${PN}-rtl8188 = "Firmware-rtlwifi_firmware"
LICENSE:${PN}-rtl8192cu = "Firmware-rtlwifi_firmware"
LICENSE:${PN}-rtl8192ce = "Firmware-rtlwifi_firmware"
LICENSE:${PN}-rtl8192su = "Firmware-rtlwifi_firmware"
LICENSE:${PN}-rtl8723 = "Firmware-rtlwifi_firmware"
LICENSE:${PN}-rtl8761 = "Firmware-rtlwifi_firmware"
LICENSE:${PN}-rtl8821 = "Firmware-rtlwifi_firmware"
LICENSE:${PN}-rtl-license = "Firmware-rtlwifi_firmware"
LICENSE:${PN}-rtl8168 = "WHENCE"

FILES:${PN}-rtl-license = " \
  ${libdir}/firmware/LICENCE.rtlwifi_firmware.txt* \
"
FILES:${PN}-rtl8188 = " \
  ${libdir}/firmware/rtlwifi/rtl8188*.bin* \
"
FILES:${PN}-rtl8192cu = " \
  ${libdir}/firmware/rtlwifi/rtl8192cufw*.bin* \
"
FILES:${PN}-rtl8192ce = " \
  ${libdir}/firmware/rtlwifi/rtl8192cfw*.bin* \
"
FILES:${PN}-rtl8192su = " \
  ${libdir}/firmware/rtlwifi/rtl8712u.bin* \
"
FILES:${PN}-rtl8723 = " \
  ${libdir}/firmware/rtlwifi/rtl8723*.bin* \
"
FILES:${PN}-rtl8821 = " \
  ${libdir}/firmware/rtlwifi/rtl8821*.bin* \
"
FILES:${PN}-rtl8761 = " \
  ${libdir}/firmware/rtl_bt/rtl8761*.bin* \
"
FILES:${PN}-rtl8168 = " \
  ${libdir}/firmware/rtl_nic/rtl8168*.fw* \
"

RDEPENDS:${PN}-rtl8188 += "${PN}-rtl-license"
RDEPENDS:${PN}-rtl8192ce += "${PN}-rtl-license"
RDEPENDS:${PN}-rtl8192cu += "${PN}-rtl-license"
RDEPENDS:${PN}-rtl8192su = "${PN}-rtl-license"
RDEPENDS:${PN}-rtl8723 += "${PN}-rtl-license"
RDEPENDS:${PN}-rtl8821 += "${PN}-rtl-license"
RDEPENDS:${PN}-rtl8761 += "${PN}-rtl-license"
RDEPENDS:${PN}-rtl8168 += "${PN}-whence-license"

# For ti-connectivity
LICENSE:${PN}-wlcommon = "Firmware-ti-connectivity"
LICENSE:${PN}-wl12xx = "Firmware-ti-connectivity"
LICENSE:${PN}-wl18xx = "Firmware-ti-connectivity"
LICENSE:${PN}-ti-connectivity-license = "Firmware-ti-connectivity"

FILES:${PN}-ti-connectivity-license = "${libdir}/firmware/LICENCE.ti-connectivity*"
# wl18xx optionally needs wl1271-nvs.bin (which itself is a symlink to
# wl127x-nvs.bin) - see linux/drivers/net/wireless/ti/wlcore/sdio.c
# and drivers/net/wireless/ti/wlcore/spi.c.
# While they're optional and actually only used to override the MAC
# address on wl18xx, driver loading will delay (by udev timout - 60s)
# if not there. So let's make it available always. Because it's a
# symlink, both need to go to wlcommon.
FILES:${PN}-wlcommon = " \
  ${libdir}/firmware/ti-connectivity/TI* \
  ${libdir}/firmware/ti-connectivity/wl127x-nvs.bin* \
  ${libdir}/firmware/ti-connectivity/wl1271-nvs.bin* \
"
FILES:${PN}-wl12xx = " \
  ${libdir}/firmware/ti-connectivity/wl12* \
"
FILES:${PN}-wl18xx = " \
  ${libdir}/firmware/ti-connectivity/wl18* \
"

RDEPENDS:${PN}-wl12xx = "${PN}-ti-connectivity-license ${PN}-wlcommon"
RDEPENDS:${PN}-wl18xx = "${PN}-ti-connectivity-license ${PN}-wlcommon"

# For vt6656
LICENSE:${PN}-vt6656 = "Firmware-via_vt6656"
LICENSE:${PN}-vt6656-license = "Firmware-via_vt6656"

FILES:${PN}-vt6656-license = "${libdir}/firmware/LICENCE.via_vt6656*"
FILES:${PN}-vt6656 = " \
  ${libdir}/firmware/vntwusb.fw* \
"

RDEPENDS:${PN}-vt6656 = "${PN}-vt6656-license"

# For broadcom

# for i in `grep brcm WHENCE  | grep ^File | sed 's/File: brcm.//g'`; do pkg=`echo $i | sed 's/-[sp40].*//g; s/\.bin//g; s/brcmfmac/bcm/g; s/_hdr/-hdr/g; s/BCM/bcm-0bb4-0306/g'`; echo -e "             \${PN}-$pkg \\"; done  | sort -u

LICENSE:${PN}-broadcom-license = "Firmware-broadcom_bcm43xx"
FILES:${PN}-broadcom-license = "${libdir}/firmware/LICENCE.broadcom_bcm43xx*"

# for i in `grep brcm WHENCE  | grep ^File | sed 's/File: brcm.//g'`; do pkg=`echo $i | sed 's/-[sp40].*//g; s/\.bin//g; s/brcmfmac/bcm/g; s/_hdr/-hdr/g; s/BCM/bcm-0bb4-0306/g'`; echo "$i - $pkg"; echo -e "FILES:\${PN}-$pkg = \"\${libdir}/firmware/brcm/$i\""; done | grep ^FILES

FILES:${PN}-bcm43xx = "${libdir}/firmware/brcm/bcm43xx-0.fw*"
FILES:${PN}-bcm43xx-hdr = "${libdir}/firmware/brcm/bcm43xx_hdr-0.fw*"
FILES:${PN}-bcm4329-fullmac = "${libdir}/firmware/brcm/bcm4329-fullmac-4.bin*"
FILES:${PN}-bcm43236b = "${libdir}/firmware/brcm/brcmfmac43236b.bin*"
FILES:${PN}-bcm4329 = "${libdir}/firmware/brcm/brcmfmac4329-sdio.bin*"
FILES:${PN}-bcm4330 = "${libdir}/firmware/brcm/brcmfmac4330-sdio.*"
FILES:${PN}-bcm4334 = "${libdir}/firmware/brcm/brcmfmac4334-sdio.bin*"
FILES:${PN}-bcm4335 = "${libdir}/firmware/brcm/brcmfmac4335-sdio.bin*"
FILES:${PN}-bcm4339 = "${libdir}/firmware/brcm/brcmfmac4339-sdio.bin* \
  ${libdir}/firmware/cypress/cyfmac4339-sdio.bin* \
"
FILES:${PN}-bcm43241b0 = "${libdir}/firmware/brcm/brcmfmac43241b0-sdio.bin*"
FILES:${PN}-bcm43241b4 = "${libdir}/firmware/brcm/brcmfmac43241b4-sdio.bin*"
FILES:${PN}-bcm43241b5 = "${libdir}/firmware/brcm/brcmfmac43241b5-sdio.bin*"
FILES:${PN}-bcm43242a = "${libdir}/firmware/brcm/brcmfmac43242a.bin*"
FILES:${PN}-bcm43143 = "${libdir}/firmware/brcm/brcmfmac43143.bin* \
  ${libdir}/firmware/brcm/brcmfmac43143-sdio.bin* \
"
FILES:${PN}-bcm43430a0 = "${libdir}/firmware/brcm/brcmfmac43430a0-sdio.*"
FILES:${PN}-bcm43455 = "${libdir}/firmware/brcm/brcmfmac43455-sdio.* \
  ${libdir}/firmware/cypress/cyfmac43455-sdio.* \
"
FILES:${PN}-bcm4350c2 = "${libdir}/firmware/brcm/brcmfmac4350c2-pcie.bin*"
FILES:${PN}-bcm4350 = "${libdir}/firmware/brcm/brcmfmac4350-pcie.bin*"
FILES:${PN}-bcm4356 = "${libdir}/firmware/brcm/brcmfmac4356-sdio.* \
  ${libdir}/firmware/cypress/cyfmac4356-sdio.* \
"
FILES:${PN}-bcm43569 = "${libdir}/firmware/brcm/brcmfmac43569.bin*"
FILES:${PN}-bcm43570 = "${libdir}/firmware/brcm/brcmfmac43570-pcie.bin* \
  ${libdir}/firmware/cypress/cyfmac43570-pcie.bin* \
"
FILES:${PN}-bcm4358 = "${libdir}/firmware/brcm/brcmfmac4358-pcie.bin*"
FILES:${PN}-bcm43602 = "${libdir}/firmware/brcm/brcmfmac43602-pcie.bin* \
  ${libdir}/firmware/brcm/brcmfmac43602-pcie.ap.bin* \
"
FILES:${PN}-bcm4366b = "${libdir}/firmware/brcm/brcmfmac4366b-pcie.bin*"
FILES:${PN}-bcm4366c = "${libdir}/firmware/brcm/brcmfmac4366c-pcie.bin*"
FILES:${PN}-bcm4371 = "${libdir}/firmware/brcm/brcmfmac4371-pcie.bin*"

# for i in `grep brcm WHENCE  | grep ^File | sed 's/File: brcm.//g'`; do pkg=`echo $i | sed 's/-[sp40].*//g; s/\.bin//g; s/brcmfmac/bcm/g; s/_hdr/-hdr/g; s/BCM/bcm-0bb4-0306/g'`; echo -e "LICENSE:\${PN}-$pkg = \"Firmware-broadcom_bcm43xx\"\nRDEPENDS_\${PN}-$pkg += \"\${PN}-broadcom-license\""; done
# Currently 1st one and last 6 have cypress LICENSE

LICENSE:${PN}-bcm43xx = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43xx += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43xx-hdr = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43xx-hdr += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4329-fullmac = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4329-fullmac += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43236b = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43236b += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4329 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4329 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4330 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4330 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4334 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4334 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4335 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4335 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4339 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4339 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43241b0 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43241b0 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43241b4 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43241b4 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43241b5 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43241b5 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43242a = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43242a += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43143 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43143 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43430a0 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43430a0 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43455 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43455 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4350c2 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4350c2 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4350 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4350 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4356 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4356 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43569 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43569 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43570 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43570 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4358 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4358 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm43602 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm43602 += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4366b = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4366b += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4366c = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4366c += "${PN}-broadcom-license"
LICENSE:${PN}-bcm4371 = "Firmware-broadcom_bcm43xx"
RDEPENDS:${PN}-bcm4371 += "${PN}-broadcom-license"

# For broadcom cypress

LICENSE:${PN}-cypress-license = "Firmware-cypress"
FILES:${PN}-cypress-license = "${libdir}/firmware/LICENCE.cypress*"

FILES:${PN}-bcm-0bb4-0306 = "${libdir}/firmware/brcm/BCM-0bb4-0306.hcd*"
FILES:${PN}-bcm43340 = "${libdir}/firmware/brcm/brcmfmac43340-sdio.* \
  ${libdir}/firmware/cypress/cyfmac43340-sdio.*"
FILES:${PN}-bcm43362 = "${libdir}/firmware/brcm/brcmfmac43362-sdio.* \
  ${libdir}/firmware/cypress/cyfmac43362-sdio.*"
FILES:${PN}-bcm43430 = "${libdir}/firmware/brcm/brcmfmac43430-sdio.* \
  ${libdir}/firmware/cypress/cyfmac43430-sdio.*"
FILES:${PN}-bcm4354 = "${libdir}/firmware/brcm/brcmfmac4354-sdio.bin* \
  ${libdir}/firmware/cypress/cyfmac4354-sdio.bin* \
"
FILES:${PN}-bcm4356-pcie = "${libdir}/firmware/brcm/brcmfmac4356-pcie.* \
  ${libdir}/firmware/cypress/cyfmac4356-pcie.* \
"
FILES:${PN}-bcm4373 = "${libdir}/firmware/brcm/brcmfmac4373-sdio.bin* \
  ${libdir}/firmware/brcm/brcmfmac4373.bin* \
  ${libdir}/firmware/cypress/cyfmac4373-sdio.bin* \
  ${libdir}/firmware/brcm/brcmfmac4373-sdio.clm_blob* \
"

LICENSE:${PN}-bcm-0bb4-0306 = "Firmware-cypress"
RDEPENDS:${PN}-bcm-0bb4-0306 += "${PN}-cypress-license"
LICENSE:${PN}-bcm43340 = "Firmware-cypress"
RDEPENDS:${PN}-bcm43340 += "${PN}-cypress-license"
LICENSE:${PN}-bcm43362 = "Firmware-cypress"
RDEPENDS:${PN}-bcm43362 += "${PN}-cypress-license"
LICENSE:${PN}-bcm43430 = "Firmware-cypress"
RDEPENDS:${PN}-bcm43430 += "${PN}-cypress-license"
LICENSE:${PN}-bcm4354 = "Firmware-cypress"
RDEPENDS:${PN}-bcm4354 += "${PN}-cypress-license"
LICENSE:${PN}-bcm4356-pcie = "Firmware-cypress"
RDEPENDS:${PN}-bcm4356-pcie += "${PN}-cypress-license"
LICENSE:${PN}-bcm4373 = "Firmware-cypress"
RDEPENDS:${PN}-bcm4373 += "${PN}-cypress-license"

# For Broadcom bnx2-mips
#
# which is a separate case to the other Broadcom firmwares since its
# license is contained in the shared WHENCE file.

LICENSE:${PN}-bnx2-mips = "WHENCE"
LICENSE:${PN}-whence-license = "WHENCE"

FILES:${PN}-bnx2-mips = "${libdir}/firmware/bnx2/bnx2-mips-09-6.2.1b.fw*"
FILES:${PN}-whence-license = "${libdir}/firmware/WHENCE*"

RDEPENDS:${PN}-bnx2-mips += "${PN}-whence-license"

# For imx-sdma
LICENSE:${PN}-imx-sdma-imx6q       = "Firmware-imx-sdma_firmware"
LICENSE:${PN}-imx-sdma-imx7d       = "Firmware-imx-sdma_firmware"
LICENSE:${PN}-imx-sdma-license       = "Firmware-imx-sdma_firmware"

FILES:${PN}-imx-sdma-imx6q = "${libdir}/firmware/imx/sdma/sdma-imx6q.bin*"

RPROVIDES:${PN}-imx-sdma-imx6q = "firmware-imx-sdma-imx6q"
RREPLACES:${PN}-imx-sdma-imx6q = "firmware-imx-sdma-imx6q"
RCONFLICTS:${PN}-imx-sdma-imx6q = "firmware-imx-sdma-imx6q"

FILES:${PN}-imx-sdma-imx7d = "${libdir}/firmware/imx/sdma/sdma-imx7d.bin*"

FILES:${PN}-imx-sdma-license = "${libdir}/firmware/LICENSE.sdma_firmware*"

RDEPENDS:${PN}-imx-sdma-imx6q += "${PN}-imx-sdma-license"
RDEPENDS:${PN}-imx-sdma-imx7d += "${PN}-imx-sdma-license"

# For iwlwifi
LICENSE:${PN}-iwlwifi           = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-135-6     = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-3160-17   = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-6000-4    = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-6000g2a-5 = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-6000g2a-6 = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-6000g2b-5 = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-6000g2b-6 = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-6050-5    = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-7260      = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-7265      = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-7265d     = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-8000c     = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-8265      = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-9000      = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-misc      = "Firmware-iwlwifi_firmware"
LICENSE:${PN}-iwlwifi-license   = "Firmware-iwlwifi_firmware"


FILES:${PN}-iwlwifi-license = "${libdir}/firmware/LICENCE.iwlwifi_firmware*"
FILES:${PN}-iwlwifi-135-6 = "${libdir}/firmware/iwlwifi-135-6.ucode*"
FILES:${PN}-iwlwifi-3160-17 = "${libdir}/firmware/iwlwifi-3160-17.ucode*"
FILES:${PN}-iwlwifi-6000-4 = "${libdir}/firmware/iwlwifi-6000-4.ucode*"
FILES:${PN}-iwlwifi-6000g2a-6 = "${libdir}/firmware/iwlwifi-6000g2a-6.ucode*"
FILES:${PN}-iwlwifi-6000g2b-6 = "${libdir}/firmware/iwlwifi-6000g2b-6.ucode*"
FILES:${PN}-iwlwifi-6050-5 = "${libdir}/firmware/iwlwifi-6050-5.ucode*"
FILES:${PN}-iwlwifi-7260   = "${libdir}/firmware/iwlwifi-7260-*.ucode*"
FILES:${PN}-iwlwifi-7265   = "${libdir}/firmware/iwlwifi-7265-*.ucode*"
FILES:${PN}-iwlwifi-7265d   = "${libdir}/firmware/iwlwifi-7265D-*.ucode*"
FILES:${PN}-iwlwifi-8000c   = "${libdir}/firmware/iwlwifi-8000C-*.ucode*"
FILES:${PN}-iwlwifi-8265   = "${libdir}/firmware/iwlwifi-8265-*.ucode*"
FILES:${PN}-iwlwifi-9000   = "${libdir}/firmware/iwlwifi-9000-*.ucode*"
FILES:${PN}-iwlwifi-misc   = "${libdir}/firmware/iwlwifi-*.ucode*"

RDEPENDS:${PN}-iwlwifi-135-6     = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-3160-17   = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-6000-4    = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-6000g2a-5 = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-6000g2a-6 = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-6000g2b-5 = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-6000g2b-6 = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-6050-5    = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-7260      = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-7265      = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-7265d     = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-8000c     = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-8265      = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-9000      = "${PN}-iwlwifi-license"
RDEPENDS:${PN}-iwlwifi-misc      = "${PN}-iwlwifi-license"

# -iwlwifi-misc is a "catch all" package that includes all the iwlwifi
# firmwares that are not already included in other -iwlwifi- packages.
# -iwlwifi is a virtual package that depends upon all iwlwifi packages.
# These are distinct in order to allow the -misc firmwares to be installed
# without pulling in every other iwlwifi package.
ALLOW_EMPTY:${PN}-iwlwifi = "1"
ALLOW_EMPTY:${PN}-iwlwifi-misc = "1"

# Handle package updating for the newly merged iwlwifi groupings
RPROVIDES:${PN}-iwlwifi-7265 = "${PN}-iwlwifi-7265-8 ${PN}-iwlwifi-7265-9"
RREPLACES:${PN}-iwlwifi-7265 = "${PN}-iwlwifi-7265-8 ${PN}-iwlwifi-7265-9"
RCONFLICTS:${PN}-iwlwifi-7265 = "${PN}-iwlwifi-7265-8 ${PN}-iwlwifi-7265-9"

RPROVIDES:${PN}-iwlwifi-7260 = "${PN}-iwlwifi-7260-7 ${PN}-iwlwifi-7260-8 ${PN}-iwlwifi-7260-9"
RREPLACES:${PN}-iwlwifi-7260 = "${PN}-iwlwifi-7260-7 ${PN}-iwlwifi-7260-8 ${PN}-iwlwifi-7260-9"
RCONFLICTS:${PN}-iwlwifi-7260 = "${PN}-iwlwifi-7260-7 ${PN}-iwlwifi-7260-8 ${PN}-iwlwifi-7260-9"

# For ibt
LICENSE:${PN}-ibt-license = "Firmware-ibt_firmware"
LICENSE:${PN}-ibt-hw-37-7 = "Firmware-ibt_firmware"
LICENSE:${PN}-ibt-hw-37-8 = "Firmware-ibt_firmware"
LICENSE:${PN}-ibt-11-5    = "Firmware-ibt_firmware"
LICENSE:${PN}-ibt-12-16   = "Firmware-ibt_firmware"
LICENSE:${PN}-ibt-17 = "Firmware-ibt_firmware"
LICENSE:${PN}-ibt-20 = "Firmware-ibt_firmware"
LICENSE:${PN}-ibt-misc    = "Firmware-ibt_firmware"

FILES:${PN}-ibt-license = "${libdir}/firmware/LICENCE.ibt_firmware*"
FILES:${PN}-ibt-hw-37-7 = "${libdir}/firmware/intel/ibt-hw-37.7*.bseq*"
FILES:${PN}-ibt-hw-37-8 = "${libdir}/firmware/intel/ibt-hw-37.8*.bseq*"
FILES:${PN}-ibt-11-5    = "${libdir}/firmware/intel/ibt-11-5.sfi* ${libdir}/firmware/intel/ibt-11-5.ddc*"
FILES:${PN}-ibt-12-16   = "${libdir}/firmware/intel/ibt-12-16.sfi* ${libdir}/firmware/intel/ibt-12-16.ddc*"
FILES:${PN}-ibt-17 = "${libdir}/firmware/intel/ibt-17-*.sfi* ${libdir}/firmware/intel/ibt-17-*.ddc*"
FILES:${PN}-ibt-20 = "${libdir}/firmware/intel/ibt-20-*.sfi* ${libdir}/firmware/intel/ibt-20-*.ddc*"
FILES:${PN}-ibt-misc    = "${libdir}/firmware/intel/ibt-*"

RDEPENDS:${PN}-ibt-hw-37-7 = "${PN}-ibt-license"
RDEPENDS:${PN}-ibt-hw-37.8 = "${PN}-ibt-license"
RDEPENDS:${PN}-ibt-11-5    = "${PN}-ibt-license"
RDEPENDS:${PN}-ibt-12-16   = "${PN}-ibt-license"
RDEPENDS:${PN}-ibt-17 = "${PN}-ibt-license"
RDEPENDS:${PN}-ibt-20 = "${PN}-ibt-license"
RDEPENDS:${PN}-ibt-misc    = "${PN}-ibt-license"

ALLOW_EMPTY:${PN}-ibt= "1"
ALLOW_EMPTY:${PN}-ibt-misc = "1"

LICENSE:${PN}-i915       = "Firmware-i915"
LICENSE:${PN}-i915-license = "Firmware-i915"
FILES:${PN}-i915-license = "${libdir}/firmware/LICENSE.i915*"
FILES:${PN}-i915         = "${libdir}/firmware/i915"
RDEPENDS:${PN}-i915      = "${PN}-i915-license"

LICENSE:${PN}-ice       = "Firmware-ice"
LICENSE:${PN}-ice-license = "Firmware-ice"
FILES:${PN}-ice-license = "${libdir}/firmware/LICENSE.ice*"
FILES:${PN}-ice         = "${libdir}/firmware/intel/ice"
RDEPENDS:${PN}-ice      = "${PN}-ice-license"

FILES:${PN}-adsp-sst-license      = "${libdir}/firmware/LICENCE.adsp_sst*"
LICENSE:${PN}-adsp-sst            = "Firmware-adsp_sst"
LICENSE:${PN}-adsp-sst-license    = "Firmware-adsp_sst"
FILES:${PN}-adsp-sst              = "${libdir}/firmware/intel/dsp_fw*"
RDEPENDS:${PN}-adsp-sst           = "${PN}-adsp-sst-license"

# For QAT
LICENSE:${PN}-qat         = "Firmware-qat"
LICENSE:${PN}-qat-license = "Firmware-qat"
FILES:${PN}-qat-license   = "${libdir}/firmware/LICENCE.qat_firmware*"
FILES:${PN}-qat           = "${libdir}/firmware/qat*.bin*"
RDEPENDS:${PN}-qat        = "${PN}-qat-license"

# For QCOM VPU/GPU and SDM845
LICENSE:${PN}-qcom-license = "Firmware-qcom"
LICENSE:${PN}-qcom-yamato-license = "Firmware-qcom-yamato"
LICENSE:${PN}-qcom-venus-1.8 = "Firmware-qcom"
LICENSE:${PN}-qcom-venus-4.2 = "Firmware-qcom"
LICENSE:${PN}-qcom-venus-5.2 = "Firmware-qcom"
LICENSE:${PN}-qcom-venus-5.4 = "Firmware-qcom"
LICENSE:${PN}-qcom-vpu-1.0 = "Firmware-qcom"
LICENSE:${PN}-qcom-vpu-2.0 = "Firmware-qcom"
LICENSE:${PN}-qcom-adreno-a2xx = "Firmware-qcom Firmware-qcom-yamato"
LICENSE:${PN}-qcom-adreno-a3xx = "Firmware-qcom"
LICENSE:${PN}-qcom-adreno-a4xx = "Firmware-qcom"
LICENSE:${PN}-qcom-adreno-a530 = "Firmware-qcom"
LICENSE:${PN}-qcom-adreno-a630 = "Firmware-qcom"
LICENSE:${PN}-qcom-adreno-a650 = "Firmware-qcom"
LICENSE:${PN}-qcom-adreno-a660 = "Firmware-qcom"
LICENSE:${PN}-qcom-apq8096-audio = "Firmware-qcom"
LICENSE:${PN}-qcom-apq8096-modem = "Firmware-qcom"
LICENSE:${PN}-qcom-sc8280xp-lenovo-x13s-audio = "Firmware-qcom"
LICENSE:${PN}-qcom-sc8280xp-lenovo-x13s-adreno = "Firmware-qcom"
LICENSE:${PN}-qcom-sc8280xp-lenovo-x13s-compute = "Firmware-qcom"
LICENSE:${PN}-qcom-sc8280xp-lenovo-x13s-sensors = "Firmware-qcom"
LICENSE:${PN}-qcom-sdm845-audio = "Firmware-qcom"
LICENSE:${PN}-qcom-sdm845-compute = "Firmware-qcom"
LICENSE:${PN}-qcom-sdm845-modem = "Firmware-qcom"
LICENSE:${PN}-qcom-sm8250-audio = "Firmware-qcom"
LICENSE:${PN}-qcom-sm8250-compute = "Firmware-qcom"

FILES:${PN}-qcom-license   = "${libdir}/firmware/LICENSE.qcom ${libdir}/firmware/qcom/NOTICE.txt*"
FILES:${PN}-qcom-yamato-license = "${libdir}/firmware/LICENSE.qcom_yamato*"
FILES:${PN}-qcom-venus-1.8 = "${libdir}/firmware/qcom/venus-1.8/*"
FILES:${PN}-qcom-venus-4.2 = "${libdir}/firmware/qcom/venus-4.2/*"
FILES:${PN}-qcom-venus-5.2 = "${libdir}/firmware/qcom/venus-5.2/*"
FILES:${PN}-qcom-venus-5.4 = "${libdir}/firmware/qcom/venus-5.4/*"
FILES:${PN}-qcom-vpu-1.0 = "${libdir}/firmware/qcom/vpu-1.0/*"
FILES:${PN}-qcom-vpu-2.0 = "${libdir}/firmware/qcom/vpu-2.0/*"
FILES:${PN}-qcom-adreno-a2xx = "${libdir}/firmware/qcom/leia_*.fw* ${libdir}/firmware/qcom/yamato_*.fw*"
FILES:${PN}-qcom-adreno-a3xx = "${libdir}/firmware/qcom/a3*_*.fw* ${libdir}/firmware/a300_*.fw*"
FILES:${PN}-qcom-adreno-a4xx = "${libdir}/firmware/qcom/a4*_*.fw*"
FILES:${PN}-qcom-adreno-a530 = "${libdir}/firmware/qcom/a530*.* ${libdir}/firmware/qcom/apq8096/a530*.*"
FILES:${PN}-qcom-adreno-a630 = "${libdir}/firmware/qcom/a630*.* ${libdir}/firmware/qcom/sdm845/a630*.*"
FILES:${PN}-qcom-adreno-a650 = "${libdir}/firmware/qcom/a650*.* ${libdir}/firmware/qcom/sm8250/a650*.*"
FILES:${PN}-qcom-adreno-a660 = "${libdir}/firmware/qcom/a660*.*"
FILES:${PN}-qcom-apq8096-audio = "${libdir}/firmware/qcom/apq8096/adsp*.*"
FILES:${PN}-qcom-apq8096-modem = "${libdir}/firmware/qcom/apq8096/mba.mbn* ${libdir}/firmware/qcom/apq8096/modem*.* ${libdir}/firmware/qcom/apq8096/wlanmdsp.mbn*"
FILES:${PN}-qcom-sc8280xp-lenovo-x13s-compat = "${libdir}/firmware/qcom/LENOVO/21BX"
FILES:${PN}-qcom-sc8280xp-lenovo-x13s-audio = "${libdir}/firmware/qcom/sc8280xp/LENOVO/21BX/*adsp*.* ${libdir}/firmware/qcom/sc8280xp/LENOVO/21BX/battmgr.jsn*"
FILES:${PN}-qcom-sc8280xp-lenovo-x13s-adreno = "${libdir}/firmware/qcom/sc8280xp/LENOVO/21BX/qcdxkmsuc8280.mbn*"
FILES:${PN}-qcom-sc8280xp-lenovo-x13s-compute = "${libdir}/firmware/qcom/sc8280xp/LENOVO/21BX/*cdsp*.*"
FILES:${PN}-qcom-sc8280xp-lenovo-x13s-sensors = "${libdir}/firmware/qcom/sc8280xp/LENOVO/21BX/*slpi*.*"
FILES:${PN}-qcom-sdm845-audio = "${libdir}/firmware/qcom/sdm845/adsp*.*"
FILES:${PN}-qcom-sdm845-compute = "${libdir}/firmware/qcom/sdm845/cdsp*.*"
FILES:${PN}-qcom-sdm845-modem = "${libdir}/firmware/qcom/sdm845/mba.mbn* ${libdir}/firmware/qcom/sdm845/modem*.* ${libdir}/firmware/qcom/sdm845/wlanmdsp.mbn*"
FILES:${PN}-qcom-sm8250-audio = "${libdir}/firmware/qcom/sm8250/adsp*.*"
FILES:${PN}-qcom-sm8250-compute = "${libdir}/firmware/qcom/sm8250/cdsp*.*"

RDEPENDS:${PN}-qcom-venus-1.8 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-venus-4.2 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-venus-5.2 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-venus-5.4 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-vpu-1.0 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-vpu-2.0 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-adreno-a2xx = "${PN}-qcom-license ${PN}-qcom-yamato-license"
RDEPENDS:${PN}-qcom-adreno-a3xx = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-adreno-a4xx = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-adreno-a530 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-adreno-a630 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-adreno-a650 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-adreno-a660 = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-apq8096-audio = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-apq8096-modem = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-sc8280xp-lenovo-x13s-audio = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-sc8280xp-lenovo-x13s-adreno = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-sc8280xp-lenovo-x13s-compute = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-sc8280xp-lenovo-x13s-sensors = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-sdm845-audio = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-sdm845-compute = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-sdm845-modem = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-sm8250-audio = "${PN}-qcom-license"
RDEPENDS:${PN}-qcom-sm8250-compute = "${PN}-qcom-license"

RRECOMMENDS:${PN}-qcom-sc8280xp-lenovo-x13s-audio = "${PN}-qcom-sc8280xp-lenovo-x13s-compat"
RRECOMMENDS:${PN}-qcom-sc8280xp-lenovo-x13s-adreno = "${PN}-qcom-sc8280xp-lenovo-x13s-compat"
RRECOMMENDS:${PN}-qcom-sc8280xp-lenovo-x13s-compute = "${PN}-qcom-sc8280xp-lenovo-x13s-compat"
RRECOMMENDS:${PN}-qcom-sc8280xp-lenovo-x13s-sensors = "${PN}-qcom-sc8280xp-lenovo-x13s-compat"

FILES:${PN}-liquidio = "${libdir}/firmware/liquidio"

# For Amlogic VDEC
LICENSE:${PN}-amlogic-vdec = "Firmware-amlogic_vdec"
FILES:${PN}-amlogic-vdec-license = "${libdir}/firmware/LICENSE.amlogic_vdec*"
FILES:${PN}-amlogic-vdec = "${libdir}/firmware/meson/vdec/*"
RDEPENDS:${PN}-amlogic-vdec = "${PN}-amlogic-vdec-license"

# For other firmwares
# Maybe split out to separate packages when needed.
LICENSE:${PN} = "\
    Firmware-Abilis \
    & Firmware-agere \
    & Firmware-amdgpu \
    & Firmware-amd-ucode \
    & Firmware-amlogic_vdec \
    & Firmware-atmel \
    & Firmware-ca0132 \
    & Firmware-cavium \
    & Firmware-chelsio_firmware \
    & Firmware-cw1200 \
    & Firmware-dib0700 \
    & Firmware-e100 \
    & Firmware-ene_firmware \
    & Firmware-fw_sst_0f28 \
    & Firmware-go7007 \
    & Firmware-hfi1_firmware \
    & Firmware-ibt_firmware \
    & Firmware-it913x \
    & Firmware-IntcSST2 \
    & Firmware-kaweth \
    & Firmware-moxa \
    & Firmware-myri10ge_firmware \
    & Firmware-nvidia \
    & Firmware-OLPC \
    & Firmware-ath9k-htc \
    & Firmware-phanfw \
    & Firmware-qat \
    & Firmware-qcom \
    & Firmware-qla1280 \
    & Firmware-qla2xxx \
    & Firmware-r8a779x_usb3 \
    & Firmware-radeon \
    & Firmware-ralink_a_mediatek_company_firmware \
    & Firmware-ralink-firmware \
    & Firmware-imx-sdma_firmware \
    & Firmware-siano \
    & Firmware-ti-connectivity \
    & Firmware-ti-keystone \
    & Firmware-ueagle-atm4-firmware \
    & Firmware-wl1251 \
    & Firmware-xc4000 \
    & Firmware-xc5000 \
    & Firmware-xc5000c \
    & WHENCE \
"

FILES:${PN}-license += "${libdir}/firmware/LICEN*"
FILES:${PN} += "${libdir}/firmware/*"
RDEPENDS:${PN} += "${PN}-license"
RDEPENDS:${PN} += "${PN}-whence-license"

# Make linux-firmware depend on all of the split-out packages.
# Make linux-firmware-iwlwifi depend on all of the split-out iwlwifi packages.
# Make linux-firmware-ibt depend on all of the split-out ibt packages.
python populate_packages:prepend () {
    firmware_pkgs = oe.utils.packages_filter_out_system(d)
    d.appendVar('RRECOMMENDS:linux-firmware', ' ' + ' '.join(firmware_pkgs))

    iwlwifi_pkgs = filter(lambda x: x.find('-iwlwifi-') != -1, firmware_pkgs)
    d.appendVar('RRECOMMENDS:linux-firmware-iwlwifi', ' ' + ' '.join(iwlwifi_pkgs))

    ibt_pkgs = filter(lambda x: x.find('-ibt-') != -1, firmware_pkgs)
    d.appendVar('RRECOMMENDS:linux-firmware-ibt', ' ' + ' '.join(ibt_pkgs))
}

# Firmware files are generally not ran on the CPU, so they can be
# allarch despite being architecture specific
INSANE_SKIP = "arch"

# Don't warn about already stripped files
INSANE_SKIP:${PN} = "already-stripped"

# No need to put firmware into the sysroot
SYSROOT_DIRS_IGNORE += "${libdir}/firmware"
