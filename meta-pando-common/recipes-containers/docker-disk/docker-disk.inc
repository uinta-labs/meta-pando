PANDO_CHECK_CONN_URL ?= "index.docker.io"

# Check if host can reach a specific URL
# Used for connectivity check
def connected(d):
    import socket

    REMOTE_SERVER = d.getVar('PANDO_CHECK_CONN_URL', True)
    try:
        host = socket.gethostbyname(REMOTE_SERVER)
        testSocket = socket.create_connection((host, 80), 2)
        testSocket.close()
        return "yes"
    except:
        pass
    return "no"

# Check if docker is running and usable for current user
def usable_docker(d):
    import subprocess

    # Test docker execute permission
    cmd = "docker images > /dev/null 2>&1"
    child = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE)
    cmd_output = child.communicate()[0]
    if child.returncode != 0:
        return "no"

    return "yes"

python () {
    # We need docker on host
    if usable_docker(d) != "yes":
        bb.fatal("docker-disk: Docker needs to run on your host and current user must be able to use it.")
}

HOSTAPP_PLATFORM:armv5 = "linux/arm/v5"
HOSTAPP_PLATFORM:armv6 = "linux/arm/v5"
HOSTAPP_PLATFORM:armv7a = "linux/arm/v7"
HOSTAPP_PLATFORM:armv7ve = "linux/arm/v7"
HOSTAPP_PLATFORM:789-aarch64 = "linux/arm64"
HOSTAPP_PLATFORM:x86 = "linux/i386"
HOSTAPP_PLATFORM:x86-64 = "linux/amd64"
HOSTAPP_PLATFORM:intel-quark = "linux/i386"

### HOSTAPP_PLATFORM = "${@d.getVar('TARGET_ARCH') == 'aarch64' and 'linux/arm64' or \
###                        d.getVar('TARGET_ARCH') == 'arm' and \
###                        ('armv7' in d.getVar('TUNE_FEATURES') and 'linux/arm/v7' or 'linux/arm/v5') or \
###                        d.getVar('TARGET_ARCH') == 'x86_64' and 'linux/amd64' or \
###                        d.getVar('TARGET_ARCH') == 'x86' and 'linux/i386' or ''}"

HOSTEXT_IMAGES ?= ""
