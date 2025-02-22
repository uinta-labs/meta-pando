#ifndef HEADER_ENV_PANDO_H
#define HEADER_ENV_PANDO_H

/*
 * Assumes defined:
 *     pando_kernel_load_addr - kernel load address as we use the same
 *                              to load the env file
 *     pando_root_part        - existing in the env file to import (optional)
 *     pando_flasher_skip     - if set to 1 by integration layer, skips flasher detection (optional)
 * Defines:
 *     pando_set_kernel_root  - needs to be integrated with board
 *                              specific configuration
 *     set_os_cmdline         - Sets cmdline parameters as required by the OS
 *                              in os_cmdline env variable.
 *                              Needs to be integrated with board specific
 *                              configuration so that os_cmdline is part of the
 *                              final cmdline/bootargs passed to the kernel.
 *                              This needs to run after pando_set_kernel_root
 *                              as it can use the device scan which is
 *                              performed in pando_set_kernel_root. Otherwise
 *                              an additional scan is needed.
 *     pando_kernel_root      - the root kernel argument
 *     pando_dev_type         - device type from where we boot (e.g. mmc, usb etc.)
 *     pando_dev_index        - device index to be used at boot
 * Other notes:
 *     os_bc_wr_sz            - The exact size of 'bootcount=X' to fatwrite
 *
 */

#include <config_pando.h>

