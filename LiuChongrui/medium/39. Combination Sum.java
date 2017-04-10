/*
思路：为了剪枝和去重，我们可以先将集合元素排序，
    记录一个数组res,res[i]表示第i个数取多少次
    然后搜索+可行性剪枝
    当然也可以用dp的方法
    LIST(dp[i]) = LIST(dp[i-a[j]]) + a[j]
    但是这样要枚举[1..target]，感觉会比直接搜索慢
 */
public class Solution {
    List<List<Integer>> ans;
    private void dfs(int[] a,int sta,int target,int[] res){
        if (target == 0){
            List<Integer> w = new ArrayList();
            for (int j = 0; j < res.length; j++){
                for (int k = 0; k < res[j]; k++)
                    w.add(a[j]);
            }
            ans.add(w);
            return;
        }
        if (sta == a.length) return;
        if (a[sta] > target) return;
        for (int i = 0; i <= target/a[sta]; i++){
            res[sta] = i;
            dfs(a,sta+1,target-i*a[sta],res);
            res[sta] = 0;
        }
    }
    public List<List<Integer>> combinationSum(int[] a, int target) {
        Arrays.sort(a);
        ans = new ArrayList<List<Integer>>();
        int[] res = new int[a.length];
        dfs(a,0,target,res);
        return ans;
    }
}