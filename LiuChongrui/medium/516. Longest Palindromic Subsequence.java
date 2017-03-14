/*
思路：有道很经典的题是LCS，而回文串可以看做是自己和倒叙的自己计算最长公共子序列。
c[i][j] 表示0..i 和 j..len-1的最长公共子序列.
1.s[i] == s[j] c[i][j] = c[i-1][j+1] + 1
2.s[i] != s[j] c[i][j] = max(c[i-1][j],c[i][j+1])
最终答案是c[len-1][0]
当然还可以从里往外dp
c[i][j] 表示 i..j最长的回文子序列
1.s[i] == s[j] c[i][j] = c[i+1][j-1] + 1;
2.s[i] != s[j] c[i][j] = max(c[i+1][j],c[i][j-1])
最终答案是c[0][len-1]

这里的dp数组可以用滚动数组实现，节省空间
*/

public class Solution {
    private int getmax(int x,int y){
        if (x > y) return x; else return y;
    }

    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        if (len == 0) return 0;
        int [][] c = new int[len][len];
        for (int i = 0; i < len; i++)
            for (int j = len-1; j >= 0; j--){
                c[i][j] = 0;
                if (i > 0) c[i][j] = getmax(c[i-1][j],c[i][j]);
                if (j < len-1) c[i][j] = getmax(c[i][j+1],c[i][j]);
                if (s.charAt(i) == s.charAt(j)) {
                    if (i > 0 && j < len-1)
                        c[i][j] = getmax(c[i][j],c[i-1][j+1]+1);
                    else c[i][j] = 1;
                }
            }
        return c[len-1][0];
    }
}