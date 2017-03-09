/*
思路：不用乘，除，取余操作实现除法
用减法代替除法，但是单纯的一次次减会超时，所以我们将减数依次变成 1倍，2倍，4倍，8倍。。。当被减数大小不够时，又将减数变成1倍，2倍，4倍。。。
算法复杂度为(logN)^2
 */
public class Solution {
    public int divide(int dividend, int divisor) {
        int flag = 1;
        long z_dividend = dividend;
        long z_divisor = divisor;

        if (z_dividend < 0) {flag = -flag; z_dividend = -z_dividend;}
        if (z_divisor < 0) {flag = -flag; z_divisor = -z_divisor;}
        long a = z_dividend;
        long ans = 0;
        while (a >= z_divisor){
            long b = z_divisor;
            long res = 0;
            while (a >= b){
                a = a - b;
                b = b + b;
                res = (res == 0?1:res + res);
                ans = ans + res;
            }
        }

        return (int)(flag*ans>Integer.MAX_VALUE?Integer.MAX_VALUE:flag*ans);
    }
}