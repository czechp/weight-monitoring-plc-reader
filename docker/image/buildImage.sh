#!/usr/bin/bash

PROJECT_IMAGE_NAME=$(cat imageName.txt)
sudo docker image rm $PROJECT_IMAGE_NAME
sudo docker image build --tag=$PROJECT_IMAGE_NAME .
