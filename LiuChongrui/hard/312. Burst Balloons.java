/*
思路：很明显是道区域dp问题，我们首先将数组更改下，为了坐标方便，1..n存原来的n个数，0和n+1位置存1
    f[i][j]表示消除到i,j保留的最大分数（i+1..j-1）都被消除了
    f[i][j] = max{f[i][k]+f[k][j]+a[i]*a[k]*a[j];}   i<k<j
    f[0][n+1]为最终结果
 */
public class Solution {
    public int maxCoins(int[] a) {
        int n = a.length;
        int[] b = new int[n+2];
        int[][] f = new int[n+2][n+2];
        b[0] = 1; f[0][0] = 1;
        b[n+1] = 1; f[n+1][n+1] = 1;
        for (int i = n; i >= 1; i--){
            b[i] = a[i-1]; f[i][i] = b[i];
        }
        //
        for (int len = 2; len <= n+2; len++)
            for (int i = 0; i <= n+1; i++){
                int j = i+len-1;
                if (j > n+1) break;
                f[i][j] = 0;
                for (int k = i+1; k < j; k++){
                    f[i][j] = Math.max(f[i][j],f[i][k]+f[k][j]+b[i]*b[k]*b[j]);
                }
            }
        return f[0][n+1];
    }
}