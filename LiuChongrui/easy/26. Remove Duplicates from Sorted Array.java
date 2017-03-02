/*
思路：如果当前数和前面的数不同，则把当前数填入，相同则忽略
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        int j = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] > nums[i-1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}