# -buster is required to have apt available
FROM openjdk:11-jre-slim-buster

# by default curl is not available, so install it
RUN apt update && apt install curl -y

WORKDIR /app
COPY ./build/libs/provider.jar /app
COPY ./config.properties /app
COPY ./04.0-file-transfer/ /app/samples/04.0-file-transfer/
COPY ./assets /app/assets

EXPOSE 8181
EXPOSE 8182
EXPOSE 8282


ENTRYPOINT java \
    -Dedc.fs.config=./config.properties \
    -Dedc.ids.title="Eclipse Dataspace Connector" \
    -Dedc.ids.description="Eclipse Dataspace Connector with IDS extensions" \
    -jar provider.jar