/*
水题
注意的地方是string[]为空则直接输出空串
 */

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";

        int i = -1;
        boolean isend = false;
        String ans = "";
        while (!isend){
            if (i >= 0) ans = ans + strs[0].charAt(i);
            i++;
            for (int j = 0; j < strs.length; j++){
                if (i >= strs[j].length()) {
                    isend = true; break;
                }
                if (j > 0 && (strs[j].charAt(i) != strs[j-1].charAt(i))) {
                    isend = true; break;
                }
            }
        }
        return ans;
    }
}