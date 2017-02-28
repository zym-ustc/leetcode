/*
思路：brute froce,注意判断i的边界，也可以用kmp
 */
public class Solution {
    public int strStr(String haystack, String needle) {
        int i,j;
        if (needle.length() == 0) return 0;
        for (i = 0; i < haystack.length()-needle.length()+1; i++){
            for (j = 0; j < needle.length(); j++){
                if (haystack.charAt(i+j) == needle.charAt(j)) continue;
                else break;
            }
            if (j == needle.length()) return i;
        }
        return -1;
    }
}