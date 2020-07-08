---
title: python属性查找顺序
tags: 
  - python
  - 属性查找
categories:
  - python
keywords: 
date: 2018-06-20
updated: 
description: 
top_img: 

---



```python
import numbers

class IntField:
    # 数据描述符
    def __get__(self, instance, owner):
        return self.value

    def __set__(self, instance, value):
        if not isinstance(value, numbers.Integral):
            raise ValueError('int value need')
        if value < 0:
            raise ValueError('positive value need')
        self.value = value

    def __delete__(self, instance):
        pass

class NonDataIntField:
    # 非数据属性描述符
    def __get__(self, instance, owner):
        return self.value

class User:
    # age = IntField()
    age = NonDataIntField()

if __name__=='__main__':
    user = User()
    user.__dict__['age'] = 'abc'
    print(user.__dict__)
    print(getattr(user, 'age'))
```

输出：

```python
{'age': 'abc'}
abc
```

查找顺序：

先查找__getattribute__: 

　　1.查找数据描述符__get__

　　2.查找实例__dict__

　　3.查找基类

　　　　若是非数据描述符访问其__get__

　　　　否则查找基类__dict__

没找到再查找__getattr__

否则抛出异常AttributeError