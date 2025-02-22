#
# Sanity checks for resinOS builds
#
# Copyright (C) 2017 resin.io
# Author: Andrei Gherzan <andrei@resin.io>
#
# Licensed under the Apache-2.0 license, see COPYING.Apache-2.0 for details


def pando_build_configuration(d):
    success = True
    if d.getVar('PACKAGE_CLASSES', True) != "package_ipk":
        bb.warn("Pando OS distro depends on opkg packages (ipk). Make sure PACKAGE_CLASSES is set on package_ipk.")
    if d.getVar('DOCKER_STORAGE', True):
        bb.warn("DOCKER_STORAGE variable was replaced by BALENA_STORAGE. Please update your build configuration.")
    if d.getVar('BALENA_STORAGE', True) not in ['overlay2']:
        bb.error("Pando OS supports only overlay2 as the balena storage driver.")
        success = False
    if d.getVar('PANDO_CONNECTABLE', True) or d.getVar('PANDO_CONNECTABLE_SERVICES', True) or d.getVar('PANDO_CONNECTABLE_ENABLE_SERVICES', True):
        bb.warn("Your build configuration uses PANDO_CONNECTABLE* variables. These variables are no longer used. There is only one type of resinOS image type which is unconnected by default. The os-config tool is used to configure the resinOS image for connectivity to a resin instance.")
    if d.getVar('PANDO_DEPRECATED_YOCTO_LAYER', True) == "1":
        bb.warn("Your build configuration is using a poky layer that has been deprecated by meta-balena. Please update and use a newer poky version.")
    # for deprecation in d.getVar('PANDO_DEPRECATED_COLLECTIONS').split():
    #     deprecated_collection = deprecation.split(':')[0]
    #     new_collection = deprecation.split(':')[1] if len(deprecation.split(':')) == 2 else ''
    #     if deprecated_collection in d.getVar('BBFILE_COLLECTIONS'):
    #         bb.warn("meta-%s is a deprecated layer. Please replace it in your bblayers.conf by meta-%s." % (deprecated_collection, new_collection if new_collection else 'the respective new balena layer'))
    return success

python pando_sanity_handler() {
    d = e.data
    if d.getVar('PANDO_OS_SANITY_SKIP', True) == "1":
        bb.warn('Pando OS specific sanity checks were skipped.')
        return
    if not pando_build_configuration(d):
        bb.fatal("Pando OS sanity checks failed. See above.")
}

addhandler pando_sanity_handler
pando_sanity_handler[eventmask] = "bb.event.BuildStarted"
