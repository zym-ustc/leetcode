/*
思路：f[i][j]表示以(i,j)为右下顶点的正方形边长最长是多少，定义几个辅助数组如下
left[i][j]表示(i,j)点往左有多少个连续的1
up[i][j]表示(i,j)点往上有多少个连续的1
1).(i,j)为1 f[i][j] = min(f[i-1][j-1]+1,left[i][j],up[i][j])
2).(i,j)为0 f[i][j] = 0
 */


public class Solution {
    private int getmin(int x,int y){
        if (x < y) return x;
        else return y;
    }

    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        if (n == 0 ) return 0;
        int m = matrix[0].length;
        if (m == 0) return 0;
        int [][] f = new int[n][m];
        int [][] up = new int[n][m];
        int [][] left = new int [n][m];

        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++){
                up[i][j] = 0;
                left[i][j] = 0;
                if (matrix[i][j] == '1'){
                    up[i][j] = (i > 0? up[i-1][j] + 1:1);
                    left[i][j] = (j > 0? left[i][j-1] + 1:1);
                }
                f[i][j] = matrix[i][j]-'0';
                if (f[i][j] > 0){
                    f[i][j] = getmin(up[i][j],left[i][j]);
                    if (i > 0 && j > 0)f[i][j] = getmin(f[i][j],f[i-1][j-1]+1);
                    if (f[i][j] > ans) ans = f[i][j];
                }
            }
        //
        return ans*ans;
    }
}