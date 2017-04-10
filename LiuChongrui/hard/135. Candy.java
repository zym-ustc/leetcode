/*
思路：其实贪心策略很简单，很容易确定哪些位置取1，然后通过这些1的位置不断扩展。
    我们可以首先将所有的位置都设为1，
    然后从前往后扫一遍，a[i]>a[i-1]，那么f[i] = f[i-1]+1, 其余情况不变
    然后从后往前扫一遍，a[i]<a[i+1], 那么f[i] = max{f[i],f[i+1]+1} ,这里取大值的原因是顶峰点的值由两边的值给出
 */
public class Solution {
    public int candy(int[] a) {
        int n = a.length;
        if (n < 2) return n;

        int [] f = new int [n];
        Arrays.fill(f,1);
        //shang po
        for (int i = 1; i < n; i++){
            if (a[i-1] < a[i]) f[i] = f[i-1]+1;
        }
        //xia po
        for (int i = n-2; i >= 0; i--){
            if (a[i] > a[i+1]) f[i] = Math.max(f[i],f[i+1]+1);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) ans += f[i];

        return ans;
    }
}