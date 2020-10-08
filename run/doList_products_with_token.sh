export TOKEN=`curl -L -X POST 'http://localhost:9999/auth/realms/one/protocol/openid-connect/token' \
-H 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=app' \
--data-urlencode 'client_secret=bfbd9f62-02ce-4638-a370-80d45514bd0a' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'scope=openid' \
--data-urlencode 'username=test1' \
--data-urlencode 'password=abc111' | jq -r '.access_token'`

curl -H 'Accept: application/json' -H "Authorization: Bearer ${TOKEN}" http://localhost:8080/products