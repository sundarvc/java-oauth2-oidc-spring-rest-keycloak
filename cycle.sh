#!/bin/bash

kubectl delete -f deployment -n i821932-dev
mvn clean package -DskipTests
docker build -t sundarvc/restdemo:v$1 .
docker push sundarvc/restdemo:v$1
kubectl apply -f deployment -n i821932-dev
sleep 10
./tail_restdemo_logs.sh
