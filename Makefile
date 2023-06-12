# Makefile used to build and run project
VERSION := $(shell mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression=project.version -q -DforceStdout)

.SILENT:

# Build
build:
	echo "Building Maven project...This might take a few minutes!"
	./mvnw -q clean -B verify -DskipTest

# Execute Tests
test:
	./mvnw -q clean test

# Build & Run Micronaut Device Power Sender
run-micronaut-sender: build
	java -jar ./micronaut-device-energy-sender/target/micronaut-device-energy-sender-$(VERSION).jar
