# Download GemFire and GemFire-Greenplum Connector via script

This readme describes how to use script that automatically downloads binaries from Pivotal Network

## Contents
1. [Pre-requisites](#pre-requisites)
2. [How to configure script](#configure)
3. [How to run the script](#run)
4. [Documentation](#documentation)

## <a name="pre-requisites"></a>Pre-requisites

- You need to have an account with Pivotal Network.
- You need to provide your API token applicable.
 1. Log on to network.pivotal.io.
 2. Click on your name tab and select "Edit Profile" to get your "API TOKEN" keys.
- Gradle preinstalled on your system

## <a name="configure"></a>How to configure script

### Configure the script
-  Edit gradle.properties file in the root folder.
-  Replace the "REPLACE_ME" in this pivotal.network.token with your API Token that is provided by Pivotal Network.


## <a name="run"></a>How to run the script
- Use this command to download GemFire binaries
```
gradle -b downloadbinary.gradle downloadGemfireFile verifyGemfireFile
```

The expected response is shown below. You can access the GemFire binary at local build directory.
```
:downloadGemfireFile
Download https://network.pivotal.io/api/v2/products/pivotal-gemfire/releases/4834/product_files/17411/download
:verifyGemfireFile

BUILD SUCCESSFUL
```

- Use this command to download GemFire-Greenplum connector binaries
```
gradle -b downloadbinary.gradle downloadGGCFile verifyGGCFile
```

The expected response is shown below. You can access the GemFire-Greenplum connector binary at local build directory.
```
:downloadGGCFile
Download https://network.pivotal.io/api/v2/products/pivotal-gemfire/releases/4834/product_files/15597/download
:verifyGGCFile

BUILD SUCCESSFUL
```


- Use this command to view list of tasks in Gradle
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
* Gemfire - gfsh Commands (http://gemfire.docs.pivotal.io/gemfire/tools_modules/gfsh/gfsh_quick_reference.html)
* Gemfire-GreenPlum connector documentation(http://ggc.docs.pivotal.io/ggc/about_ggc.html)
