# Email service worker

Create a service that accepts the necessary information of an email.


__Table of Contents__

- [Overview](#overview)

- [Documentation](#documentation)
  - [Install](#install)
    - [Steps](#steps)
    - [Without docker](#without-docker)
    - [With docker](#without-docker)

- [Development](#development)
  - [Notes](#notes)
  - [TODO](#todo)
    - [Operational](#operational)
    - [Features](#features)

# Overview

This app listens to the SQS queue, gets the message and sends it to the email provider for further processing. It uses Hystrix to manage the email provide and fallsback to the other email provide if the primary email provider fails.

# Documentation
## Install
- Requires Java 1.8 
- Requires mvn 
- Docker (Optional) - (https://docs.docker.com/install/)
- Docker-compose (Optional) - (https://docs.docker.com/compose/install/)

### Steps

- Unzip the code repo `email-service-worker.zip` (Which is a git repo)
- Update the `/src/main/resources/application.properties` with `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` 

#### Without docker
- `mvn package`
- `java -jar email-service-worker-app-0.0.1-SNAPSHOT.jar`

#### With docker
- `docker build`
- `docker-compose up`

# Development

## Notes
- To run test `mvn test`
- To build `mvn build`
- To run checkstyle `mvn verify`
- To package `mvn package`


## TODO
### Operational
- Cloudformation stack with load balancer and auto scaling 
- Add monitoring of the application 
    - Cloud watch alarm (For API and SQS)
    - On-call Support for agreed SLA (pagerduty or others)
    - Configure App insights (new relic)
    - Log forwarder/aggregator (Splunk)

### Features
- Configure dead-letter queue for messages that fails more than 10 times (Its an option available in the SQS queue itself).
- Expose some metrics the count of emails send via the specific email provider.
 