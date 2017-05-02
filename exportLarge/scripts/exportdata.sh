#!/bin/bash
#
#

set -e

current=`pwd`

cd `dirname $0`

. ./setEnv.sh

cd $current


gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "list regions"

gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e 'query --query="select count(*) from /usertable"'

# gfsh -e "connect --locator=localhost[10334]" -e "export data --region=usertable --file=usertable100K.gfd --member=server1"
gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "export data --region=usertable --file=usertable.gfd --member=server1"

gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "export gpdb --region=/usertable --type=UPSERT"


exit 0

