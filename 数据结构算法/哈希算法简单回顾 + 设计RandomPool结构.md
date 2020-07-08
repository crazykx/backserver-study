---
title: 哈希算法简单回顾 + 设计RandomPool结构
tags: 
  - 哈希算法
categories:
  - 数据结构算法
keywords: 
date: 2018-06-04
updated: 
description: 
top_img: 
---

**哈希回顾**

**比较重要的性质：**

**不可逆：**当你知道x的HASH值，无法求出x；

**无冲突：**当你知道x，无法求出一个y， 使x与y的HASH值相同

```python
# 哈希特征：
# 1.输入无限
# 2.输出有穷
# 3.输入相同==>输出必相同
# 4.可能存在不同的输入结果输出一样  ==>  哈希碰撞
# 5.输入样本够大，在输出样本上几乎均匀分布 ==> 散列
# md 5  桶结构  hashcode模n ===>  在0--n-1上均匀分布

# 设计RandomPool结构
# 【题目】 设计一种结构，在该结构中有如下三个功能：
# insert(key)：将某个key加入到该结构，做到不重复加入。
# delete(key)：将原本在结构中的某个key移除。
# getRandom()： 等概率随机返回结构中的任何一个key。
# 【要求】 Insert、delete和getRandom方法的时间复杂度都是 O(1)
import random


class RandomPool:
    def __init__(self):
        self.dict1 = dict()     # 字典1：  key:存储key       value:存储当前加入的顺序，第n个
        self.dict2 = dict()     # 字典2：  key:存储count     value:存储加入的key
        self.size = 0

    def insert(self, key):
        if key not in self.dict1:
            self.dict1[key] = self.size
            self.dict2[self.size] = key
            self.size += 1

    def getRandom(self):
        if self.size == 0:
            return None
        index = int(random.randrange(self.size))
        return self.dict2[index]

    def delete(self, key):                  # 当删除指定key时，字典2中删除key对应的count后，
        if key in self.dict1:               # 把最大的count对应的value补到count上，方便getRandom()函数
            delindex = self.dict1[key]
            lastkey = self.dict2[self.size]
            self.dict1.pop(key)
            self.dict1[lastkey] = delindex
            self.dict2[delindex] = lastkey
            self.dict2.pop(self.size)
            self.size -= 1
```

