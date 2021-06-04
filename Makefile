ARGS	?=
PORT	?= 8081
IMAGE   :=  currency-converter
VERSION :=  $(shell git describe --tags --always --dirty)

docker: Dockerfile
	echo "building the $(IMAGE) container..."
	docker build --label "version=$(VERSION)" -t $(IMAGE):$(VERSION) .

run-docker: .run-docker
.run-docker:
	docker run -it --rm -p $(PORT):$(PORT) $(ARGS)
