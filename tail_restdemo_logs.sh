kubectl logs `kubectl get pods -n i821932-dev | grep restapp | awk '{print $1}'`  -n i821932-dev -f restapp
