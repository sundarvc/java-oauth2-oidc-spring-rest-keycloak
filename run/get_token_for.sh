#! /bin/bash

# usage
# . ./get_token_for.sh <user> <password> <scope>
# e.g. 
# . ./get_token_for.sh test1 abc111 product:read

# realm used here is "one" realm.
# by default keycloak does not save entries between server restarts.

#if creating a new user under "one" realm , you should add a custom attribute called "user_id"
# and then copy the UUID from LoadUserTable.java. This will be the federated identifier for that user.

# e.g. "carol"" . 
USER_NAME=$1
# password for carol defined in keycloak
PASSWORD=$2
# scope for authorization, must be defined in keycloak server
# first create scope e.g. product:read and then add it to the client "app"
SCOPE=$3



export TOKEN=`curl --location --request POST 'http://localhost:9999/auth/realms/one/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username='$USER_NAME \
--data-urlencode 'password='$PASSWORD \
--data-urlencode 'client_id=app' \
--data-urlencode 'scope='$SCOPE \
--data-urlencode 'client_secret=bfbd9f62-02ce-4638-a370-80d45514bd0a' \
--data-urlencode 'grant_type=password' | jq -r '.access_token'`

