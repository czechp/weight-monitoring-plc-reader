#!/bin/bash 
GIT_REPO=https://github.com/czechp/bsp-weight-monitoring-plc-reader.git
git clone $GIT_REPO /JavaProject
cd JavaProject
mvn clean install package -DskipTests
java -jar /JavaProject/target/weightMonitoringPlcReader.jar  --spring.profiles.active=production
