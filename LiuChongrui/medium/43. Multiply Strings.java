/*
思路：高精度乘法，这里是每一位数都隔开了（1/2/3/4/5），也可以压缩一下多位多位处理（1/23/45）
 */
public class Solution {
    public String multiply(String a, String b) {
        int k=0,g=0;
        int lena = a.length();
        int lenb = b.length();
        int [] c = new int [lena+lenb];
        Arrays.fill(c,0);
        for (int i = lena-1; i >= 0; i--){
            g = 0;
            for (int j = lenb-1; j >= 0; j--){
                k = (lena+lenb-2-i-j);
                c[k] += (int)(a.charAt(i)-'0')*(int)(b.charAt(j)-'0')+g;
                g = c[k]/10;
                c[k] = c[k]%10;
            }
            if (g > 0) c[k+1] += g;
        }
        int high = lena+lenb-1;
        while (high > 0 && c[high] == 0) high--;
        StringBuilder s = new StringBuilder();
        for (int i = high; i >= 0; i--)
            s.append(c[i]);
        return s.toString();
    }
}