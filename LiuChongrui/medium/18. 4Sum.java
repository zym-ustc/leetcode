/*
思路：这类nSum问题我们都可以用一个模板来写，首先排序，然后枚举前n-2个元素，最后的两个元素用双指针的方式求得，
    这里我们加了一些剪枝，比如当前都取最小元素>target，或者都取最大元素<target，就不枚举了，其实就是一个可行性剪枝
 */
public class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> fourSum(int[] a, int target) {
        int n = 4;
        Arrays.sort(a);
        ans = new ArrayList<List<Integer>>();
        if (a.length >= n)
            nSum(a,0,target,n,new ArrayList());
        return ans;
    }
    public void nSum(int[] a, int sta, int target,int depth,List<Integer> res){
        if (a[sta] * depth > target || a[a.length-1] * depth < target) return;
        if (depth > 2) {
            for (int i = sta; i < a.length-depth+1; i++) {
                if (i > sta && a[i] == a[i - 1]) continue;
                else{
                    res.add(a[i]);
                    nSum(a, i + 1, target - a[i], depth - 1, res);
                    res.remove(res.size() - 1);
                }
            }
        }
        else{
            int l = sta, r = a.length-1;
            while (l < r){
                if (a[l] * depth > target || a[r] * depth < target) return;
                if (a[l] + a[r] == target) {
                    res.add(a[l]);
                    res.add(a[r]);
                    ans.add(new ArrayList<Integer>(res));
                    res.remove(res.size()-1);
                    res.remove(res.size()-1);
                    l++; r--;
                    while (l < r && a[l]==a[l-1]) l++;
                    while (l < r && a[r]==a[r+1]) r--;
                }
                else if (a[l] + a[r] > target) r--;
                else l++;
            }
        }
    }
}