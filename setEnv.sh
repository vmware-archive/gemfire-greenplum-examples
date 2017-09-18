#!/bin/bash
#  This script configure default settings for Apache Geode/Pivotal GemFire and Pivotal Greenplum
#
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

CLASSLIB_PATH="$DIR/libs"\

# CHANGE ME if you have different GemFire - Greenplum connector
GGC_JAR="gemfire-greenplum-3.0.0.jar"

export CLASSPATH=$CLASSPATH:$CLASSLIB_PATH/$GGC_JAR
# Change me if you are using Pivotal GemFire
export GEMFIRE_HOME=$CLASSLIB_PATH/pivotal-gemfire-9.0.0
if [ ! -f $GEMFIRE_HOME/bin/gfsh ]; then
   echo "$GEMFIRE_HOME is not configured. Use GEODE_HOME: $GEODE_HOME"
else
	export GEODE_HOME=$GEMFIRE_HOME
fi


#echo $CLASSPATH
## check if locator port has been set otherwise set to default
export GEODE_LOCATOR_PORT="${GEODE_LOCATOR_PORT:-10334}"
export GEODE_IP="gemfiregreenplumexamples_gemfire_1"

export GEODE_SERVERIP=$GEODE_IP

# Greenplum
export GREENPLUM_HOST=gemfiregreenplumexamples_gpdb_1
export GREENPLUM_USER=gpadmin
export GREENPLUM_DB=basic_db
export GREENPLUM_DB_PWD=pivotal
export PGPASSWORD=${GREENPLUM_DB_PWD}

# Manual process to change server-cache.xml in each project.

## check if GEODE_HOME has been set
: ${GEODE_HOME?"GEODE_HOME enviroment variable needs to be set"}

## check if gfsh script is accessible and print version
: ${GEODE_HOME/bin/gfsh?"gfsh doesn't seem to be available. Please check $GEODE_HOME"}
echo "Gemfire/Geode version: `$GEODE_HOME/bin/gfsh version`"

## prefer GEODE_HOME for finding gfsh
export PATH=$GEODE_HOME/bin:$PATH


: ${GEODE_LOCATOR_PORT?}
