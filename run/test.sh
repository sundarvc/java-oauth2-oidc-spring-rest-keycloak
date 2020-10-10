#!/bin/bash

USER_NAME=$1
PASSWORD=$2
SCOPE=$3

curl --location --request POST 'http://localhost:9999/auth/realms/one/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username='$USER_NAME \
--data-urlencode 'password='$PASSWORD \
--data-urlencode 'client_id=app' \
--data-urlencode 'scope='$SCOPE \
--data-urlencode 'client_secret=bfbd9f62-02ce-4638-a370-80d45514bd0a' \
--data-urlencode 'grant_type=password'