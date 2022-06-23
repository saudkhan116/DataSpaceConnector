# Local Setup

These docker commands are used to setup the system locally.

## Cloning repository

```bash
git clone https://github.com/saudkhan116/DataSpaceConnector.git
```
## Create Docker network

```bash
docker create network edc-network
```
## Launch containers

#### Container 1: edc-consumer

```bash

# Navigate to the working directory where Dockerfile is located
cd edc-consumer/

# build docker image
docker build -t edc-consumer:latest .

# run docker container
docker run -p 9191:9191 -p 9292:9292 -p 9192:9192 --name edc-consumer --network edc-network --volume /$(pwd)/config:/app/config -d edc-consumer:latest

# stop and remove docker container
docker stop edc-consumer; docker rm edc-consumer;
```

#### Using existing docker image
- Pull image using the command  ```docker pull muhammadsaudkhan/edc-consumer:latest```
- Run the container using above run command

#### Container 2: edc-provider
```bash
# Navigate to the working directory where Dockerfile is located
cd edc-provider/

# build docker image
docker build -t edc-provider:latest .

# run docker container
docker run -p 8181:8181 -p 8282:8282 -p 8182:8182 --name edc-provider -volume /$(pwd)/config:/app/config --network edc-network -d edc-provider:latest

# stop and remove docker container
docker stop edc-provider; docker rm edc-provider;
```

#### Using existing docker image
- Pull image using the command  ```docker pull muhammadsaudkhan/edc-provider:latest```
- Run the container using above run command

#### Container 3: consumer-ui
```bash
# Navigate to the working directory where Dockerfile is located
cd consumer-ui/

# build docker image
docker build -t consumer-ui:latest .

# build docker image
docker run -p 8080:80 --name consumer-ui --network edc-network -d consumer-ui:latest

# build docker image
docker stop consumer-ui; docker rm consumer-ui;
```

#### Using existing docker image
- Pull image using the command  ```docker pull muhammadsaudkhan/consumer-ui:latest```
- Run the container using above run command

