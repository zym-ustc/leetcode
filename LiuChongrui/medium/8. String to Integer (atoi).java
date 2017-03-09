/*
思路：其实就是一道考编程的细节题，注意各种细节就可以了。。。
 */
public class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) return 0;
        int m = 1;
        int sta = 0;
        boolean ok = false;
        if (str.charAt(0) == '-'){
            m = -1;
            sta = 1;
        }
        else if (str.charAt(0) == '+'){
            m = 1;
            sta = 1;
        }
        long ans = 0;
        for (int i = sta; i < str.length(); i++){
            int x = str.charAt(i)-'0';
            if (x > 9 || x < 0) break;
            ans = ans * 10 + x;
            ok = true;
            if (m*ans > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
            else if (ans*m < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        //System.out.println(m*ans+" "+Integer.MIN_VALUE);
        if (ok)
            return (int)ans*m;
        else return 0;
    }
}