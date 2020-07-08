---
title: 图的广度优先搜索（BFS）与深度优先搜索（DFS） 
tags: 
  - python
  - 数据结构
  - 图
  - BFS
  - DFS
categories:
  - 数据结构算法
keywords: 
date: 2018-06-11
updated: 
description: 
top_img: 

---

1. 广度优先搜索

```python
# 图的广度优先遍历
# 1.利用队列实现
# 2.从源节点开始依次按照宽度进队列，然后弹出
# 3.每弹出一个节点，就把该节点所有没有进过队列的邻接点放入队列
# 4.直到队列变空
from queue import Queue
def bfs(node):
    if node is None:
        return
    queue = Queue()
    nodeSet = set()
    queue.put(node)
    nodeSet.add(node)
    while not queue.empty():
        cur = queue.get()               # 弹出元素
        print(cur.value)                # 打印元素值
        for next in cur.nexts:          # 遍历元素的邻接节点
            if next not in nodeSet:     # 若邻接节点没有入过队，加入队列并登记
                nodeSet.add(next)
                queue.put(next)
```



2. 深度优先搜索

```python
# 图的深度优先遍历
# 1.利用栈实现
# 2.从源节点开始把节点按照深度放入栈，然后弹出
# 3.每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
# 4.直到栈变空
def dfs(node):
    if node is None:
        return
    nodeSet = set()
    stack = []
    print(node.value)
    nodeSet.add(node)
    stack.append(node)
    while len(stack) > 0:
        cur = stack.pop()               # 弹出最近入栈的节点
        for next in cur.nexts:         # 遍历该节点的邻接节点
            if next not in nodeSet:    # 如果邻接节点不重复
                stack.append(cur)       # 把节点压入
                stack.append(next)      # 把邻接节点压入
                set.add(next)           # 登记节点
                print(next.value)       # 打印节点值
                break                   # 退出，保持深度优先
```

