#!/usr/bin/bash

PROJECT_IMAGE_NAME=$(cat imageName.txt)
sudo docker push $PROJECT_IMAGE_NAME
