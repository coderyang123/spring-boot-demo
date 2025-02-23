# 应用打包成Docker镜像，然后运行容器输出日志至宿主机指定目录案例

# 1.应用打包

# 2.将应用包和build目录下的3个文件上传至服务器

# 3.启动容器

## 3.1 Docker方式

1. 构建镜像：`docker build -t app:1.0.0 .`
2. 运行容器：`docker run -d -v /tmp/logs:/application/logs -p 8001:8001 app-v1.0.0 app:1.0.0`
3. 观察日志输出情况：`tail -f /tmp/logs/docker_app.log`

## 3.2 Docker-compose方式

1. 运行容器：`docker-compose up`
2. 观察日志输出情况：`tail -f /tmp/logs/docker_app.log`