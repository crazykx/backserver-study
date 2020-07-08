---
title: 求解完全二叉树的节点总数
tags: 
  - python
  - 二叉树
  - 完全二叉树
  - 树节点数
categories:
  - 数据结构算法
keywords: 
date: 2018-06-04
updated: 
description: 
top_img: 
---

1. 利用一般递归即可求得

```python
def getNodeNums(head):
    if not head:
        return 0
    lnums = getNodeNums(head.left)
    rnums = getNodeNums(head.right)
    return lnums + rnums + 1
```



2. 利用完全二叉树的特性递归（时间复杂度O(logn*logn)）

```python
# 利用完全二叉树特性，使用递归 时间复杂度：O(logn*logn)
# 满二叉树节点总个数为2**n-1
def nodeNums(head):
    if not head:
        return 0
    return bs(head, 1, mostLeftLevel(head, 1))

# 求当前节点的节点总数
def bs(node, level, height):
    if level == height:
        return 1
        # 如果当前节点的右子节点等于父节点深度减一，即当前节点的左子树为满二叉树
    if mostLeftLevel(node.right, level+1) == height:
        # 返回左子树节点数+父节点+右子树节点数
        return 2**(height-level) + bs(node.right, level+1, height)
    else:
        # 如果右子节点深度不等于父节点深度减一，又此树为完全二叉树，即右子树为满二叉树
        # 返回右子树节点数+父节点+左子树节点数
        return 2**(height-level-1) + bs(node.left, level+1, height)

# 根据完全二叉树特征求当前节点的深度
def mostLeftLevel(node, level):
    while node:
        level += 1
        node = node.left
    return level - 1
```



