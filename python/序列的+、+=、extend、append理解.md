---
title: 序列的+、+=、extend、append理解
tags: 
  - python
categories:
  - python
keywords: 
date: 2018-06-17
updated: 
description: 
top_img: 

---



列表 + 元组：

```python
>>> a = [1, 2]
>>> b = (3, 4)
>>> a = a + b
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
TypeError: can only concatenate list (not "tuple") to list
```



列表 += 元组：

```python
>>> a += b
>>> a
[1, 2, 3, 4]
```

原因：‘ += ’是调用 iadd()函数，然后调用extend函数，extend函数会遍历序列元素再依次加入：

```python
def __iadd__(self, values):
      self.extend(values)
      return self

def extend(self, values):
    'S.extend(iterable) -- extend sequence by appending elements from the iterable'
    for v in values:
        self.append(v)
```



append()始终将加入的元素作为整体加入:

```python
>>> a
[1, 2, 3, 4]
>>> a.append((5, 6))
>>> a
[1, 2, 3, 4, (5, 6)]
```

 