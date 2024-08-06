package com.leetcode.doubleP;

/**
 * 11. 盛最多水的容器
 * 题目意思：qiu
 */

/**
 * 思路
 * 双指针
 * l指向左边，r 指向右边
 * l从左向右遍历，r从右向左遍历
 * l，r 的更新规则是 那边小那边更新
 */
public class Code11 {
    public int maxArea(int[] height) {
        int l = 0 , r = height.length - 1;
        int area = 0;
        while(l < r){
            int t = (r - l) * Math.min(height[l],height[r]);
            area = Math.max(area,t);
            if (height[l] <= height[r]) l++;
            else r --;
        }
        return area;
    }
}
