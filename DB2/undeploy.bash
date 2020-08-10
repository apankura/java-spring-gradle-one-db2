pushd "./cluster"

kubectl delete namespace sut

kubectl delete -f ./deploy-db2.yaml

popd
