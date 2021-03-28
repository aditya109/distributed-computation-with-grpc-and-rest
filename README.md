

# distributed-computation-with-`grpc`-and-`rest`

Distributed computing with `deadline-footprinting` functionality in Spring Boot server acting as gRPC Client (as Master) and gRPC Server (as Workers). Also the Spring Boot server can auto-scale workers to a configurable threshold. 

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