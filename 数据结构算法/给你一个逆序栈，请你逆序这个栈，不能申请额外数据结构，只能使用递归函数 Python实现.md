---
title: 给你一个逆序栈，请你逆序这个栈，不能申请额外数据结构，只能使用递归函数
tags: 
  - python
  - 数据结构
  - 逆序栈
categories:
  - 数据结构算法
keywords: 
date: 2018-06-07
updated: 
description: 
top_img: 

---



### 逆序栈

```python
# 返回并移除当前栈底元素（栈内元素（1,2,3）==> (2,3),弹出1）
def getAndRemoveLast(stack):
    res = stack.pop()         # 依次将栈顶元素弹出并保存在res中
    if len(stack) == 0:       # 若此时栈已空：栈底元素返回
        return res
    else:
        last = getAndRemoveLast(stack)  # 没有达到栈底则递归求栈底元素，直到栈元素弹完保存在每层递归的res变量中
        stack.append(res)               # 将保存在res中的元素依次再入栈
        return last


# 逆序压入元素
def reverseStack(stack):
    if len(stack) == 0:
        return
    i = getAndRemoveLast(stack)         # 依次得到栈底元素，最后的i保存的即是栈顶元素
    reverseStack(stack)                 # 每层递归取出栈底的元素并缓存到i中
    stack.append(i)                     # 此时栈已空并依次加入最近弹出的栈底元素
```

