#!/bin/bash
#
#
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"


# Retrieve the global environmental variables
. ../../setEnv.sh

# Customize this settings for custom Gemfire/Geode
## check if locator port has been set otherwise set to default
#export GEODE_LOCATOR_PORT="${GEODE_LOCATOR_PORT:-10334}"
#export GEODE_IP="gfgpdbconnectorexamples_gemfire_1"
#export GEODE_SERVERIP=$GEODE_IP

# Customize this settings for custom Greenplum
#export GREENPLUM_HOST=gfgpdbconnectorexamples_gpdb_1
#export GREENPLUM_USER=gpadmin
export GREENPLUM_DB=export_db
#export GREENPLUM_DB_PWD=pivotal
#export PGPASSWORD=${GREENPLUM_DB_PWD}
