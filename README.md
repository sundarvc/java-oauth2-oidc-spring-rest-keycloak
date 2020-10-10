# oauth-demo
REST API with OAuth and OIDC

Version 0.1 

Security Vulnerabilities

1. User credentials passed with every request.
2. User credentials would have to be stored in the Client app perhaps as plain text.
3. Once Client has access to User credentials , Client has full control over resources i.e. impersonate the User.
4. User credentials are stored in the application database.
