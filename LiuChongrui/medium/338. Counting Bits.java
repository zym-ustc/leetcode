/*
思路：f[i]表示i的二进制1的数目，将i转化为二进制数，它去掉首位的1后得到j，所有f[i] = f[j]+1

 */

public class Solution {
    public int[] countBits(int n) {
        int [] f = new int [n+1];
        f[0] = 0;
        int g = 1;
        for (int i = 1; i <= n; i++){
            if (i >= g * 2) g = g * 2;
            f[i] = f[i - g] + 1;
        }
        return f;
    }
}