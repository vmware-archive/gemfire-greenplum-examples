#!/bin/bash
#
#

set -e

current=`pwd`

cd `dirname $0`

. ./setEnv.sh

cd $current


gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "list regions"

gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "list functions"

psql -h ${GREENPLUM_HOST} -U ${GREENPLUM_USER} -d ${GREENPLUM_DB} -c "select count(*) from basic_ao"

gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "export gpdb --region=/basicAO --type=INSERT_MISSING"

gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "query --query=\"select count(*) from /basicAO\""

psql -h ${GREENPLUM_HOST} -U ${GREENPLUM_USER} -d ${GREENPLUM_DB} -c "select count(*) from basic_ao"

gfsh -e "connect --locator=localhost[${GEODE_LOCATOR_PORT}]" -e "export gpdb --region=/basicAO --type=INSERT_MISSING"

psql -h ${GREENPLUM_HOST} -U ${GREENPLUM_USER} -d ${GREENPLUM_DB} -c "select count(*) from basic_ao"
exit 0

