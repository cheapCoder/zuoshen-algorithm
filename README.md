# 左神算法班笔记

视频地址：https://www.bilibili.com/video/BV13g41157hK/

其他版本的源码笔记：https://github.com/necessityOVO/zuoshen-algorithm/tree/another-note

可借鉴的笔记及源码解析链接：https://github.com/renyujie518/StrcuctandAlgorithm/tree/master/StructandAlgorithm/src

待整理：
1. determinant_reasoning：当一个问题除了前几项是base case能直接已知结果外，后续的每一项都按照之前值的严格递推表达式来求出的问题，推理结果为`f(n, n-1,... n-m) = f(m,...2,1) * Math.pow(|{x x x} {x x x}{x x x}|, n - m)`
典例：斐波那契数列的O(logN)算法

TODO: 
- [ ] 补充题目注释
- [ ] 总结技巧并添加示例题目链接
- [ ] 添加分支仅保留自己的代码
  

思维：

1. 察觉已经计算过的部分并重用，或用缓存以重用
2. 要用废时间换空间，要么废空间换时间，此消彼长
3. 利用数学思维：分类讨论，数形结合

优化：
1. 自身的数据状况
2. 所要求解的标准

待强化：
- [ ] 小根堆，大根堆(例：skill-c18)
- [ ] 二分法(例：skill-c01)
