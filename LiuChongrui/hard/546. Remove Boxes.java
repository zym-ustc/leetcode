/*
思路：类似于消除方块的游戏，很容易想到是一个区域dp问题
    我们考虑这样一段盒子 （l,r）,
    现在我们考虑消除 r 位置的盒子，有两种选择，一种是直接消去，一种是和前面的某个颜色与 r 相同的合并再消去。
    但是我们发现，不管是哪种，都和 r 之后的盒子有关，所以这l,r段不是独立的，两维无法表示
    f[l][r][k]表示从l到r段，r之后还有k个连续盒子和r颜色相等（这k个可能是直接相连，可能是消去后相连）。
    f[l][r][k] =max{ f[l][r-1][0] + (1+k)^2,                                            //直接和后面的k个一起消除
                     f[l][p][1+k] + f[p+1][r-1][0]}  l<=p<r,且color[p] = color[r]      //和前面的某个颜色相同的盒子合并后再消除，
                                                                                        // 所以f[p+1][r-1][0]代表的是要使得p,r相连需要消除的段
    PS：其实我们发现以上的搜索有些并不是满足条件的，比如连续的k个在一起，一次肯定会消除k个，但我们对假设了消除1,2,3,4,5...k个都考虑了，
        但是并没有影响结果，因为一次消除K个的结果肯定是最优的
*/
public class Solution {
    int[] num,color;
    private int dfs(int[][][] f, int l,int r,int k){
        if (l > r) return 0;
        if (f[l][r][k] > 0) return f[l][r][k];
        if (l == r) return (num[r]+k)*(num[r]+k);
        int temp = dfs(f,l,r-1,0)+(num[r]+k)*(num[r]+k);         //直接和后面的k个一起消除

        for (int i = l; i < r; i++){
            if (color[i] == color[r])
                temp = Math.max(temp,dfs(f,l,i,num[r]+k)+dfs(f,i+1,r-1,0)); //和前面的某个颜色相同的盒子合并后消除
        }
        f[l][r][k] = temp;
        return temp;
    }
    public int removeBoxes(int[] boxes) {
        int n = 0;
        num = new int[boxes.length];
        color = new int[boxes.length];
        for (int i = 0; i < boxes.length; i++){
            if (i == 0 || boxes[i-1] != boxes[i]){
                color[n] = boxes[i];
                num[n] = 1;
                n++;
            }
            else num[n-1]++;
        }
        int[][][] f = new int[n][n][boxes.length+1];
        return dfs(f,0,n-1,0);
    }
}