#define PANDO_ENV \
       "pando_env_file=" __stringify(PANDO_ENV_FILE) "\0" \
       "pando_extra_env_file=" __stringify(PANDO_EXTRA_ENV_FILE) "\0" \
       "os_overlap_file=" __stringify(OS_OVERLAP_FILE) "\0" \
       "os_bc_file=" __stringify(OS_BOOTCOUNT_FILE) "\0" \
       "os_bc_skip=" __stringify(OS_BOOTCOUNT_SKIP) "\0" \
       "os_bc_inced=0 \0" \
       "os_bc_lim=" __stringify(OS_BOOTCOUNT_LIMIT) "\0" \
       "os_bc_wr_sz=0xd \0" \
       "upgrade_available=0 \0" \
       "pando_flasher_flag_file=" __stringify(PANDO_FLASHER_FLAG_FILE) "\0" \
       "pando_image_flag_file=" __stringify(PANDO_IMAGE_FLAG_FILE) "\0" \
       "pando_uboot_devices=" __stringify(PANDO_UBOOT_DEVICES) "\0" \
       "pando_uboot_device_types=" __stringify(PANDO_UBOOT_DEVICE_TYPES) "\0" \
       "pando_boot_part=" __stringify(PANDO_BOOT_PART) "\0" \
       "pando_root_part=" __stringify(PANDO_DEFAULT_ROOT_PART) "\0" \
       "base_os_cmdline=" __stringify(BASE_OS_CMDLINE) "\0" \
       "pando_device_kernel_addr_var=" __stringify(PANDO_DEVICE_KERNEL_ADDR_VAR) "\0"\
       "pando_device_fdt_addr_var=" __stringify(PANDO_DEVICE_FDT_ADDR_VAR) "\0"\
       "pando_flasher_skip=0 \0" \
       \
       "pando_find_root_part_uuid=" \
               "fsuuid ${pando_dev_type} ${pando_dev_index}:${pando_root_part} pando_root_part_uuid\0" \
       \
       "pando_load_env_file=" \
               "echo Loading ${pando_env_file} from ${pando_dev_type} device ${pando_dev_index} partition ${pando_boot_part};" \
               "fatload ${pando_dev_type} ${pando_dev_index}:${pando_boot_part} ${pando_kernel_load_addr} ${pando_env_file};\0" \
       "pando_load_extra_env_file=" \
               "echo Loading ${pando_extra_env_file} from ${pando_dev_type} device ${pando_dev_index} partition ${pando_boot_part};" \
               "fatload ${pando_dev_type} ${pando_dev_index}:${pando_boot_part} ${pando_kernel_load_addr} ${pando_extra_env_file};\0" \
       "os_load_bootcount_file=" \
               "echo Loading ${os_bc_file} from ${pando_dev_type} device ${pando_dev_index} partition ${pando_boot_part};" \
               "fatload ${pando_dev_type} ${pando_dev_index}:${pando_boot_part} ${pando_kernel_load_addr} ${os_bc_file};\0" \
       \
       "pando_import_env_file=" \
               "echo Import ${pando_env_file} in environment;" \
               "env import -t ${pando_kernel_load_addr} ${filesize}\0" \
       \
       "pando_import_extra_env_file=" \
               "echo Import ${pando_extra_env_file} in environment;" \
               "env import -t ${pando_kernel_load_addr} ${filesize}\0" \
       \
       "pando_import_scan_dev_extra_env_file=" \
               "if fatload ${pando_scan_dev_type} ${pando_scan_dev_index}:${pando_boot_part} ${pando_kernel_load_addr} ${pando_extra_env_file}; then " \
                   "run pando_import_extra_env_file; " \
                   "echo Imported ${pando_extra_env_file} from scanned device ${pando_scan_dev_type}:${pando_scan_dev_index} in environment;" \
               "else " \
                   "echo File ${pando_extra_env_file} not found on scanned device ${pando_scan_dev_type}:${pando_scan_dev_index}; " \
               "fi; \0" \
       "pando_save_overlap_file=if fatwrite ${pando_dev_type} ${pando_dev_index}:${pando_boot_part} ${pando_kernel_load_addr} ${os_overlap_file} 0xd; then; else; echo OVERLAP FILE WRITE FAILED ; fi;\0" \
       "pando_kernel_load_crc_save=if pando_crc32 save ${pando_device_kernel_addr_var}; then; else run pando_save_overlap_file; fi;\0" \
       "pando_kernel_load_crc_check=if pando_crc32 check ${pando_device_kernel_addr_var}; then; else run pando_save_overlap_file; fi;\0" \
       "pando_fdt_load_crc_save=if pando_crc32 save ${pando_device_fdt_addr_var}; then; else run pando_save_overlap_file; fi;\0" \
       "pando_fdt_load_crc_check=if pando_crc32 check ${pando_device_fdt_addr_var}; then; else run pando_save_overlap_file; fi;\0" \
       "os_import_bootcount_file=" \
               "echo Import ${os_bc_file} in environment;" \
               "env import -t ${pando_kernel_load_addr} ${filesize}\0" \
       \
       "os_inc_bc_save=" \
              "if test ${os_bc_skip} = 0 && test ${os_bc_inced} = 0 && test ${upgrade_available} = 1; then " \
                     "setexpr bootcount ${bootcount} + 1;" \
                     "env set os_bc_inced 1;" \
                     "echo bootcount=${bootcount} now;" \
                     "env export -t ${pando_kernel_load_addr} bootcount;" \
                     "if fatwrite ${pando_dev_type} ${pando_dev_index}:${pando_boot_part} ${pando_kernel_load_addr} ${os_bc_file} ${os_bc_wr_sz}; then; else; echo FATWRITE FAILED ; fi;" \
                     "echo bootcount=${bootcount} written to ${pando_dev_type} ${pando_dev_index}:${pando_boot_part} ${os_bc_file};" \
              "fi;\0" \
       \
       "pando_flasher_detect=" \
               "if test \"${pando_scan_dev_type}\" = usb ; then " \
	               "usb start ; " \
               "fi; " \
               "fatload ${pando_scan_dev_type} ${pando_scan_dev_index}:${pando_boot_part} ${pando_kernel_load_addr} ${pando_flasher_flag_file};\0" \
       \
       "pando_image_detect=" \
               "if test \"${pando_scan_dev_type}\" = usb ; then " \
                       "usb start ; " \
               "fi; " \
               "fatload ${pando_scan_dev_type} ${pando_scan_dev_index}:${pando_boot_part} ${pando_kernel_load_addr} ${pando_image_flag_file};\0" \
       \
       "pando_scan_devs=" \
               "echo Scanning ${pando_uboot_device_types} devices ${pando_uboot_devices}; " \
               "for pando_scan_dev_type in ${pando_uboot_device_types}; do " \
                       "for pando_scan_dev_index in ${pando_uboot_devices}; do " \
                               "run pando_import_scan_dev_extra_env_file; " \
                               "if test ${pando_flasher_skip} = 0 && run pando_flasher_detect; then " \
                                       "setenv pando_flasher_dev_index ${pando_scan_dev_index}; " \
                                       "setenv pando_dev_type ${pando_scan_dev_type}; " \
                                       "exit; " \
                               "else; " \
                                       "if test -n \"${pando_image_dev_index}\"; then ;" \
                                               "else if run pando_image_detect; then " \
                                                       "setenv pando_image_dev_index ${pando_scan_dev_index}; " \
                                                       "setenv pando_dev_type ${pando_scan_dev_type}; " \
                                               "fi; " \
                                       "fi; " \
                               "fi; " \
                       "done;" \
               "done;\0"  \
       \
       "pando_set_dev_index=" \
               "run pando_scan_devs; " \
               "if test -n ${pando_flasher_dev_index}; then " \
                       "echo Found pando flasher on ${pando_dev_type} ${pando_flasher_dev_index}; "\
                       "setenv bootparam_flasher flasher; "\
                       "setenv pando_dev_index ${pando_flasher_dev_index}; "\
               "else; "\
                       "if test -n \"${pando_image_dev_index}\"; then " \
                               "echo Found resin image on ${pando_dev_type} ${pando_image_dev_index}; "\
                               "setenv pando_dev_index ${pando_image_dev_index}; "\
                       "else; " \
                               "echo ERROR: Could not find a resin image of any sort.; " \
                       "fi; " \
               "fi;\0" \
       \
       "pando_inject_env_file=" \
               "if run pando_load_env_file; then " \
                       "run pando_import_env_file;" \
               "fi;" \
               "if run pando_load_extra_env_file; then " \
                       "run pando_import_extra_env_file;" \
               "fi;" \
               "if run os_load_bootcount_file; then " \
                       "run os_import_bootcount_file;" \
               "else; " \
                       "echo No bootcount.env file. Setting bootcount=0 in environment;" \
                       "env set bootcount 0;" \
               "fi;\0" \
       \
       "pando_check_altroot=" \
              "setexpr pando_roota ${pando_boot_part} + 1; " \
              "setexpr pando_rootb ${pando_boot_part} + 2; " \
              "run os_inc_bc_save;" \
              "if test -n ${os_bc_lim} && test -n ${bootcount}; then " \
                      "if test ${bootcount} -gt ${os_bc_lim}; then " \
                               "echo WARNING! BOOTLIMIT EXCEEDED. SWITCHING TO PREVIOUS ROOT;" \
                               "echo WARNING! was: pando_root_part=${pando_root_part};" \
                               "if test ${pando_root_part} = ${pando_roota}; then "\
                                       "env set pando_root_part ${pando_rootb}; " \
                               "else; "\
                                       "env set pando_root_part ${pando_roota}; " \
                               "fi;" \
                               "echo WARNING! now: pando_root_part=${pando_root_part};" \
                      "fi;" \
              "fi;\0" \
       \
       "set_os_cmdline=" \
               "setenv os_cmdline ${base_os_cmdline} ${bootparam_flasher} ${extra_os_cmdline};\0" \
       "pando_set_kernel_root=" \
               "run pando_set_dev_index;" \
               "run pando_inject_env_file;" \
               "run pando_check_altroot;" \
               "run pando_find_root_part_uuid;" \
               "setenv pando_kernel_root root=UUID=${pando_root_part_uuid}\0"

#endif /* HEADER_ENV_PANDO_H */

