pushd "./cluster"

kubectl create namespace "sut"

kubectl apply -f ./deploy-db2.yaml

sleep 10

kubectl get pods -n sut -l app=pod-db2

sleep 10

kubectl logs -n sut -l app=pod-db2

POD=$(kubectl get pods -n sut -l app=pod-db2 -o jsonpath="{.items[0].metadata.name}")

sleep 60

kubectl port-forward $POD -n sut 50000

# kubectl logs -n joy -l app=pod-hssbp-db2

#kubectl exec -it $POD -n joy -- /bin/bash

popd
