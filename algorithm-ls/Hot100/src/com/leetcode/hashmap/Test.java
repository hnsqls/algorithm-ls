package com.leetcode.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public void moveZeroes(int[] nums) {
        //遇到0，就将0向后移动交换交换交换，不过复杂读是O(n2)
        //优化：双指针，i指向非0元素，j指向非0元素插入的位置，
        //当i指向的元素非0，就说明应该在j指针位置上，j++
        for(int i = 0,j = 0; i < nums.length; i ++){
            if(nums[i] != 0){
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] =t;
                j++;
            }
        }
    }

    /**
     * 双指针维护两边
     * 两边的更新策略，是那边小，就更新那边
     * 因为小的那边，是决定盛水的位置，假如更新大的高度，即使还有更大的高度，对于结果来说还是由小高确定
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int maxArea = 0;
        int l = 0 , r = height.length -1;
        while (l < r){
            int t = (r - l) * Math.min(height[r],height[l]);
            maxArea = Math.max(t,maxArea);
            if (height[r] < height[l])  r --;
            else l++;

        }
        return maxArea;

    }

    /**
     * 三数字之和，且答案集合中不能出现相同的结果
     * 三重循环 o^3
     * 排序 + 双指针
     * 排序成小---》大  相同的元素在一块，
     * 设nums[i] 为3数之和的最小数，其他两个数要在i之后查找
     * 双指针，l = i +1  r = nums.length - 1
     * nums[i] > 0 说明至此后面的报考该数之后都是正数，怎么也不可能有答案
     * nums[i] + nums[l]+nums[r] < 0 更新 l++
     * nums[i] + nums[l]+nums[r] == 0 记录答案，并更新i l r,因为指向的这山个数即使有解也不能在次被记录了
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > 0) return res;

            int l = i + 1 , r = nums.length-1;
            while (l < r){
                int t = nums[i] + nums[l] +nums[r];
                if (t < 0) l ++;
                else if (t > 0) r --;
                else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    res.add(list);
                    //调整j,k元素,继续寻找是否还有其他满足条件
                    while (l + 1 < r && nums[l] == nums[l + 1]) l++;
                    while (r - 1 > l && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;


                }
            }

            while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
        }
        return  res;

    }
}
