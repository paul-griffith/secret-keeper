### Secret Keeper
##### Information
This repository will generate a module in Ignition that allows you to store passwords within the Ignition database.
Stored passwords are encrypted and stored in the GI_SECRETS table of Ignition's SQLite database.

##### Cautions
While this module stores encrypted password within Ignition, the encryption is reversible.  Anyone that has a backup of
your Ignition Gateway can gain access to the stored values.

##### Building
###### Pre-Reqs:
* Java 11
* A Java IDE (IntelliJ or Eclipse)
* [All requirements satisfied for ignition-sdk-examples github repository](https://github.com/inductiveautomation/ignition-sdk-examples#requirements)

###### Building:
1. Open a terminal window or command prompt
2. Change directory to this cloned repository
3. Run the command: `mvn clean package`

This will create an unsigned module file `secret-keeper-build/target/Secret-Keeper-unsigned.modl`.  The
 `ignition-sdk-examples` repository details how to set up Ignition to allow unsigned modules to be installed.  If you need 
sign a module, please look at [Inductive Automation's module-signer repository](https://github.com/inductiveautomation/module-signer).
