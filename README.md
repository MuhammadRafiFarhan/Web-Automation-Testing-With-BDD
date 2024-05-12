# SwagLab Web Testing Automation

## Installation
After you have clone this repository, run this command for the first time to download all the dependencies:
```bash
mvn clean install
```

## Running the tests
This command below will automatically generate the report after the test execution using the HTML plugin from cucumber:
```bash
mvn test
```

And this command below will generate the report after the test execution using the surefire plugin:
```bash
mvn surefire-report:report 
```

## Folder structure (Maven, simplified)
```
  root
    ├───.vscode
    ├───src
    │   ├───main
    │   │   └───java
    │   │       └───com
    │   │           └───swaglab
    │   │               └─── Main.java (default class)
    │   └───test
    │       ├───java
    │       │   └───com
    │       │       └───swaglab
    │       │           ├───steps
    │       │           │    ├─── LoginSteps.java (step definition)
    │       │           │    └─── LogoutSteps.java (step definition)
    │       │           └─── TestRunner.java (cucumber runner class)
    │       └───resources
    │           ├───com
    │           │    └───swaglab
    │           │        └───features
    │           │            ├─── login.feature (feature file)
    │           │            └─── logout.feature (feature file)
    │           └─── junit-platform.properties (properties file for JUnit5 usage)
    ├───target
    │   ├───cucumber-reports
    │   │   └───Cucumber.html (cucumber report in HTML format)
    │   └───site
    │       └───surefire-report.html (surefire report in HTML format)
    └───pom.xml

```