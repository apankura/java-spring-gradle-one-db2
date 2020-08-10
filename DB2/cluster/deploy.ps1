kubectl create namespace "sut"

kubectl apply -f ./deploy-db2.yaml

Start-Sleep 10

kubectl get pods -n sut -l key=pod-db2

Start-Sleep 60

kubectl logs -n sut -l app=pod-db2

$POD = kubectl get pods -n sut -l key=pod-db2 -o name
$POD = $POD.replace("pod/", "")

Start-Sleep 60

kubectl apply -f ./service-db2.yaml

kubectl get services -n sut

write-host "kubectl port-forward ${POD} -n sut 50000"

kubectl logs -n sut -l key=pod-db2

#kubectl exec -it $POD -n joy -- /bin/bash


