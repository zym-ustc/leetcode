/*
思路：和26题很像，元素值和val相等就忽略
 */
public class Solution {
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == val) continue;
            nums[j] = nums[i];
            j++;
        }
        return j;
    }
}