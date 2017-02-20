/*
思路：一开始的思路错了，为什么呢，因为我被 Search in Rotated Sorted Array I 这题给误导了，进入了思维误区。
       先讲下 Search in Rotated Sorted Array I 我的解法。
       题目是说将一个无重复的有序数组砍成了两段，调换了一下位置，所以我们可以看到，这个数组是有两个上升段，所以我们可以
       一开始用一个二分找到最小的元素，这样我们就可以知道两段的边界了，然后分别在两段中二分搜索。

       然后Search in Rotated Sorted Array II 不同，是存在重复值的，所以用二分的方法是找不出来最小值的，举一个反例：
       [2,0,2,2,2,2] 和 [2,2,2,0,2,2] 我们二分的mid元素都是第三个，左界和右界都是2，但是最小元素0可能存在于左边，也可能存在于有半。

       正解：
       直接二分，但是判断条件稍微复杂点，我们要根据mid，target在两个上升段的相对位置来改变左界和右界的值，具体逻辑见代码
       复杂度 在最坏的情况下（全部相等）是O（n）,最好是O（logn）
 */


public class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0,right = nums.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[left]){
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            else if (nums[mid] < nums[left]){
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            else left++; //or right--
            //System.out.println(left+ " "+ right);
        }
        return false;
    }
}