#!/bin/bash
# This script runs your docker image, assuming it is already built
# If you don't have a current docker image, use build-docker.sh
# 5432 : GPDB port
# 5005 : java debug port
# 5010 : jmx connection port
# Docker image from :https://hub.docker.com/r/pivotaldata/gpdb-base/
#
# User: gpadmin Password: pivotal
#
# or root/pivotal

set -euo pipefail


OSTYPE="`uname`"
# show bridge between container and host


if [[ "$OSTYPE" == "linux-gnu" ]]; then
	# show bridge between container and host
	docker0=`ip addr show docker0`
	hostip=$(ip route show | awk '/default/ {print $3}')
	echo "Host IP in the docker bridge: $hostip"

	echo "Setting firewall rule to permit access to any ports on the host from docker container"
	sudo iptables -A INPUT -i docker0 -j ACCEPT
elif [[ "$OSTYPE" == "darwin"* ]]; then
        # Mac OSX
	echo "TBD"
else
	#unknown
	echo "TBD"
fi




docker run -t -i --rm=true \
   -p 5432:5432 \
   -p 5005:5005 \
   -p 5010:5010 \
   -p 9022:22 \
   -p 40000:40000 \
   -p 40001:40001 \
   -p 40002:40002 \
   pivotaldata/gpdb-base
