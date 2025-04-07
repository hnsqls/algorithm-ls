# LeetCode 75

我的更多题解：[hnsqls/algorithm-ls: 数据结构，算法](https://github.com/hnsqls/algorithm-ls)

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



## [1431. 拥有最多糖果的孩子 - 力扣（LeetCode）](https://leetcode.cn/problems/kids-with-the-greatest-number-of-candies/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	一群孩子每个有都拥有n个糖果，现在你有m个糖果，你选择将糖果给某个孩子，判断该孩子是不是拥有最多的糖果数



思路

* 先找出最多拥有的糖果数
* 遍历如果将自己的糖果全部给该孩子，是否小于最多的糖果数。

```java
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int temp = 0;
        for(int i = 0; i < candies.length; i ++){
            if(candies[i] >= temp){
                temp = candies[i];
            }
        }

        List res = new ArrayList<Boolean>(candies.length);
         for(int i = 0; i < candies.length; i ++){
            if(candies[i] + extraCandies >= temp){
                res.add(true);
            }else{
                res.add(false);
            }
        }

        return res;
    }
}
```



## [605. 种花问题 - 力扣（LeetCode）](https://leetcode.cn/problems/can-place-flowers/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	 一个数组，已经存在一部分元素（不能相邻存在元素），要求插入n个元素，但是不能相邻，判断该n个元素是否能插入。



思路

* 根据数组大小判断最多可以存多少元素
  * 比如说偶数组长度n，最多只能存 n/2.
  * 奇数，可以存n/2 或者 n/ 2 + 1.(根据第一个元素)------有问题不能这莫分析
  * ![image-20250329105149313](images/LeetCode75.assets/image-20250329105149313.png)

```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cnt = 0; // 计数
        for(int i = 0; i< flowerbed.length;i ++){
            if(flowerbed[i] == 1)
            {
                cnt ++;
            }
        }
        // 最多可以存
        //偶数
        int max= 0;
        if(flowerbed.length  % 2 == 0){
            max = flowerbed.length / 2;
        }
        // 奇数
        else{
            if(flowerbed[0] == 1){
                max = flowerbed.length/ 2 +1;
            }
            else{
                max = flowerbed.length / 2;
            }
        }
        if(cnt + n > max){
            return false;
        }
        return true;
    }
}
```

错误

![image-20250329110225554](images/LeetCode75.assets/image-20250329110225554.png)

分析：思路有问题，我上述的设想奇数长度的大小，最多可以判断只有在开头种植了才能+1,忽略了结尾，比如上述。

经过实验，对于过一个数组，间隔插入的最大值： 偶数长度 n/2, 奇数长度 (n+1)/2.

但是对于已经存在数据的数组，就不能按上述计算，因为，插入的位置同时可以在偶数位置并且在奇数位置上。



优化思路

* 查找数组的连续0
* 连续0在中间 最多可以插入 (n -1）/ 2 
* 连续0 在两边， 最多可以插入 n / 2 

如下图

连续0在数组的中间的情况：

![image-20250329112507403](images/LeetCode75.assets/image-20250329112507403.png)

连续0在数组两边的情况：

![image-20250329112729053](images/LeetCode75.assets/image-20250329112729053.png)



优化思路

* 贪心，能种就种



```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        int i = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0) {
                boolean canPlace = (i == 0 || flowerbed[i - 1] == 0) &&  // 左边没有花或是数组开头
                  (i == flowerbed.length - 1 || flowerbed[i + 1] == 0); // 右边没有花或是数组结尾
               
                if (canPlace) {
                    flowerbed[i] = 1; // 种花
                    count++;
                    if (count >= n) {
                        return true;
                    }
                    i += 2; // 跳过下一个位置，因为不能相邻
                       // 检查是否能种花，左右都没中种花就可以种花，考虑边界情况，对于边界开头，只需要确保其后没有种花，对于结尾的边界，只需要前面没有种花

                //开头边界
                boolean first = (i==0) && (flowerbed[i+1] == 0);

                //结尾边界
                boolean end =(i == flowerbed.length -1) && (flowerbed[i-1] == 0);

                // 中间
                boolean center = (flowerbed[i-1] ==0 && flowerbed[i + 1] == 0);         } else {
                    i++;
                }
            } else {
                i += 2; // 已经有花，跳过下一个位置
            }
        }
        return count >= n;
    }
}
```

边界问题处理了好久，看一下我最初的处理边界

```java
                // 检查是否能种花，左右都没中种花就可以种花，考虑边界情况，对于边界开头，只需要确保其后没有种花，对于结尾的边界，只需要前面没有种花

 //开头边界
                // boolean first = (i==0) && (flowerbed[i+1] == 0);
                if(i == 0){
                     if(flowerbed[i+1] == 0){
                        first = true;
                     } 
                }

                //结尾边界
                // boolean end =(i == flowerbed.length -1) && (flowerbed[i-2] == 0);
                else if(i == flowerbed.length -1){
                    if(flowerbed[i-1] == 0){
                        end = true;
                    }
                }else{
                     // 中间
                 center = (flowerbed[i-1] ==0 && flowerbed[i + 1] == 0);
                }
```

还是下标溢出，原因是，假如数组长度就1，在判断开头的时候就i+1，下标溢出



## [345. 反转字符串中的元音字母 - 力扣（LeetCode）](https://leetcode.cn/problems/reverse-vowels-of-a-string/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

 一个字符串，将其中的元音字母，反转（交换位置）输出结果字符串。



思路

* 双指针，找到元音字母相互交换位置。

```java
class Solution {
    public String reverseVowels(String s) {

        // 元音数组
        Set<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
        );

        // sting 不可变---》转成数组
        char [] check = s.toCharArray();

        int l = 0, r = check.size - 1;
      
        while(l < r){ 
            // 从左向右找元音
            while(l < r && !vowels.contains(check[l])){
                l++;
            }
             // 从左向右找元音
            while(l < r && !vowels.contains(check[r])){
                r --;
            }

            // 交换元素

            if( l < r ){
                char t = check[l];
                check[l] = check[r];
                check[r] = t;
                l++;
                r--;
            }


        }
        return new String(check);
    
    }
}
```



## [151. 反转字符串中的单词 - 力扣（LeetCode）](https://leetcode.cn/problems/reverse-words-in-a-string/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	反转单词 一个字符串，包含多个单词，反转单词的顺序。



题目思路

* 识别单词，存入list,倒叙拼接。
* 主要是处理空格，我们可以使用split()根据空格进行分割，但是假如多个空格分割，值是有空格的？那我存的时候判断一下。



实现有误

```java
class Solution {
    public String reverseWords(String s) {
        String[] split =s.split(" ");
        StringBuilder result = new StringBuilder();
        for(int i = split.length - 1 ;i >=0 ;i --){
            if(split[i] != " "){
                // 最后一个不加空格
                if(i == 0){
                     result.append(split[i]);
                }
                // 其他的后边要加一个空格
                else{
                    result.append(split[i] + " ");
                }
            }
        }
        return result.toString();
    }
}
```

原因如下： 在处理最后一个单词的时候，判断要不要加空格，是根据分割后的下标来判断的，这种逻辑是错误的

tips:比较字符串 不要直接使用 == !=  而是使用equals。



![image-20250330222307734](images/LeetCode75.assets/image-20250330222307734.png)

问题？什么时候应该不加空格，最后一个有效单词，怎么判断？下标是判断不了。换种思路，在添加元素的前面添加空格

```java
class Solution {
    public String reverseWords(String s) {
        String[] split =s.split(" ");
        StringBuilder result = new StringBuilder();
        boolean isfirst = true;
        for(int i = split.length - 1 ;i >=0 ;i --){
            if(!split[i].isEmpty()){
                
                // 不是第一个元素，在前面就加空格
                if(!isfirst){
                    result.append(" ");
                }
                result.append(split[i]);
                isfirst =false;
            }
        }
        return result.toString();
    }
}
```

更优雅的做法，使用trim()去除首尾空格，同时分割的时候根据一个或者多个空格分割

```java
class Solution {
    public String reverseWords(String s) {
        // 去除首尾空格
        s = s.trim();
        // 按一个或多个空格分割
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        
        // 从后向前添加单词
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) {
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }
}
```

更简洁的做法： 直接reverse

```java
class Solution {
    public String reverseWords(String s) {
        // 去除首尾空格，并按一个或多个空格分割
        String[] words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
```

## [238. 除自身以外数组的乘积 - 力扣（LeetCode）](https://leetcode.cn/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	一个整型数组， 获得出自己之外的数组相乘的值数组，数组中的每个值相乘（但是不能乘自己）



思路

* 全部相乘除以自己， 注意nums[i] =0 要注意 不要除。---实验发现这种思路错误因为有0的存在导致总和是0，所以不能这样算。

优化思路

* 前缀积 + 后缀积
*  前缀鸡[i] = 前缀鸡[i-1] * 数组[i-1]
* 后缀鸡[i] = 后缀鸡[i+1] * 数组[i+1]



```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        // 前缀数组
        int[] prefix = new int[nums.length];

        //前缀乘积
        prefix[0] = 1; // 第一个元素的前缀乘积是1
        for(int i = 1 ; i < nums.length; i ++){
            prefix[i] = prefix[i-1] * nums[i-1]; // 前缀鸡[i] = 前缀鸡[i-1] * 数组[i-1]
        }
        // 后缀鸡
        int[] suffix = new int[nums.length];
        suffix[nums.length -1] = 1;
        for(int i = nums.length -2 ; i >=0 ; i --){
              suffix[i] = suffix[i+1] * nums[i+1]; // 后缀鸡[i] = 后缀鸡[i+1] * 数组[i+1]
        }

        // 结果
        for(int i = 0; i < nums.length ; i ++){
            nums[i] = prefix[i] * suffix[i];
        }
        return nums;
    }
}
```

内存优化：上述new 了两个数组，可以就弄一个前缀数组，后缀数组不需要，直接使用一个变量存一下，直接计算结果

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // 计算前缀乘积并直接存入结果数组
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i-1] * nums[i-1];
        }
        
        // 使用一个变量来跟踪后缀乘积，避免使用额外数组
        int suffix = 1;
        for (int i = n-1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }
        
        return result;
    }
}
```



## [334. 递增的三元子序列 - 力扣（LeetCode）](https://leetcode.cn/problems/increasing-triplet-subsequence/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述 

​	 一个数组，判断是否存在至少3个元素递增趋势（存在 i ,j,k 满足 nums[i] <  nums[j]<  nums[k]）。



思路

* 三循环---超时
* 动态规划-超时
* 贪心可以
  * 每一步选择中都采取当前状态下最优（即最有利）的选择，从而希望导致结果是全局最优的算法
  * 定义 first ,second,遍历，first 存最小的，second 存第二小的，如果还有比这俩更大的就是有解。
  * 为了找到这样的三元组，我们可以维护两个变量 `first` 和 `second`，分别表示当前找到的最小和次小的数。通过遍历数组，我们不断更新这两个变量，并在找到一个数大于 `second` 时确认存在递增的三元组。



```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for(int num : nums){
            if(num <= first) first = num;
            else if(num <= second) second = num;
            else{
                return true;
            }
        }
        return false;
    }
}
```



## [443. 压缩字符串 - 力扣（LeetCode）](https://leetcode.cn/problems/string-compression/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	给定一个字符数组，对于连续相同的字符进行压缩，比如 char [] = a,b,b,c,c,c  压缩成a,b，2,c，3。 计算压缩后的长度。

额外的要求，不能在新建数组，在原数组修改。

思路

* 遍历
* 遍历原数组，同时统计相同的个数，记录字符以及相同长度



优化思路

* 双指针
* 从左到右，一个读指针负责遍历原数组统计相同的字符个数，
* 一个写指针，负责在当前位置写现在统计的字符
* 后续处理，如果当前读的相同大于1就写入数字

```java
public class Solution {
    public int compress(char[] chars) {

        int read = 0;  // 读取指针，用于遍历原数组
        int write = 0; // 写入指针，用于记录压缩后的位置
        int n = chars.length;
        while(read < n){
            char temp = chars[read]; // 当前处理的字符

            int count = 0; // 当前字符的连续出现次数

            // 统计连续相同字符的数量
            while(read < n && chars[read] == temp){
                read ++;
                count ++;
            }
            //跳出上述循环，说明，出现了不相同的字符
            //写字符
            chars[write++] = temp;

            // 写统计的相同字符个数
            if(count > 1){
                //  将数字转换为字符数组并逐个写入
                for(char c : String.valueOf(count).toCharArray()){
                    chars[write++] = c;
                }
            }
        } 
        //统计长度
        return write;

    }
}

