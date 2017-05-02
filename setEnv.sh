#!/bin/bash
#
#
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

CLASSLIB_PATH="$DIR/libs"
GGC_JAR="gemfire-greenplum-2.4.1-SNAPSHOT.jar"

export CLASSPATH=$CLASSPATH:$CLASSLIB_PATH/$GGC_JAR

#echo $CLASSPATH
## check if locator port has been set otherwise set to default
export GEODE_LOCATOR_PORT="${GEODE_LOCATOR_PORT:-10334}"
export GEODE_IP="gfgpdbconnectorexamples_gemfire_1"
export GEODE_SERVERIP=$GEODE_IP

# Greenplum
export GREENPLUM_HOST=gfgpdbconnectorexamples_gpdb_1
export GREENPLUM_USER=gpadmin
export GREENPLUM_DB=basic_db
export GREENPLUM_DB_PWD=pivotal
export PGPASSWORD=${GREENPLUM_DB_PWD}


## check if GEODE_HOME has been set
: ${GEODE_HOME?"GEODE_HOME enviroment variable needs to be set"}

## check if gfsh script is accessible and print version
: ${GEODE_HOME/bin/gfsh?"gfsh doesn't seem to be available. Please check $GEODE_HOME"}
echo "Gemfire/Geode version: `$GEODE_HOME/bin/gfsh version`"

## prefer GEODE_HOME for finding gfsh
export PATH=$GEODE_HOME/bin:$PATH


: ${GEODE_LOCATOR_PORT?}
