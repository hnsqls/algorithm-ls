# LeetCode 75

## [1768. 交替合并字符串 - 力扣（LeetCode）](https://leetcode.cn/problems/merge-strings-alternately/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	两个字符串，每个字符串都拆成字符，交替拼接，长的字符串（剩余的字符） 拼接其后。

思路

* 根据短字符的长度进行遍历，最后拼接上长字符的剩余字符

```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int minSize = Math.min(word1.length(),word2.length());

        StringBuilder res = new StringBuilder();
        
        for(int i = 0 ;i < minSize ;i ++){
            res.append(word1.charAt(i));
            res.append(word2.charAt(i));
        }
        // 处理剩余字符
        if(word1.length() > minSize){
            res.append(word1.substring(minSize));
        }
        // 处理剩余字符
        if(word2.length() > minSize){
             res.append(word2.substring(minSize));
        }
        return res.toString();
        
    }
}
```

## [1071. 字符串的最大公因子 - 力扣（LeetCode）](https://leetcode.cn/problems/greatest-common-divisor-of-strings/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	给出两个字符串，找出最长的公因子（比如  str1 = "ABCABC", str2 = "ABC"， 则最长公因子是“ABC”）



思路

* 最长公因子，最长也就是短串

优化思路

* str1 + str2 = str2 + str1 的时候才会有解，因为有解的情况下，str1可以被除尽，str2也可以被除尽，当然str1+str2 可以被除尽。
* 如果存在这样的字符串x，那么x的长度必须是两个字符串长度的公约数。



```java
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // 检查两个字符串连接后是否相等 // 假设str1是N个x，str2是M个x，那么str1+str2肯定是等于str2+str1的。
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // 计算两个字符串长度的最大公约数
        int gcdLength = gcd(str1.length(), str2.length());
       
        // 返回最大公约数长度的前缀子串
        return str1.substring(0, gcdLength);
    }
    
    // 计算最大公约数的辅助方法
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
```

