---
title: 二叉树查找后继节点(即中序遍历情况下的这个节点的下一个)
tags: 
  - python
  - 二叉树
  - 搜索树
  - 完全二叉树
  - 平衡二叉树
categories:
  - 数据结构算法
keywords: 
date: 2018-06-04
updated: 
description: 
top_img: 
---

1. 若节点类型没有parent属性，采用中序遍历方式获取后继节点

```python
def getSuccessorNode(head, node):
    if (not node) or (not head):
        return None
    stack = []
    flag = False
    while head or len(stack) > 0:
        if head:
            stack.append(head)
            head = head.left
        else:
            head = stack.pop()
            if flag:
                return head
            if head == node:            # 若找到当前节点，则下一个弹出的节点即为后继节点
                flag = True
            head = head.right
    return None
```



2. 若节点存在parent属性即

```python
class TreeNode:
    def __init__(self, x=0):
        self.val = x
        self.parent = None
        self.left = None
        self.right = None


def getSuccessorNode(node):
    if not node :
        return None
    if node.right:               # 如果当前节点有右子树，则返回右子树的最左边节点
        node = node.right
        while node.left:
            node = node.left
        return node
    else:                        # 没有右子树   则向上找寻父节点，直到为父节点的左子树，返回父节点，否则返回空
        par = node.parent
        while not par and par.left != node:
            node = par
            par = par.parent
        return par
```



