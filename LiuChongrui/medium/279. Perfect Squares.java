/*
思路：f[i]表示数i最少用多少个平方数和表示
    f[i] = f[i-j+j]+f[j*j]
    应该用记忆华搜索更加快速，
    还有一些数学方法见discuss区
 */
public class Solution {
    public int numSquares(int n) {
        int[] f = new int [n+1];
        int[] que = new int [n];
        f[0] = 0;
        int total = 0;
        for (int i = 1; i <= n; i++){
            if (i*i > n) break;
            f[i*i] = 1;
            que[total++] = i*i;
        }

        for (int i = 2; i <= n; i++){
            for (int j = 0; j < total; j++){
                if (que[j] >= i) break;
                if (f[i] == 0) f[i] = f[que[j]] + f[i-que[j]];
                else f[i] = Math.min(f[i],f[que[j]]+f[i-que[j]]);
            }

        }
        return f[n];
    }
}