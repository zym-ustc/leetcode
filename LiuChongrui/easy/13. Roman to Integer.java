/*
水题
注意罗马数字的左减右加规则
我们先把所有字符转成对应的阿拉伯数字后再看哪些数值要取反
 */
/*
思路：罗马数字转阿拉伯数字，注意一下特殊规则就行。
 */


public class Solution {
    public int romanToInt(String s) {
        int[] nums = new int[s.length()];
        int tot = 0;
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            int x = 0;
            switch (ch){
                case 'M': x = 1000; break;
                case 'D': x = 500; break;
                case 'C': x = 100; break;
                case 'L': x = 50; break;
                case 'X': x = 10; break;
                case 'V': x = 5; break;
                case 'I': x = 1; break;
                default: x = 0; break;
            }
            nums[tot++] = x;
        }
        int ans = 0;
        for (int i = 0; i < tot; i++){
            if (i < tot - 1 && nums[i+1] > nums[i])
                ans = ans - nums[i];
            else ans = ans + nums[i];
        }
        return ans;
    }
}