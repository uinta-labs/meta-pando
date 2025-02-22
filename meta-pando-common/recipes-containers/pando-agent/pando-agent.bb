DESCRIPTION = "Pando Agent"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

### inherit systemd deploy pando-configurable cargo_bin
inherit systemd deploy pando-configurable

SRC_URI += "file://pando-agent.service \
           file://pando-data.mount \
		   file://pando-agent-start \
           "
### SRCREV = "cd4f3788df7b9ce1798970112f2a977c4b1e1006"
### S = "${WORKDIR}/git"
### CARGO_SRC_DIR = ""

### LIC_FILES_CHKSUM = " \
###     file://LICENSE;md5=3bfd34238ccc26128aef96796a8bbf97 \
### "

SYSTEMD_SERVICE:${PN} = " \
	pando-agent.service \
	"

FILES:${PN} += " \
	/pando-data \
	${systemd_unitdir} \
	${sysconfdir} \
	"

### DEPENDS += "jq-native curl-native protobuf-native grpc grpc-native"
DEPENDS += "jq-native curl-native"

RDEPENDS:${PN} = " \
	balena \
	bash \
	coreutils \
	curl \
	healthdog \
	pando-unique-key \
	pando-config-vars \
	systemd \
	os-helpers-api \
	"

do_compile[network] = "1"
### do_compile:prepend() {
###     export PROTOC=/Users/isaac/Dev/pando-rs/.flox/run/aarch64-darwin.pando-rs.dev/bin/protoc
### 	export PATH=$PATH:/Users/isaac/Dev/pando-rs/.flox/run/aarch64-darwin.pando-rs.dev/bin
### }

do_install[network] = "1"
do_install:append () {
	install -d ${D}/pando-data

	install -d ${D}${systemd_unitdir}/system
    # Yocto gets confused if we use strange file names - so we rename it here
	# https://bugzilla.yoctoproject.org/show_bug.cgi?id=8161
	install -c -m 0644 ${WORKDIR}/pando-data.mount ${D}${systemd_unitdir}/system/pando\\x2ddata.mount
	install -c -m 0644 ${WORKDIR}/pando-agent.service ${D}${systemd_unitdir}/system

	sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
		-e 's,@SBINDIR@,${sbindir},g' \
		-e 's,@BINDIR@,${bindir},g' \
		${D}${systemd_unitdir}/system/*.service

	install -d ${D}${bindir}
	install ${WORKDIR}/pando-agent-start ${D}${bindir}
}

include pando-agent.inc
