# Starting the `server`

- Starting the server first time
  
    ```java
    mvn clean generate-sources compile install 
    mvn exec:java -Dexec.args="PORT:<your port number>;"
    ```
    Eg.,
    ```java
    mvn clean generate-sources compile install
    mvn exec:java -Dexec.args="PORT:8097;"
    ```

- Re-starting the server

    ```java
    mvn exec:java -Dexec.args="PORT:<your port number>;"
    ```
    Eg.,
    ```java
    mvn exec:java -Dexec.args="PORT:8097;"
    ```