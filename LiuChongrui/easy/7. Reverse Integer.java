/*
水题
Integer.MAX_VALUE    int 最大值
Integer.MIN_VALUE    int 最小值
 */

public class Solution {
    public int reverse(int x) {
        int flag = 1;
        if (x < 0) {x = -x; flag = -1;}
        long rev = 0;
        int y = 0;
        while (x > 0){
            y = x % 10;
            x = x / 10;
            rev = rev * 10 + y;
        }
        //System.out.println(rev);
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) return 0;
        else return flag * (int)rev;
    }
}