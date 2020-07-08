---
title: 二叉树先序、中序、后序遍历 递归与非递归
tags: 
  - python
  - 二叉树遍历
  - 中序
  - 后序
  - 先序
categories:
  - 数据结构算法
keywords: 
date: 2018-06-03
updated: 
description: 
top_img: 
---

1. 先序遍历：根节点->左子树->右子树

```python
 # 先序打印二叉树（递归）
def preOrderTraverse(node):
    if node is None:
        return None
    print(node.val)
    preOrderTraverse(node.left)
    preOrderTraverse(node.right)
```
```python
# 先序打印二叉树（非递归）
def preOrderTravese(node):
    stack = [node]
    while len(stack) > 0:
        print(node.val)
        if node.right is not None:
            stack.append(node.right)
        if node.left is not None:
            stack.append(node.left)
        node = stack.pop()
```

 

2. 中序遍历：左子树->根节点->右子树

```python
# 中序打印二叉树（递归）
def inOrderTraverse(node):
    if node is None:
        return None
    inOrderTraverse(node.left)
    print(node.val)
    inOrderTraverse(node.right)
```
```python
# 中序打印二叉树（非递归）
def inOrderTraverse(node):
    stack = []
    pos = node
    while pos is not None or len(stack) > 0:
        if pos is not None:
            stack.append(pos)
            pos = pos.left
        else:
            pos = stack.pop()
            print(pos.val)
            pos = pos.right
```

 

3. 后序遍历：左子树->右子树->根节点

```python
# 后序打印二叉树（递归）
def postOrderTraverse(node):
    if node is None:
        return None
    postOrderTraverse(node.left)
    postOrderTraverse(node.right)
    print(node.val)
```
```python
# 后序打印二叉树（非递归）
# 使用两个栈结构
# 第一个栈进栈顺序：左节点->右节点->跟节点
# 第一个栈弹出顺序： 跟节点->右节点->左节点(先序遍历栈弹出顺序：跟->左->右)
# 第二个栈存储为第一个栈的每个弹出依次进栈
# 最后第二个栈依次出栈
def postOrderTraverse(node):
    stack = [node]
    stack2 = []
    while len(stack) > 0:
        node = stack.pop()
        stack2.append(node)
        if node.left is not None:
            stack.append(node.left)
        if node.right is not None:
            stack.append(node.right)
    while len(stack2) > 0:
        print(stack2.pop().val)
```

 

4. 按层遍历：从上到下、从左到右按层遍历

```python
# 先进先出选用队列结构
import queue
def layerTraverse(head):
    if not head:
        return None
    que = queue.Queue()      # 创建先进先出队列
    que.put(head)
    while not que.empty():
        head = que.get()    # 弹出第一个元素并打印
        print(head.val)
        if head.left:       # 若该节点存在左子节点,则加入队列（先push左节点）
            que.put(head.left)
        if head.right:      # 若该节点存在右子节点,则加入队列（再push右节点）
            que.put(head.right)
```

 

5. 二叉树节点个数

```python
# 求二叉树节点个数
def treeNodenums(node):
    if node is None:
        return 0
    nums = treeNodenums(node.left)
    nums += treeNodenums(node.right)
    return nums + 1
```

 

6.二叉树的最大深度

```python
# 二叉树的最大深度
def bTreeDepth(node):
    if node is None:
        return 0
    ldepth = bTreeDepth(node.left)
    rdepth = bTreeDepth(node.right)
    return (max(ldepth, rdepth) + 1)
```