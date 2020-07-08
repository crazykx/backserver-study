---
title: Dockerfile指令详解
tags:
  - docker
  - dockerfile
categories:
  - docker

keywords: 
date: 2019-10-31
description: 
top_img:
description:

---

## Dockerfile

### COPY复制文件

``` dockerfile
COPY [--chown=<user>:<group>] <源路径>... <目标路径>
```

```dockerfile
COPY  package.json  /ust/src/app/

COPY hom* /mydir/
COPY hom?.txt /mydir/
```



通配符规则要满足 Go 的 [`filepath.Match`](https://golang.org/pkg/path/filepath/#Match) 规则

使用 `COPY` 指令，源文件的各种元数据都会保留。比如读、写、执行权限、文件变更时间等

加上 `--chown=<user>:<group>` 选项来改变文件的所属用户及所属组

```dockerfile
COPY --chown=55:mygroup files* /mydir/
COPY --chown=bin files* /mydir/
COPY --chown=1 files* /mydir/
COPY --chown=10:11 files* /mydir/
```