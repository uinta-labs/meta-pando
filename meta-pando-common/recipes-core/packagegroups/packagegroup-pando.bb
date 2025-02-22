SUMMARY = "Pando Package Group"
LICENSE = "Apache-2.0"

PR = "r1"

inherit packagegroup

PANDO_INIT_PACKAGE ?= "pando-init"
PANDO_MOUNTS ?= "pando-mounts"
# PANDO_REGISTER ?= "pando-supervisor"
# PANDO_SUPERVISOR ?= "pando-supervisor"

include packagegroup-pando.inc

##@ Prevously:     ${PANDO_SUPERVISOR}

### pando-state-reset
### systemd-zram-swap

# Additional packages
RDEPENDS:${PN} += " \
    balena \
    dosfstools \
    mobynit \
    docker-disk \
    hostapp-update \
    hostapp-update-hooks \
    pando-filesystem-expand \
    pando-info \
    pando-hostname \
    pando-unique-key \
    pando-hello-world \
    pando-mounts \
    pando-data-reset \
    timeinit \
    pando-agent \
    "
