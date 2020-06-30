# Secret Keeper

## About
This repository will generate a module in Ignition that allows you to store and retrieve password, API tokens, or other sensitive information within Ignition.
Secrets are stored (hashed and salted) in the `GI_SECRETS` table of Ignition's internal database.

### Warning
While this module stores the password encrypted, that encryption is reversible. Anyone that has a backup of your Ignition Gateway can gain access to the stored values.
In addition, script authors can (within the gateway scope) retrieve secret values as plain strings, which could be accidentally emitted in logs or error messages.

## Building
### Requirements
* Java 11
* If you choose to use Maven to build, it must be installed on your system

### Recommended
* [Requirements from the `ignition-sdk-examples` repo](https://github.com/inductiveautomation/ignition-sdk-examples#requirements)

### Building

#### Maven
1. Open a terminal window or command prompt
2. Change directory to this cloned repository
3. Run the command: `mvn clean package`

This will create an unsigned module file `secret-keeper-build/target/Secret-Keeper-unsigned.modl`. The `ignition-sdk-examples` repository details how to set up Ignition to allow unsigned modules to be installed. 
If you need to sign a module, please look at [Inductive Automation's module-signer repository](https://github.com/inductiveautomation/module-signer).

#### Gradle
1. Open a terminal window or command prompt
2. Change directory to this cloned repository
3. Run the `gradlew` executable; *Nix systems: `./gradlew`, Windows `gradlew.bat`.
