/*
思路：这题和有题很类似：就是说将一个a[1..n]的数组排序，数组元素1..n各出现一次
    同样我们将元素i排到a[i-1]的位置，若当前k位置的元素不等于k+1，那么k应该放到a[k]-1位置上，所以我们将k和a[k]-1位置上的元素
    交换。继续看k位置元素是否等于k+1,这样每交换一次，就有一个位置满足条件，所以最多可能交换n次，复杂度也是O（n）
 */
public class Solution {
    public void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public int firstMissingPositive(int[] nums) {
        int j;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == i+1) continue;
            while (nums[i] != i+1){
                j = nums[i];
                if (j <= 0 || j > nums.length || nums[i] == nums[j-1]) break;
                swap(nums,i,j-1);
            }
        }
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != i+1) return i+1;
        }
        return nums.length+1;
    }
}