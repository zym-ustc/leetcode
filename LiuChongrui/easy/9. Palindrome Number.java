/*
水题，翻转后和原值比较，注意翻转后可能会int溢出，负数不是回文数
 */
/*
思路：1.负数不是回文数
      2.注意回文后的数可能会超int，用long存储
 */


public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        long xx = x;
        int y;
        long rev = 0;
        while (x > 0){
            y = x % 10;
            x = x / 10;
            rev = rev * 10 + y;
        }
        return (xx == rev);
    }
}