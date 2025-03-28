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



