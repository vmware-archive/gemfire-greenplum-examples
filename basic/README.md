#  Gemfire-Greenplum connector example

This basic example demonstrates how to run GemFire-Greenplum connector.

The setup consists of GemFire and Greenplum DB (GPDB) cluster.

- Two GemFire servers host a region.
- GPDB cluster that has two segments with pre-created table "basic"

## Steps
1. From the `````` directory, start the dockers with both GPDB and GemFire cluster:
        $ docker-compose up

2. From the ```/basic directory, create the database and table in GPDB cluster
        $ scripts/setupDB.sh

	Gemfire/Geode version: 9.0.3
	psql:./sample_table.sql:1: NOTICE:  table "basic" does not exist, skipping
	DROP TABLE
	CREATE TABLE
	INSERT 0 1
	...

3. From the ```/basic``` directory, start the locator and two servers:

        $ scripts/startAll.sh

2. Run the the import script:

        $ scripts/importFromGPDB.sh
        ...
	(2) Executing - import gpdb --region=/basic

	Imported 5 objects in 1.968s.

        ...
	Result
	------
	5

3. Run the export script:

        $ scripts/exportToGPDB.sh
        ...
	(2) Executing - export gpdb --region=/basic --type=UPSERT

	Exported 5 objects in 0.569s.
	 count
	-------
     	5
	(1 row)
        ...




6. Shutdown the system:

        $ scripts/stopAll.sh

This example is a simple demonstration on basic Gemfire-Greenplum connector
