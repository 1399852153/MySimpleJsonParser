# 从零开始实现一个简单的json解析器

## 1. MySimpleJsonParser介绍
最近正在学习编译原理相关的知识，为了加深对词法分析、语法分析阶段中诸如有穷自动机、自顶向下语法分析、AST等概念的理解，所以选择了json解析器作为练手的对象。  
#####
通过实现json解析器来学习编译原理前端知识有以下几个优点：
1. json作为一种轻量级的数据交换格式，在日常的工作天天都会接触到，对json的语法非常熟悉，没有额外的学习成本
2. 作为学习编译原理的入门新手，词法和语法不能太复杂，否则无论是理解还是正确的实现解析器都会非常困难，而让人产生挫败感。而json的词法和语法足够简单，在语法分析时只需要简单判断下一个token即可确定AST生成的方向。
3. json并不是一个真正的编程语言，其完全不需要后端的运行时，可以认为将json文本转换成正确的AST就算完成了任务。实现基于AST对原始的json文本进行beauty格式化输出的功能就能产生一定的成就感。
#####
在本篇博客中，我们将基于java语言，不依赖任何第三方库，从零开始实现一个简单的json解析器：MySimpleJsonParser。  其包括以下几个主要模块：  
1. 一次性生成所有token的静态json词法分析器StaticJsonLexer
2. 惰性按需解析token的流式json词法分析器StreamJsonLexer
3. 基于递归实现的json语法解析器RecursiveJsonParser
4. 基于堆栈实现的json语法解析器StackBaseJsonParser
5. json的AST数据结构JsonElement及其子类，以及生成对应beauty json字符串的方法

## 2. json词法分析实现

## 3. json语法分析实现

## 4. 基于AST生成beauty json字符串

## 5. 总结