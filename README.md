# Aiven Kafka Quickstart with Micronaut 🦀

![Java CI with Maven](https://github.com/fhussonnois/aiven-kafka-micronaut-iot-demo/actions/workflows/maven-build.yml/badge.svg)
![CodeQL](https://github.com/fhussonnois/aiven-kafka-micronaut-iot-demo/actions/workflows/codeql.yml/badge.svg)

## Introduction

Check [this blog](./docs/README.md) post to lean more about this project and how to use it with Aiven.

## Development

### Prerequisite

This project is built with:

* JDK 17
* [Micronaut](https://micronaut.io/)
* Apache Kafka cluster

### Build

To build:

```bash
./mwn clean verify
```

### Test

To run tests with Maven:

```bash
./mwn clean test
```

### Run

To run the application, use the below command, which starts the application on port `8080`.

```bash
./mvnw -f ./micronaut-device-energy-sender/pom.xml mn:run
 ```

## Licence

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.See the NOTICE file distributed with this work for additional information regarding copyright ownership.The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.See the License for the specific language governing permissions and limitations under the License