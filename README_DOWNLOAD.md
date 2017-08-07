# Download GemFire and GemFire-Greenplum Connector via script

This readme describes how to use script to automatically download binaries from Pivotal Network

## Contents
1. [Pre-requisites](#pre-requisites)
2. [How to configure script](#configure)
3. [How to run the script](#run)
4. [Documentation](#documentation)

## <a name="pre-requisites"></a>Pre-requisites

- Pivotal Network account.
- API token associated to your Pivotal Network account.
- Gradle preinstalled on your system

## <a name="configure"></a>How to configure script
### Configure the script
-  Edit gradle.properties file in the root folder.
-  Replace the "REPLACE_ME" in this pivotal.network.token with your API Token that is provided by Pivotal Network.


## <a name="run"></a>How to run the script
1. Use this command to download GemFire binaries
```
gradle -b downloadbinary.gradle downloadGemfireFile verifyGemfireFile unzipGemfireFile
```
The expected response is shown below. You can access the GemFire binary at local build directory.
```
:downloadGemfireFile
Download https://network.pivotal.io/api/v2/products/pivotal-gemfire/releases/4834/product_files/17411/download
:verifyGemfireFile
BUILD SUCCESSFUL
```

2. Verify the GemFire files are downloaded and moved to `libs`
```
$ ls -al ./libs
total 32
drwxr-xr-x   4 kochan  staff    136 Jul 27 11:34 .
drwxr-xr-x  30 kochan  staff   1020 Jul 26 13:57 ..
-rw-r--r--@  1 kochan  staff  12292 May 23 16:49 .DS_Store
drwxr-xr-x  12 kochan  staff    408 Jul 27 11:34 pivotal-gemfire-9.1.0
```

3. Use this command to download GemFire-Greenplum connector binaries
```
gradle -b downloadbinary.gradle downloadGGCFile verifyGGCFile copyGGCFile
```
The expected response is shown below. You can access the GemFire-Greenplum connector binary at local build directory.
```
:downloadGGCFile
Download https://network.pivotal.io/api/v2/products/pivotal-gemfire/releases/4834/product_files/15597/download
:verifyGGCFile
:copyGGCFile
BUILD SUCCESSFUL
```

4. Verify the GemFire-Greenplum connector file is downloaded and moved to `libs`
```
$ ls -al ./libs
total 7808
drwxr-xr-x   6 kochan  staff      204 Jul 27 11:36 .
drwxr-xr-x  30 kochan  staff     1020 Jul 26 13:57 ..
-rw-r--r--@  1 kochan  staff    12292 May 23 16:49 .DS_Store
-rw-r--r--   1 kochan  staff  1977727 Jul 27 11:36 gemfire-greenplum-2.4.0.jar
-rw-r--r--   1 kochan  staff  1999087 Jul 27 11:36 gemfire-greenplum-3.0.0.jar
drwxr-xr-x  12 kochan  staff      408 Jul 27 11:34 pivotal-gemfire-9.1.0
```

5. Use this command to view list of tasks in Gradle
```
gradle -b downloadbinary.gradle tasks --all
```

```
Welcome to Gradle 3.3.
....
Other tasks
-----------
downloadGemfireFile
downloadGGCFile
printProps
verifyGemfireFile
verifyGGCFile
```

## <a name="documentation"></a>Documentation
* Greenplum documentation(http://gpdb.docs.pivotal.io/43120/common/welcome.html)
* GemFire - gfsh Commands (http://gemfire.docs.pivotal.io/gemfire/tools_modules/gfsh/gfsh_quick_reference.html)
* GemFire-Greenplum connector documentation(http://ggc.docs.pivotal.io/ggc/about_ggc.html)
