/*
思路：因为没有重复数字，所以数组分成了两个上升段。我们可以用二分查找找到最小的数，就可以找到两段的分界点，然后在两段中
分别采用二分查找target
 */
public class Solution {
    private int find(int nums[],int target,int left,int right){
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while (left < right){
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) left = mid+1;
            else right = mid;
            //System.out.println(left+" "+right);
        }
        int privot = left;
        //System.out.println(privot);
        //find left and right
        int x = find(nums, target, 0, privot-1);
        if (x == -1)
            x = find(nums, target, privot, nums.length-1);
        return x;

    }
}