#!/bin/bash
# Author: yueyang
# Date: 2022-07-28 16:50:00
# Description: 启动客户端后台

nohup java -jar /application/app.jar --server.port=8001 1> /application/logs/docker_app.log