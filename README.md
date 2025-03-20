# PLEASE NOTE
# These tests will not run in the Staging Environment
# Claim Child Benefits is now hosted in PEGA
# Neither the claim-child-benefit-fronted and claim-child-benefit
# are currently deployed in Staging

# claim-child-benefit-performance-tests
Performance test suite for the `CLAIM_CHILD_BENEFIT` service, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.


## Running the tests (Locally)

Prior to executing the tests ensure you have:

* Docker - to start mongo container
* Installed/configured service manager 2

Run the following command to start the services locally:
```
sm2 --start CLAIM_CHILD_BENEFIT_ALL
```

## Logging

The template uses [logback.xml](src/test/resources) to configure log levels. The default log level is *WARN*. This can be updated to use a lower level for example *TRACE* to view the requests sent and responses received during the test.

#### Smoke test

It might be useful to try the journey with one user to check that everything works fine before running the full performance test
```
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

#### Running the performance test
```
sbt -DrunLocal=true gatling:test
```
### Run the example test against staging environment
### First of all, deploy both claim-child-benefit-frontend AND claim-child-benefit service

#### Smoke test
```
sbt -Dperftest.runSmokeTest=true -DrunLocal=false gatling:test
```

#### Run the performance test

To run a full performance test against staging environment, implement a job builder and run the test **only** from Jenkins.

### Scalafmt
 This repository uses [Scalafmt](https://scalameta.org/scalafmt/), a code formatter for Scala. The formatting rules configured for this repository are defined within [.scalafmt.conf](.scalafmt.conf).

 To apply formatting to this repository using the configured rules in [.scalafmt.conf](.scalafmt.conf) execute:

 ```
 sbt scalafmtAll
 ```

 To check files have been formatted as expected execute:

 ```
 sbt scalafmtCheckAll scalafmtSbtCheck
 ```

[Visit the official Scalafmt documentation to view a complete list of tasks which can be run.](https://scalameta.org/scalafmt/docs/installation.html#task-keys)