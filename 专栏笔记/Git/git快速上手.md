---
title: git 常用命令介绍
tags:
  - git
  - git命令
  - 版本控制
categories:
  - git
keywords: 
date: 2018-03-16
updated: 2018-3-20
description: 
top_img:

---



Git 与 中央式差不多

优点: 

	- 大多数操作在本地运行, 无需联网即可提交代码 查看历史 切换分支
	- 可以提交到本地   ==>   分步提交代码, 把代码提交做得更细

缺点

	- 每个机器都是完整仓库, 初次获取耗时
	- 每个机器完整仓库,  本地占用存储高



远程仓库永远指向 master , push 时并不会把本地的 HEAD 的指向上传到远程仓库



**git clone -b  dev  gitaddr rename**   :拉取代码

**git checkout -b <branch_name>**: 新建本地分支

**git  push  origin  <local_branch_name>:<remote_branch_name>**: The above command creates branch on remote git repository with same name as local “**stage1**” and push all files there. If you want branch with different name on remote, use command as like below

**git  log  --branches  --not  --remotes**: This will show you all not pushed commits from all branches

**git  log  origin/master..HEAD**: this will show you all your local commits

**git  diff origin/master..HEAD**: view the diff



**git  stash  -a**: stash all  changes

**git  stash  list**: show all local stash

**git  stash  pop  stash@{0}**: pop  the  index  stash

**git  stash  clear**: 清除当前暂缓区所有内容

**git  stash  drop**: 清除某个 index stash





**流程**:

```
git push -f origin 8353a:dev		// 将远程仓库 HEAD 指向上一次 commit 记录
git log --branches --not --remotes  // 查看本地已提交还未 push 的 commit 记录
// 直接更改
git add .
git commit -amend					// 与本地已提交的重新合并生成一个新的 commit 记录, 上一个commit 作废
git reset --soft HEAD~				// 撤销本地还未 push 的 commit 记录
git reset							// 撤销暂缓区中的改动
```





