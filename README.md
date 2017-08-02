# GemFire and Greenplum with Gemfire-Greenplum connector(GGC) examples

This is the home of examples that are bundled with the project. Contributions<sup>[2]</sup> and corrections are welcome. Please send your questions/suggestions to [kochan@pivotal.io](mailto:kochan@pivotal.io)

## Contents
1. [Overview](#overview)
2. [How to Get GemFire and GemFire-Greenplum connector](#obtaining)
3. [How to install GemFire and GemFire-Greenplum connector](#install)
3. [Examples](#examples)
4. [Documentation](#documentation)

## <a name="overview"></a>Overview
[Pivotal Gemfire-Greenplum Connector](https://pivotal.io/pivotal-gemfire) enables users to more easily tackle use cases that require a blend of analytical and transactional processing, such as fraud detection, recommendation and IoT data collection system

Use Cases:
*  Cache data reads in Gemfire
*  High speed data ingestion into Greenplum DB

## <a name="obtaining"></a>How to get GemFire and GGC

Please visit Pivotal Network at https://network.pivotal.io for the latest versions of this Pivotal product.

*  Login to https://network.pivotal.io/
*  Access GemFire product link at  https://network.pivotal.io/products/pivotal-gemfire
*  Download Pivotal GemFire zip and GemFire-Greenplum connector

## <a name="install"></a>How to install GemFire and GGC (TBD)

* GemFire installation, follow this [link](http://gemfire.docs.pivotal.io/geode/getting_started/installation/install_standalone.html#concept_0129F6A1D0EB42C4A3D24861AF2C5425)

* GemFire-Greenplum connector, follow this [link](http://ggc.docs.pivotal.io/ggc/installation.html)

## <a name="run"></a>How to run docker-compose for this demo (TBD)
1. Run this command to load docker-compose.yml. This task assumes you have preinstalled docker-compose
```
$ docker-compose up
office-4-39:gemfire-greenplum-examples kochan$ docker-compose up
Recreating gemfiregreenplumexamples_gemfire_1 ...
Recreating gemfiregreenplumexamples_gemfire_1 ... done
Recreating gemfiregreenplumexamples_gpdb_1 ...
Recreating gemfiregreenplumexamples_gpdb_1 ... done
Attaching to gemfiregreenplumexamples_gemfire_1, gemfiregreenplumexamples_gpdb_1
...
```

2. Verify the docker compose processes are running by following this command.

```
$ docker-compose ps
               Name                             Command               State                                                                                     Ports
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
gemfiregreenplumexamples_gemfire_1   gfsh                             Up      0.0.0.0:10334->10334/tcp, 0.0.0.0:1099->1099/tcp, 0.0.0.0:40404->40404/tcp, 0.0.0.0:7070->7070/tcp, 0.0.0.0:8080->8080/tcp
gemfiregreenplumexamples_gpdb_1      /bin/sh -c echo "127.0.0.1 ...   Up      0.0.0.0:9022->22/tcp, 0.0.0.0:40000->40000/tcp, 0.0.0.0:40001->40001/tcp, 0.0.0.0:40002->40002/tcp, 0.0.0.0:5005->5005/tcp, 0.0.0.0:5010->5010/tcp, 0.0.0.0:5432->5432/tcp
```

3. You can access GemFire/Geode docker by following the command below.

```
$ docker exec -it gemfiregreenplumexamples_gemfire_1 bin/bash
```

4. Use another shell, in order to execute the Greenplum docker

```
$ docker exec -it gemfiregreenplumexamples_gpdb_1 bin/bash
```

## <a name="examples"></a>Examples
You can run these examples once you have successfully executed docker-compose.
For example, follow the example-[Basic](basic/README.md)

### Basics
*  [Basic](basic/README.md)
*  [Export](export/README.md)

## <a name="documentation"></a>Documentation
* Greenplum documentation(http://gpdb.docs.pivotal.io/43120/common/welcome.html)
* GemFire - gfsh Commands (http://gemfire.docs.pivotal.io/gemfire/tools_modules/gfsh/gfsh_quick_reference.html)
* GemFire-GreenPlum connector documentation(http://ggc.docs.pivotal.io/ggc/about_ggc.html)
