/*
思路：一个排列组合题，注意n>=2时，首位数字不能为0即可
 */

public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        long [] f = new long[11];
        f[0] = 1;
        f[1] = 10;
        long g = 9;
        for (int i = 2; i <= 10; i++){
            g = g * (11-i);
            f[i] = f[i-1] + g;
        }
        if (n >= 10) return (int)f[10];
        else return (int)f[n];
    }
}