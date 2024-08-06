package com.leetcode.doubleP;

import javax.swing.plaf.basic.BasicBorders;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 15. 三数之和
 * 给你一个整数数组 nums ，
 * 判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 */
public class Code15 {
    /**
         * 思路
         * 排序 + 双指针
         * 排序，将数组从小到大排序
         * 设nums[i] 为三数之和为0的最小元素
         * 在i的右边查找其他两数。l= i + 1 , r = nums.length -1
         * nums[i] > 0. 最小数都大于0，三种相加肯定不满足等于0，没答案
         * nums[i] + nums[l] +nums[r] <0 , j++
         * nums[i] + nums[l] +nums[r] > 0 ,说明其他两数和我们需要的数大，而nums[j]已经是最小了，只能移动k， k--;
         * nums[i] + nums[l] +nums[r] = 0 ,就是答案,加入到答案集合中
         * 由于要的答案不能重复，所以当我们找寻到一个答案时候，首先加入答案，
         * 然后由于我们排序过，所以相同的元素一定在一起,调整i,j,k
     */
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        //排序，将数组从小到大排序
        Arrays.sort(nums);
        //设nums[i] 为三数之和为0的最小元素
        for (int i = 0; i < nums.length - 1; i++) {
            //nums[i] > 0. 最小数都大于0，三种相加肯定不满足等于0，没答案
            // 之后的的数都大于，直接返回结果
            if (nums[i] > 0) {
                return ans;
            }
            int l = i + 1, r = nums.length - 1;
            //双指针
            while (l < r) {
                int t = nums[i] + nums[l] + nums[r];
                // * nums[i] + nums[j] +nums[k] <0 ,j++
                if (t < 0) l++;
                //nums[i] + nums[j] +nums[k] > 0 ,说明其他两数和我们需要的数大，而nums[j]已经是最小了，只能移动k， k--;
                if (t > 0) r--;
                //nums[i] + nums[j] +nums[k] = 0 ,就是答案
                if (t == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    ans.add(list);
                    //调整j,k元素
                    while (l + 1 < r && nums[l] == nums[l + 1]) l++;
                    while (r - 1 > l && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                }
            }
                //调整i元素
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
            }
        //返回List<List<Integer>>元素类型
        return ans;
    }
}

