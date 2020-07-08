---
title: 判断二叉树是否为二叉搜索树、完全二叉树、平衡二叉树
tags: 
  - python
  - 二叉树
  - 搜索树
  - 完全二叉树
  - 平衡二叉树
categories:
  - 数据结构算法
keywords: 
date: 2018-06-03
updated: 
description: 
top_img: 
---

1. **二叉搜索树**：若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为[二叉排序树](https://baike.baidu.com/item/二叉排序树)。（**即中序遍历情况下，值依次增大**）

```python
# 二叉搜索树
# 中序遍历情况下，值递增则为二叉树
def isBSTree(head):
    minimum = -100000               # 设定一个最小值
    if head is None:
        return False
    prenum = minimum
    stack = []
    while head or len(stack) > 0:
        if head:
            stack.append(head)
            head = head.left
        else:
            head = stack.pop()
            if head.val < prenum:   # 保证中序遍历情况下值递增
                return False
            else:
                prenum = head.val
            head = head.right
    return True
```



2. **完全二叉树**：若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（**除了最后一层之外的其他每一层都被完全填充，并且所有结点都保持向左对齐。**）

```python
# 判断一棵树是否为完全二叉树
# 左无、右有 ==> 返回 False
# 左无、右无 ==> 激活判断：之后所有节点都是叶节点
# 左有、右无 ==> 激活判断：之后所有节点都是叶节点        ==》      只要右无之后都必须是叶节点
# 左有、右有 ==> 不用处理
import queue
def isCBTree(head):
    if not head:
        return False
    que = queue.Queue()
    que.put(head)
    flag = False                                # 是否激活判断过程
    while not que.empty():
        head = que.get()
        if head.left:
            que.put(head.left)
        if head.right:
            que.put(head.right)

        if (not head.left) and head.right:      #左空、又不空必不为CBT
            return False

        if flag:                                # 若过程激活则判断节点是否为叶节点
            if head.left or head.right:
                return False

        if not (head.left and head.right):      # 左不空、右空 | 左空、右空
            flag = True                         # 激活判断在此之后的节点必须为叶节点
return True
```



3. **平衡二叉树：**平衡二叉树是一棵二叉树，其可以为空，或满足如下2个性质：①**左右子树深度之差的绝对值不大于1**。②**左右子树都是平衡二叉树**。

- 第一种写法：递归返回判断结果和子节点深度

```python
# 判断二叉树是否为平衡二叉树
# 先判断该节点是否平衡
# 再递归去判断左节点和右节点是否平衡
def process(head):
    if head is None:
        return True, 0
    leftData = process(head.left)
    if not leftData[0]:
        return False, 0
    rightData = process(head.right)
    if not rightData[0]:
        return False, 0
    if abs(leftData[1]-rightData[1]) > 1:
        return False, 0
    return True, max(leftData[1],rightData[1]) + 1
```

- 第二种常见写法：

```python
# 判断二叉树是否为平衡二叉树
# 先判断该节点是否平衡
# 再递归去判断左节点和右节点是否平衡

# 递归求当前节点的深度
def getdepth(node):
    if not node:
        return 0
    ld = getdepth(node.left)
    rd = getdepth(node.right)
    return max(ld, rd) + 1


def isB(head):
    if not head:
        return True
    ld = getdepth(head.left)
    rd = getdepth(head.right)
    if abs(ld - rd) > 1:
        return False
    return isB(head.left) and isB(head.right)
```

 