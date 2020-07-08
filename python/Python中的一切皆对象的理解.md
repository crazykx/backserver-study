---
title: Python中的一切皆对象的理解
tags: 
  - python
categories:
  - python
keywords: 
date: 2018-06-15
updated: 
description: 
top_img: 

---



Python有一个重要的概念，一切皆对象。

一切都可以赋值给变量：

- 内置类型赋值：

```python
>>> a=1
>>> b='abc'
>>> type(a)
<class 'int'>
>>> type(b)
<class 'str'>
```



- 将类类型赋值给变量：

```python
>>> a=int
>>> a('123')
123
>>> type(a)
<class 'type'>
```



- 将函数赋值给变量：

```python
>>> def my_func(x):
...     print(x)
...
>>> a=my_func
>>> a(8)
8
```



- 将自定义类赋值给：

```python
>>> class Person:
...     pass
...
>>> a=Person
>>> p = a()
>>> type(p)
<class '__main__.Person'>
```

。。。