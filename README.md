# SwagLab Web Testing Automation

> <big><b>Warning (for Powershell users)</b></big><br>
> When adding flag to maven command, surround it with single quotes. For example:<br>
> `mvn test '-Dcucumber.filter.name="^Successful\s.*$"`<br><br>
> For CMD users, just add the flag without any quotes. For the same example:<br>
> `mvn test -Dcucumber.filter.name="^Successful\s.*$"`

## Installation
After you have clone this repository, run this command for the first time to download all the dependencies:
```bash
mvn clean install -Dmaven.test.skip=true
```
The `-Dmaven.test.skip=true` flag is used to skip both the compilation and execution of tests, which can be faster if you do not need to compile the test code.

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
    │       │           ├───locators
    │       │           │    ├─── HomePageLocators.java
    │       │           │    └─── LoginPageLocators.java
    │       │           ├───runner
    │       │           │    └─── TestRunner.java (cucumber runner class)
    │       │           ├───stepDefinitions
    │       │           │    ├─── LoginSteps.java (step definition)
    │       │           │    └─── LogoutSteps.java (step definition)
    │       │           └─── utils
    │       │                └─── WebDriverSetup.java
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