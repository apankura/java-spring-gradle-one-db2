kubectl create namespace sut 
kubectl apply -f deploy-sut.yaml
kubectl apply -f service-sut.yaml
Start-Sleep 10
kubectl get pods -n sut 
kubectl get services -n sut
kubectl get endpoints -n sut

$POD = kubectl get pods -n sut -l key=spring-session-pod -o name
$POD = $POD.replace("pod/", "")



write-host "kubectl port-forward ${POD} -n sut 46801"