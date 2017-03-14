/*
思路 ：第一种是最暴力的brute-froce方法
       第二种是kmp算法
       第三种是bm算法
       第四种是将要模式串hash成一个数字（比如看成是128进制，溢出了按照溢出的数字存），这样每次后移一位就是去掉高位然后加上末位。
           所以每次比较就是比较两个数字。
           如果数字不相等，那么肯定不匹配。
           如果数字相等，则也有可能是hash冲突导致，这时就扫一遍模式串判断是否每一位都相等。
           据说go语言的字符串匹配的实现就是这么做的
 */

public class Solution {
    public int[] preP(String s){
        int m = s.length();
        int[] p = new int[m];
        int j = -1;
        p[0] = -1;
        for (int i = 1; i < m; i++){
            while (j>-1 && s.charAt(j+1) != s.charAt(i)) j = p[j];
            if (s.charAt(i) == s.charAt(j+1)) j++;
            p[i] = j;
        }
        return p;
    }
    public int dokmp(String a,String b,int[] p){
        int n = a.length();
        int m = b.length();
        int j = -1;
        for (int i = 0; i < n; i++){
            while (j > -1 && a.charAt(i) != b.charAt(j+1)) j = p[j];
            if (a.charAt(i) == b.charAt(j+1)) j++;
            if (j == m-1){
                return i-m+1;
            }
        }
        return -1;
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int[] p = preP(needle);
        return dokmp(haystack,needle,p);
    }
}