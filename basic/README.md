#  Gemfire-Greenplum connector example

This basic example demonstrates how to run GemFire-Greenplum connector.

The setup consists of GemFire and Greenplum DB (GPDB) cluster.

- Two GemFire servers host a region.
- GPDB cluster that has two segments with pre-created table "basic"

## Steps
1. From the `gemfire-greenplum-examples` directory, start the dockers with both GPDB and GemFire cluster:
```
    $ docker-compose up
    Starting gemfiregreenplumexamples_gemfire_1 ...
    Starting gemfiregreenplumexamples_gemfire_1 ... done
    Starting gemfiregreenplumexamples_gpdb_1 ...
    Starting gemfiregreenplumexamples_gpdb_1 ... done
    Attaching to gemfiregreenplumexamples_gemfire_1, gemfiregreenplumexamples_gpdb_1
    ...
```

2. Run this command to access the Greenplum docker image
```
  $ docker exec -it gemfiregreenplumexamples_gemfire_1 bash
  [gradle@58c9eea8e1f6 ~]$
```

3. Change your directory to `/code/basic` and run this command to view the examples
```
  [gradle@58c9eea8e1f6 ~]$ cd  /code/basic
  [gradle@58c9eea8e1f6 basic]$
```
4. From the `/basic` directory, create database and table in GPDB cluster
```   
    [root@e5fd4799b3fd basic]#$ scripts/setupDB.sh

    psql:./sample_table.sql:1: NOTICE:  table "basic" does not exist, skipping
    DROP TABLE
    CREATE TABLE
    INSERT 0 1
    INSERT 0 1
    INSERT 0 1
    INSERT 0 1
    INSERT 0 1
    psql:./sample_table.sql:16: NOTICE:  table "basic2" does not exist, skipping
    DROP TABLE
    CREATE TABLE
    INSERT 0 1
    INSERT 0 1
	...
```

5. Download GemFire-Greenplum connector jar. Next, place the file (For exapmle gemfire-greenplum-3.0.0.jar) under `gemfire-greenplum-examples/`. If you are using a different file version, make sure you change the script `gemfire-greenplum-examples/setEnv.sh``` 


5. Run a script that starts a locator and two servers.  Each of the servers
hosts the partitioned region.  The gemfire-greenplum-3.0.0.jar will be placed onto the
classpath when the script starts the servers. Also, you can verify the "basic" and "basic2" regions are created.



3. From the ```/basic``` directory, start the locator and two servers:
```
        $ scripts/startAll.sh
```
2. Run the the import script:
```
        $ scripts/importFromGPDB.sh
        ...
	(2) Executing - import gpdb --region=/basic

	Imported 5 objects in 1.968s.

        ...
	Result
	------
	5
```
3. Run the export script:
```
        $ scripts/exportToGPDB.sh
        ...
	(2) Executing - export gpdb --region=/basic --type=UPSERT

	Exported 5 objects in 0.569s.
	 count
	-------
     	5
	(1 row)
        ...
```
6. Shutdown the system:
```
        $ scripts/stopAll.sh
```

This example is a simple demonstration for Gemfire-Greenplum connector.
