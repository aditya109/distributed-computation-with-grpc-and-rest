# Starting the `server`

## Using the standard `mvn` manual method

- Starting the server first time
  
    ```powershell
    mvn clean generate-sources compile install 
    mvn exec:java -Dexec.args="PORT:<your port number>;"
    ```

    Eg.,

    ```powershell
    mvn clean generate-sources compile install
    mvn exec:java -Dexec.args="PORT:8097;"
    ```

- Re-starting the server

    ```powershell
    mvn exec:java -Dexec.args="PORT:<your port number>;"
    ```

    Eg.,

    ```powershell
    mvn exec:java -Dexec.args="PORT:8097;"
    ```

## Using `docker` image

```powershell
docker run --detach --publish PORT:8097 daitya96/dc-grest-grpc-server:0.0.1
```

Eg.,

```powershell
docker run --detach --publish 3000:8097 daitya96/dc-grest-grpc-server:0.0.1
```

## Using `docker-compose`

To bring the grpc-worker up, open an instance of `cmd`, `powershell` or `bash` and just run:

```powershell
docker-compose up
```

To bring the grpc-worker down, open another instance of `cmd`, `powershell` or `bash` and just run:

```powershell
docker-compose down
```
