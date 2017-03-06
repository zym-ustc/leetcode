/*
思路：其实就是如何设计key值，我一开始的想法是将每次字母出现的次数算出来拼接成一个字符串（如a3b4c1这种），然后放入hashmap中。
      其实Java提供了String.toCharArray()的方法来将字符串变成char数组。String.valueOf(charArray)的方法将字符数组变成String
      所以我们可以将String变成char数组然后将数组排序，再转化成String作为key值。
 */

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
}

//old
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,Integer> dict = new HashMap<String ,Integer>();
        int [] c = new int [26];
        int total = -1;
        List<List<String>> result = new ArrayList<List<String>>();
        for (int i = 0; i < strs.length; i++){
            String item = strs[i];
            String key = "";
            for (int j = 0; j < item.length(); j++){
                int x = item.charAt(j)-'a';
                c[x] += 1;
            }
            for (int j = 0; j < 26; j++){
                if (c[j] > 0) key = key+ ('a'+j)+c[j];
                c[j] = 0;
            }
            Integer v = dict.get(key);
            if (v == null){
                total++;
                List<String> p = new ArrayList<String>();
                result.add(p);
                p.add(item);
                dict.put(key,total);
            }
            else{
                List<String> p = result.get(v);
                p.add(item);
            }
        }
        return result;
    }
}