/*
思路：这题和18.4sum问题类似，具体看18题
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] a) {
        Arrays.sort(a);
        int l,r,x;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < a.length; i++){
            if (a[i] > 0) break;
            if (i>0 && a[i] == a[i-1]) continue;
            x = 0 - a[i];
            l = i+1; r = a.length-1;
            while (l < r){
                if (a[l] + a[r] == x) {
                    ans.add(new ArrayList<Integer>(Arrays.asList(a[i],a[l],a[r])));
                    l++;
                    r--;
                    while (l < r && a[l]==a[l-1]) l++;
                    while (l < r && a[r]==a[r+1]) r--;
                }
                else if (a[l] + a[r] > x) r--;
                else l++;
            }
        }
        return ans;
    }
}