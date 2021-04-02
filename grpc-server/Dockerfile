FROM maven:3.6.3-jdk-8-slim as base
RUN apt-get update \
    && apt install -y protobuf-compiler \
    && protoc --version 
WORKDIR /app
COPY . .
EXPOSE 8097
RUN mvn clean generate-sources compile install
CMD ["mvn exec:java -Dexec.args=\"PORT:8097;\""]