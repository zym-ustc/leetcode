/*
思路：这题和39题不同的地方在于集合元素最多取一次了，但是会出现相同元素。
    一种思想是排序后记录下每个元素的出现个数，然后转化成39题做，但是预处理稍显麻烦，
    另一种是先排序，然后搜索，判断当前元素取还是不取，但是这样会出现重复[1,1,2] target=3, [1,2]出现两次。
    所以我们可以这样做，若决定当前元素不取，则跳过后面所有与之相等的所有元素。这个想法类似于通配符*的处理
 */
public class Solution {
    List<List<Integer>> ans;
    private void dfs(int[] a,int sta,int target,List<Integer> res){
        if (target == 0){
            ans.add(new ArrayList<Integer>(res));
            return;
        }
        if (sta == a.length) return;
        if (a[sta] > target) return;
        //no add a[sta]
        int j = 1;
        while (sta+j < a.length && a[sta+j] == a[sta]) j++;
        dfs(a,sta+j,target,res);
        //add
        res.add(a[sta]);
        dfs(a,sta+1,target-a[sta],res);
        res.remove(res.size()-1);
    }
    public List<List<Integer>> combinationSum2(int[] a, int target) {
        Arrays.sort(a);
        ans = new ArrayList<List<Integer>>();
        dfs(a,0,target,new ArrayList<Integer>());
        return ans;
    }
}