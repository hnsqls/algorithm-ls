package com.leetcode.hashmap;

/**128. 最长连续序列
 * https://leetcode.cn/problems/longest-consecutive-sequence/description/?envType=study-plan-v2&envId=top-100-liked
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */

import java.util.HashSet;

/**
 * set去重
 * 找连续数组的起始位置，从起始位置开始计算最大长度。
 * 遍历数组nums[i] 是否满足nums[i]-1 在set中
 *      若不在，该数字就是最长连续的起始位置
 *          从起始位置开始查找，连续的数组并记录长度。直到不连续，更新最大长度。
 */
public class code128 {
    public int longestConsecutive(int[] nums) {

        //set去重
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            //遍历数组nums[i] 是否满足nums[i]-1 在set中
            // *      若不在，该数字就是最长连续的起始位置
            if (!set.contains(num-1)){
//                从起始位置开始，遍历set直到不满足连续。
                int t = num;
                int cnt = 1;
                while (set.contains(t + 1)){
                    t++;
                    cnt++;
                }
                 max = Math.max(max, cnt);
            }
        }
        return max;
    }
}
