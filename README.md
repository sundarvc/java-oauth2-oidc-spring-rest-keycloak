# OAuth 2.0 Spring 5.x REST API with Keycloak Server

Version 0.1 

Security Vulnerabilities

1. User credentials passed with every request.
2. User credentials would have to be stored in the Client app perhaps as plain text.
3. Once Client has access to User credentials , Client has full control over resources i.e. impersonate the User.
4. User credentials are stored in the application database.

Version 0.2

Security Vulnerabilities

1. Removed version 0.1 , 1-4 vulnerabilities.

Usage for MacOS:

Step 1 : cd ./run

Step 2 : Start keycloak server. 
         Note you have to change the default realm directory on your host first.
         Open the file below to make the change.

         ./start_auth_server.sh

Step 3 : Login to your auth server (username: admin , password: admin)
         
         http://localhost:9999/auth/

         and create user,password for user , "user_id" attribute and associated UUID and scope. 
         More info inside get_token_for.sh script.
         
Step 4 : In a new terminal

         ./start_resource_server.sh
         
Step 5 : . ./get_token_for.sh <user> <password> <scope>
         
         scope should be "product:read" , as defined in resource server controller.
         
Step 6 : echo $TOKEN to verify token is present

Step 7 : ./list_products.sh 

         Should see some data returned.
         
Comments welcome : sundar.chakravarthy@gmail.com