```

我的更多题解：[hnsqls/algorithm-ls: 数据结构，算法](https://github.com/hnsqls/algorithm-ls)

## [283. 移动零 - 力扣（LeetCode）](https://leetcode.cn/problems/move-zeroes/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	给定一个数组，将0元素移动到数组末尾，其他元素保持相对顺序。要求不能引入额外的空间。

思路

* 要求不能使用额外的空间，即对原数组进行修改
* 双指针，一个nonZero指针用于指向非0元素的存储下标， 一个i指针遍历数组找出非0元素。找到非0元素存就行了。
* 之后对剩余元素补0.

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int nonZero = 0;
        
        // 将所有非零元素前移
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZero++] = nums[i];
            }
        }
        
        // 将剩余位置填充为零
        for (int i = nonZero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
```



优化写法

* 使用双指针 + 交换
* 一个nonZero 指针 指向非0元素存储的下标。
* 一个i指针，从前向后遍历找出非0元素
* 两个指针值交换，交换后给当前元素赋值为0，因为当前的值已经移动到前面了，该位置就是空的也就是0

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int nonZero = 0; // 记录下一个非零元素应该放置的位置
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 交换当前元素和nonZero位置的元素
                int temp = nums[i];
                nums[i] = 0;       // 将当前位置设为0
                nums[nonZero++] = temp; // 将非零元素放到前面
            }
        }
    }
}
```

我的更多题解：[hnsqls/algorithm-ls: 数据结构，算法](https://github.com/hnsqls/algorithm-ls)



## [392. 判断子序列 - 力扣（LeetCode）](https://leetcode.cn/problems/is-subsequence/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	 两个字符串，s,t。判断s是否是t的子序列。

**子序列**：字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，`"ace"`是`"abcde"`的一个子序列，而`"aec"`不是）。

思路

* 双指针
* slow 指针遍历要判断的s字符，
* fast 指针遍历t字符。fast++
* 每当两个指针指向的值相等时：slow++。
* 当slow= s.length()说明，字串遍历完毕，也就是存在字串

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        int slow = 0,fast = 0;
        while(fast < t.length() && slow < s.length()){
            if(s.charAt(slow) == t.charAt(fast)){
                slow ++;
            }
            fast ++;
        }
        
        return slow == s.length();  
    }
}
```

