/*
思路：贪心，维持一个窗口，窗口中元素不重复，右界不断往右扩展，左界依情况收缩。用一个标志数据表示元素是否在窗口内
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int anslen = 0;
        boolean flag[] = new boolean[257];
        Arrays.fill(flag,true);
        for (int right = 0; right < s.length(); right++){
            int x = (int)(s.charAt(right));

            if (flag[x]) flag[x] = false;
            else{
                while (!flag[x]){
                    flag[s.charAt(left)] = true;
                    left++;
                }
                flag[x] = false;
            }
            int len = right-left+1;
            if (len > anslen){
                anslen = len;
            }
        }
        return anslen;
    }
}