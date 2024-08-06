package com.leetcode.doubleP;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */

/**
 * 题意：保持数组元素排序不变，将0元素移到最后
 * 不能复制数组
 * 思路
 *   双指针
 *   i 从前向后遍历
 *   j 从头开始，意义是非0元素可以插入的位置
 *   当 i指向非0元素时，nums[i]插入到j指向的位置 nums[j]的值保存在i指向的位置，并且j++
 *   这样经过一次循环，前面的数组都是按照原位置，并且0在最后的位置
 */
public class Code283 {
    public void moveZeroes(int[] nums) {
        for(int i = 0 ,j = 0; i <nums.length; i++){
            if (nums[i] != 0){
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                j++;
            }
        }

    }
}