tips: 注意边界，s或者t 都可能为空       `while(fast < t.length() && slow < s.length())`

我的更多题解：[hnsqls/algorithm-ls: 数据结构，算法](https://github.com/hnsqls/algorithm-ls)



## [11. 盛最多水的容器 - 力扣（LeetCode）](https://leetcode.cn/problems/container-with-most-water/description/?envType=study-plan-v2&envId=leetcode-75)

题目描述

​	给定一个数组存储的是高度，找出该数组能存储的最大的容量

如图

![image-20250407092417575](images/LeetCode75.assets/image-20250407092417575.png)



思路

* 贪心？双指针？

* 不清楚，就先模拟，先从最简单的开始，比如说上图的前四个数值的存储容量
  * l指针指向开头，r指针指向结尾，计算两个指针存储的容量`(r-l)*min(height[l],height[r])`  然后移动短的指针（因为容量是由短指针确定的，假如移动长指针，容量只会减少（长度减少，高度不变））计算移动后的容量和之前的容量比较，替换。

思路清晰了

* 双指针
* l指针指向数组的开头，r指针指向数组的结尾。
* 计算两个指针指向的值可以存储的容量`S = (r-l)*min(height[l],height[r]`
* 移动短的指针，计算容量比较，替换容量
* 双指针重合，结束

```java
class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        // l指针指向数组的开头，r指针指向数组的结尾。
        int l = 0 , r = n-1;
        int s = 0;
        while(l < r){
            // 计算两个指针指向的值可以存储的容量`S = (l-r)*min(l,r)`
            s = Math.max(s, (r - l) * Math.min(height[l],height[r]));
            if(height[l] < height[r]){
                l++;
            }else{
                r--;
            }
        }
        return s;
    }
}
```



我的更多题解：[hnsqls/algorithm-ls: 数据结构，算法](https://github.com/hnsqls/algorithm-ls)

