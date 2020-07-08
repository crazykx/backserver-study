---
title: Docker常用命令
tags:
  - docker
  - docker命令
categories:
  - docker

keywords: 
date: 2019-10-31
description: 
top_img:
description:

---

## Docker

### 拉取镜像

``` dockerfile
docker pull redis

docker pull ubuntu:18.04
```



### 运行 redis 容器

``` dockerfile
docker run –name myredis -d -p 16379:6379 redis

docker run -it –rm ubuntu:18.04 bash
```

`it`:

```dockerfile
`-i`: 交互性操作

 `-t`: 终端
```



`--rm`: 这个参数是说容器退出后随之将其删除

`ubuntu:18.04`: 这是指用 `ubuntu:18.04` 基础镜像来启动

`bash`: 放在镜像后面的是命令, 希望有个交互式

##### 执行容器中的 redis-cli

docker exec -it myredis redis-cli

##### 列出顶层镜像

docker image ls

##### 镜像体积: 查看镜像 容器 数据卷 所占用的空间

docker system df

##### 虚悬镜像

```
<none>               <none>              00285df0df87        5 days ago          342 MB
```



查看虚悬镜像:

docker image ls -f dangling=true



删除虚悬镜像:

docker image prune



##### 中间层镜像

Docker会利用中间层镜像

docker image ls -a

##### 列出部分镜像

docker image ls ubuntu

`--filter`，或者简写 `-f`


在 `mongo:3.2` 之后建立的镜像:

docker image ls -f since=mongo:3.2

docker image ls -f before=mongo:3.2

根据`label`:

docker image ls -f label=com.example.version=0.1



##### 以特定格式显示

docker image ls -q

docker image ls -q redis

docker image ls –digests

```
docker  image  ls  --format  "{{.ID}} {{.Repository}}"docker  image  ls  --format  "table {{.ID}}\t{{.Repository}}\t{{.Tag}}"
```



##### 删除镜像

docker image rm redis

docker image rm cmnasdfgnkll78sd9asdf

用docker image ls 命令配合

docker image rm ${docker image ls -q redis}

docker image rm ${docker image ls before=mongo:3.2}



##### Untagged 和 Deleted

一个镜像可以有多个标签, 当删除了指定的标签后, 肯定有别的标签指向了这个镜像, 如果是这种情况, 那么 Delete 行为就不会发生, 可能就是删除了某个标签而已

当该镜像所有的标签都被取消了，该镜像很可能会失去了存在的意义，因此会触发删除行为。镜像是多层存储结构，因此在删除的时候也是从上层向基础层方向依次进行判断删除。镜像的多层结构让镜像复用变得非常容易，因此很有可能某个其它镜像正依赖于当前镜像的某一层。这种情况，依旧不会触发删除该层的行为。直到没有任何层依赖当前层时，才会真实的删除当前层。这就是为什么，有时候会奇怪，为什么明明没有别的标签指向这个镜像，但是它还是存在的原因，也是为什么有时候会发现所删除的层数和自己 `docker pull` 看到的层数不一样的原因。

除了镜像依赖以外，还需要注意的是容器对镜像的依赖。如果有用这个镜像启动的容器存在（即使容器没有运行），那么同样不可以删除这个镜像。之前讲过，容器是以镜像为基础，再加一层容器存储层，组成这样的多层存储结构去运行的。因此该镜像如果被这个容器所依赖的，那么删除必然会导致故障。如果这些容器是不需要的，应该先将它们删除，然后再来删除镜像。



##### 理解镜像

docker run –name webserver -d -p 10080:80 nginx

docker exec -it webserver bash

查看容器存储层的改动:

docker diff webserver

将容器保存为镜像:

docker commit –author “kx” –message “修改了网页” webserver nginx:v2

使用 `docker commit` 意味着所有对镜像的操作都是黑箱操作，生成的镜像也被称为 **黑箱镜像**

除了制作镜像的人知道执行过什么命令、怎么生成的镜像，别人根本无从得知

docker image ls nginx

查看镜像内的历史记录:

docker history nginx:v2



##### 使用Dockerfile定制镜像

```
FROM nginxRUN echo '<h1>Hello, MyNginx!</h1>' > /usr/share/nginx/html/index.html
```



**FROM指定基础镜像**

> FROM scratch //不以任何镜像为基础, 接下来所写的指令将作为镜像第一层开始存在
>
> …

**RUN执行命令**

`RUN`指令是用来执行命令的,

```
FROM debian:stretch

RUN apt-get update
RUN apt-get install -y gcc libc6-dev make wget
RUN wget -O redis.tar.gz "http://download.redis.io/releases/redis-5.0.3.tar.gz"
RUN mkdir -p /usr/src/redis
RUN tar -xzf redis.tar.gz -C /usr/src/redis --strip-components=1
RUN make -C /usr/src/redis
RUN make -C /usr/src/redis install
```

====>>>

```
FROM debian:stretch

RUN buildDeps='gcc libc6-dev make wget' \
    && apt-get update \
    && apt-get install -y $buildDeps \
    && wget -O redis.tar.gz "http://download.redis.io/releases/redis-5.0.3.tar.gz" \
    && mkdir -p /usr/src/redis \
    && tar -xzf redis.tar.gz -C /usr/src/redis --strip-components=1 \
    && make -C /usr/src/redis \
    && make -C /usr/src/redis install \
    && rm -rf /var/lib/apt/lists/* \
    && rm redis.tar.gz \
    && rm -r /usr/src/redis \
    && apt-get purge -y --auto-remove $buildDeps
```



没有必要建立很多层，这只是一层的事情

添加了清理工作的命令，删除了为了编译构建所需要的软件，清理了所有下载、展开的文件，并且还清理了 `apt` 缓存文件

##### 构建镜像

docker build -t nginx:v3 .

`.`: 表示当前目录, 指定上下文

`docker build` 命令构建镜像，其实并非在本地构建，而是在服务端，也就是 Docker 引擎中构建的

当构建的时候，用户会指定构建镜像上下文的路径，`docker build` 命令得知这个路径后，会将路径下的所有内容打包，然后上传给 Docker 引擎

Docker 引擎收到这个上下文包后，展开就会获得构建镜像所需的一切文件

可以用 `-f ../Dockerfile.php` 参数指定某个文件作为 `Dockerfile`

**直接用 Git repo构建**

```
docker build https://github.com/***/gitlab-ce-zh.git#11.1
```

命令指定了构建所需的 Git repo, 并且指定默认的 `master`分支, 构建目录为 `/11.1/`, 然后 Docker 自己去 git clone 这个项目, 切换到指定分支, 并进入到指定目录后开始构建



**用指定的 tar 包构建**

docker build http://server/context.tar.gz

如果 url 不是个 git repo, 而是个 tar 压缩包, 那么 Docker 引擎会下载这个包, 并自动解压缩, 以其为上下文, 开始构建

**从标准输入中读取 Dockerfile 进行构建**

docker build - < Dockerfile

它没有上下文

**从标准输入中读取上下文压缩包进行构建**

docker build - < context.tar.gz

直接将其展开, 将里面视为上下文, 并开始构建https://hexo.io/docs/one-command-deployment.html)