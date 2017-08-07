# GemFire-Greenplum connector example

This example demonstrates the export operations that are available.

An export copies entries from a Pivotal GemFire® region to a Pivotal Greenplum® (GPDB) table.

The export operation implements the functionality of one of these:

- The UPSERT functionality updates a GPDB row if the GemFire entry to be exported is already present as a GPDB row. If the GPDB row does not already exist, a new row is inserted.
- The INSERT_ALL functionality does a GPDB insert operation for each region entry in the GemFire region. Since it does not check for the existence of GPDB rows prior to each insert, the export operation will fail and throw an error if a duplicate primary key already exists in the GPDB table.
- The INSERT_MISSING functionality inserts rows into the GPDB table for GemFire region entries that are not already present in the table. It does not update any existing rows in the GPDB table.
- The UPDATE functionality updates a GPDB row if the GemFire entry to be exported is already present as a GPDB row.

You can refer to [GemFire-Greenplum documentation](http://ggc.docs.pivotal.io/ggc/toGreenplum.html)


## Steps
1. From the ```gemfire-greenplum-examples``` directory, start the dockers with both GPDB and GemFire cluster:
```
    $ docker-compose up
    Creating network "gemfiregreenplumexamples_default" with the default driver
    Creating gemfiregreenplumexamples_gemfire_1 ...
    Creating gemfiregreenplumexamples_gemfire_1 ... done
    Creating gemfiregreenplumexamples_gpdb_1 ...
    Creating gemfiregreenplumexamples_gpdb_1 ... done
    ...
```
2. From the ```/export``` directory, create the database and table in GPDB cluster
```   
    $ scripts/setupDB.sh

        GemFire/Geode version: 9.0.3
        psql:./sample_table.sql:1: NOTICE:  table "export" does not exist, skipping
        DROP TABLE
        CREATE TABLE
        INSERT 0 1
        ...
```


        GemFire/Geode version: 9.0.3
        psql:./sample_table.sql:1: NOTICE:  table "basic" does not exist, skipping
        DROP TABLE
        CREATE TABLE
        INSERT 0 1
        ...

1. From the ```gemfire-greenplum-examples/export``` directory, start the locator and two servers:
```
        $ scripts/startAll.sh
```
2. Run the producer:

        $ gradle run -Pmain=Producer
        ...
        ... 
        INFO: Done. Inserted 50 entries.

3. Run the consumer:

        $ gradle run -Pmain=Consumer
        ...
        ...
        INFO: Done. 50 entries available on the server(s).

4. Kill one of the servers:

        $ gfsh
        ...
        gfsh>connect
        gfsh>stop server --name=server1
        gfsh>quit

5. Run the consumer a second time, and notice that all the entries are still available due to replication: 

        $ gradle run -Pmain=Consumer
        ...
        ...
        INFO: Done. 50 entries available on the server(s).

6. Shutdown the system:

        $ scripts/stopAll.sh

This example is a simple demonstration on basic APIs of Geode, as well how to write tests using mocks for Geode applications.
