package com.leetcode.hashmap;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 */

import java.util.*;

/**
 * 思路
 * map维护 每个字符串字典序排序后的字符串（K）和 对应的组的集合List(value)
 */
public class code49 {

        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> map = new HashMap<>();
            for (int i = 0; i < strs.length; i++) {
                //字典序排序，
                char[] t = strs[i].toCharArray();
                Arrays.sort(t);
                //是否该组存在map中
                String k = String.valueOf(t);
                if (map.containsKey(k)){
                    //存在这样的组，加入这组对应的集合中
                    map.get(k).add(strs[i]);
                }else {
                    //不存在这样的组，自己成为一组，并加入到改组的集合
                    ArrayList<String> list = new ArrayList<>();
                    list.add(strs[i]);
                    map.put(k,list);
                }
            }

            //返回结果
            ArrayList<List<String>> res = new ArrayList<>();
            for (Map.Entry<String,List<String>> entry : map.entrySet()){
                res.add(entry.getValue());
            }
            return  res;
        }
}
