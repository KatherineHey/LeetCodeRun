# BinarySearch

find m that f\(m\) is true. Or return the smallest number l such that g\(l\) is true.

```java
def binary_search(l, r):
  while l < r:
    m = l + (r - l) // 2
    if f(m): return m # optional
    if g(m):
      r = m     # new range [l, m)
    else:
      l = m + 1 # new range [m+1, r)

  return l # or not found
```

f\(m\) 函数：判断m是不是当前的解。 g\(m\) 函数：判断解是不是 &gt;= m。

f\(m\) 是可选的，如果找到解，直接返回解。没有f\(m\)也可以。 如果省略f\(m\)，那么循环结束后l存储是最小的能满足g\(l\) = true 的数。

如果l == r，则表示没有解。

例子：sqrt\(x\), 返回x开根号后的整数部分。 样例： sqrt\(4\) = 2 sqrt\(8\) = 2

代码： sqrt\(x\)的取值范围在 \[0, x\] 之间，所以l=0, r = x + 1, 搜索范围 \[0, x + 1\)。

```java
def sqrt(x):
  l = 0
  r = x + 1

  while l < r:
    m = l + (r - l) // 2
    if m * m > x
      r = m
    else
      l = m + 1

  return l - 1
```

套用模版：f\(m\) 没有，g\(m\) = m _m &gt; x。 循环结束后l为最小的数满足 l_ l &gt; x，第一个平方大于x的整数。所以sqrt\(x\) = l - 1，最后返回 l - 1。

```java
// first find the peek of the mountain
        int len = mountainArr.length();
        int l = 0 ; int r = len - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid+1)) {
                l = mid+1;
            } else {
                r = mid;
            }
        }

        int peak = l;
```

