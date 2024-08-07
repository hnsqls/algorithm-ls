package com.leetcode.doubleP;

/**
 * 42接雨水
 */
public class Code42 {
    /**
     * 双指针
     * l,r分别从左右两端开始
     * l左端指向第一个非0 元素，r从右端开始指向第一个非0 元素
     * 盛水：决定因素是短边，所以以短边开始从左到右开始盛水
     * 设短边为t 若 t - height[i] < 0  说明该位置不能盛水
     *  t - height[i] > 0  ans += t -height
     *  为了避免重复计算接水，我们每次循环一边都将所有的高度-接水高度，
     *  O（m*n）
     * @param height
     * @return
     */

    /**
     * 双指针
     * 核心思想是计算每个列能存多少水。
     * 左指针l从左向右遍历,右指针r从右向左遍历
     * 若`height[l] < height[r]` 说明蓄水依赖于左边，那么查看依赖于左边构成的凹槽蓄水
     * 记录一下左边依赖的值即为`left_max`  `l++; 查看是否height[l] < left_max`
     * 若满足`height[l] <left_max` 说明构成凹槽，记录答案 `ans += left_max -height[l]`
     * 若不满足小于条件，说明构不成凹槽而是凸槽或者平槽，不能蓄水。并更新`left_max = height[l]`
     * 若`height[l] > height[r]` 说明蓄水依赖于左边，那么查看依赖于右边构成的凹槽蓄水。
     * 记录一下右边边依赖的值即为`right_max`  `r--; 查看是否height[r] < right_max`
     * 若满足`height[r] <left_max` 说明构成凹槽，记录答案 `ans += left_max -height[r]`
     * 若不满足小于条件，说明构不成凹槽而是凸槽或者平槽，不能蓄水。并更新`left_max = height[l]`
     * 当 l = r 循环结束
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int res = 0;
        int l = 0, r = height.length - 1, l_max = 0, r_max = 0;
        while (l < r){
            if (height[l] < height[r]){
                if (height[l] < l_max){
                    res += l_max-height[l];
                }else {
                    l_max = height[l];
                }
                l++;
            }else {
                if (height[r] < r_max){
                    res += r_max-height[r];

                }else {
                    r_max = height[r];
                }
                r --;
            }
        }
        return res;
    }

}
