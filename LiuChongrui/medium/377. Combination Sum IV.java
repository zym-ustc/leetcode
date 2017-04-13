/*
思路：一开始的想法是搜索，然后搜到一个组合后，由排列组合公式得到排列数，（这里需要运用到分解质因数来求排列数，不然阶乘无法存储），但还是超时。
    正解，dp或者记忆化搜索
    dp：f[i]表示和为i的排列有多少个 f[i] += f[i-a[j]] 最后一个加的数为a数组中
    记忆化：类似
 */
//dp
public class Solution {
    public int combinationSum4(int[] a, int target) {
        Arrays.sort(a);
        int[] f = new int[target+1];
        f[0] = 1;
        for(int i = 1; i <= target; i++){
            f[i] = 0;
            for (int j = 0; j < a.length; j++)
                if (i >= a[j]) f[i] += f[i-a[j]];
                else break;
        }
        return f[target];
    }
}
//记忆化搜索
public class Solution {
    int ans = 0;
    int [] f;
    public int dfs(int[] a,int sum, int target){
        if (sum > target) return 0;
        if (f[target-sum] >= 0) return f[target-sum];
        if (sum == target) return 1;
        int temp = 0;
        for (int i = 0; i < a.length; i++){
            temp += dfs(a,sum+a[i],target);
        }
        f[target-sum] = temp;
        return temp;
    }
    public int combinationSum4(int[] a, int target) {
        Arrays.sort(a);
        f = new int[target+1];
        Arrays.fill(f,-1);
        f[0] = 1;
        dfs(a,0,target);
        return f[target];
    }
}