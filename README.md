# distributed-computation-with-`grpc`-and-`rest`

[![BCH compliance](https://bettercodehub.com/edge/badge/aditya109/distributed-computation-with-grpc-and-rest?branch=pre-release-v1.1)](https://bettercodehub.com/)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/20916e2587724612a05d70cc05a8fdab)](https://www.codacy.com/gh/aditya109/distributed-computation-with-grpc-and-rest/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=aditya109/distributed-computation-with-grpc-and-rest&amp;utm_campaign=Badge_Grade)

Distributed computing with `deadline-footprinting` functionality in Spring Boot server acting as gRPC Client (as Master) and gRPC Server (as Workers). Also the Spring Boot server can auto-scale workers to a configurable threshold. 

## Prerequisites for running the project

- [`maven`](https://maven.apache.org/download.cgi)
- [`protobuf-compiler`](https://github.com/protocolbuffers/protobuf/releases/tag/v3.15.6)
- [`python`](https://www.python.org/downloads/)

## Starting the Project

- Go to `grpc-server` directory and run `mvn` command over there

  ```powershell
  Aditya :: app » cd grpc-server
  Aditya :: grpc-server » mvn clean generate-sources compile install
  ```

- Come back to the parent directory.

  ```powershell
  Aditya :: grpc-server » cd ..
  ```

- Go to `rest-server` directory and run `Spring-Boot` over there.

  ```powershell
  Aditya :: rest-server » mvn clean compile install
  ```

- Starting the server

  ```java
  Aditya :: rest-server » mvn spring-boot:run
  ```