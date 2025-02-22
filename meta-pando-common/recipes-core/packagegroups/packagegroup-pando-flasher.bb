SUMMARY = "Pando Flasher Package Group"
LICENSE = "Apache-2.0"

PR = "r1"

inherit packagegroup

PANDO_INIT_PACKAGE ?= "pando-init-flasher"
PANDO_MOUNTS ?= "pando-mounts-flasher"
# PANDO_REGISTER ?= "pando-device-register"

include packagegroup-pando.inc
