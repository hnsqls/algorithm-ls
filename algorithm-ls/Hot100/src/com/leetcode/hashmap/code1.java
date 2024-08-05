package com.leetcode.hashmap;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 */

import java.util.HashMap;

/**思路
 * 遍历数组，将遍历过的数组的值和下标维护在hashmap中
 * 同时，利用hashmap，判断当前数，是否满足target-当前数，在hashmap中
 * 出现返回，否则加入hashmap中
 */
public class code1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                return new int[] {i,map.get(target-nums[i])};
            }
            map.put(nums[i],i);
        }
        return null;
    }
}
