#!/bin/bash
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

set -e

current=`pwd`
scriptpath=`dirname $0`
cd `dirname $0`

. ./setEnv.sh

cd $current

#export GEODE_LOCATOR_PORT="${GEODE_LOCATOR_PORT:-10334}"
# start a locator
gfsh start locator --name=locator1 --mcast-port=0 bind-address=${GEODE_IP} --port=${GEODE_LOCATOR_PORT} --include-system-classpath

# start 2 servers on a random available port
for N in {1..2}
do
 gfsh start server bind-address=${GEODE_SERVERIP} --locators=${GEODE_IP}[${GEODE_LOCATOR_PORT}] --name=server$N  --server-port=0 --mcast-port=0 --cache-xml-file=$scriptpath/server-cache.xml --include-system-classpath
done

# create a region using GFSH
gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "list members"

gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "list regions"

exit 0

