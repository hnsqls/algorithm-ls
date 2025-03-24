package com.leetcode.slidingindow;

/**
 * 3,无重复字符的最长子串
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class Code3 {
    /**
     * 双指针
     * 从连续字符串的头开始找
     * 从左向右l指向连续字符串的头 r指向连续字符串的尾
     * 若s[l] != s[l-1] - 1 ，就说明l所指向的字符串是某个字符串的头
     * r从l后一位开始遍历直到不满足s[r] !=s[r+1] ,说明r指向字符串的尾巴
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        int max = 0;

        char[] charArray = s.toCharArray();
        int[] intarray = new int[charArray.length];

        for (int i = 0; i < intarray.length; i++) {
            intarray[i] = (int) charArray[i];
        }
        for (int i = 0; i < intarray.length; i++) {

                if ((i >=1&&intarray[i] != intarray[i-1] + 1) || (i ==0)){
                    int r = i + 1;
                    while (r < intarray.length &&(intarray[r-1]+1 == intarray[r])){
                        //连续
                        r++;

                    }

                    int t  = r - i;
                    max = Math.max(max, t);
                }
        }
        return max;
    }

    public static void main(String[] args) {

       String s="pwwkew";

        Code3 code3 = new Code3();
        code3.lengthOfLongestSubstring(s);

    }
}
