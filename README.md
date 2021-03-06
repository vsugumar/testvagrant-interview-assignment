﻿# Interview Assignment

Phase 1, 2 and 3 of the coding assignment.

## Getting Started

Clone the repository in your local using the below command

```
git clone https://github.com/vsugumar/testvagrant-interview-assignment.git
```

### Running the Tests

Inorder to run the test ensure that maven is installed ([maven installation](https://maven.apache.org/install.html))

To run the tests in Chrome browser navigate to project’s root directory and execute the below command

```
mvn clean test
```

To run the tests in Firefox browser navigate to project’s root directory and execute the below command. (The test run is quite buggy in firefox, it is preferable to execute the tests in chrome, I’m working on the fixes)

```
mvn clean test -DBROWSER=firefox
```

## Viewing Test Reports

Post execution of test suite navigate to target/surefire-reports and view the index.html file

## Changing Configurations for Comparator

Inorder to change the variance values for Temperature and Humidity edit the corresponding values in comparator.properties file

### Note

This project is developed using a data driven approach hence a single test will be executed repeatedly for all the values in the data provider. 

