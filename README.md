# oauth-demo
REST API with Oauth and SAML

<h4> For Deployment in local kyma environment (https://spring.io/guides/gs/spring-boot-kubernetes/) </h4>

$ kubectl create deployment <deployment_name> --image=<image_name> --dry-run -o=yaml > deployment.yaml
$ echo --- >> deployment.yaml
$ kubectl create service clusterip <service_name> --tcp=8080:8080 --dry-run -o=yaml >> deployment.yaml
