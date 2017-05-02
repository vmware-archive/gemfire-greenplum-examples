#!/bin/bash
#
#

set -e

current=`pwd`
scriptpath=`$0`
cd `dirname $0`

. ./setEnv.sh

cd $current


gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "list regions"

gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e 'query --query="select count(*) from /usertable"'

# gfsh -e "connect --locator=localhost[10344]" -e "import data --region=usertable --file=usertable.gfd --member=server1"
gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "import data --region=usertable --file=${scriptpath}/usertable.gfd --member=server1"

exit 0

