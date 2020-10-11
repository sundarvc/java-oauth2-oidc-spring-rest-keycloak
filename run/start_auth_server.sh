#! /bin/bash


docker stop $(docker ps -aq)

docker rm $(docker ps -aq)

docker network rm keycloak-network

docker network create keycloak-network

docker run -d --name postgres --net keycloak-network -e POSTGRES_DB=keycloak -e POSTGRES_USER=keycloak -e POSTGRES_PASSWORD=password postgres

# Change where your local realm is deployed in the line below next to -v option
# Note 8080 port in docker is mapped to port 9999 in the host

docker run --name docker-keycloak-postgres --net keycloak-network -e DB_USER=keycloak -e DB_PASSWORD=password -e DB_ADDR=postgres -e DB_VENDOR=POSTGRES -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -e KEYCLOAK_IMPORT="/tmp/one-realm.json,/tmp/two-realm.json -Dkeycloak.profile.feature.upload_scripts=enabled" -v /Users/i821932/work/SSC/Sample\ App/securing-spring-data-rest-apis/resolutions/etc/realms:/tmp  -p 127.0.0.1:9999:8080 jboss/keycloak 


