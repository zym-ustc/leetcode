/*
思路：我一开始的想法是，首先二分找到一个等于target的位置，然后向左右扩展得到边界，这样的话极端条件下，复杂度退化为O（n），
    其实可以用两次二分，分别找左边界和右边界。
    tips:这里的二分处理很有意思，找左界是我们是mid=（left+right）/2，但是找右界时我们是mid=(left+right+1)/2，
    这是为了处理二分可能会一直出不来的情况，mid=（left+right+1）/2是让中间点取靠后的那个
 */
public class Solution {
    public int[] searchRange(int[] a, int target) {
        int mid=0,left = 0, right = a.length-1;
        int [] ans=  {-1,-1};
        if (right == -1) return ans;
        //find left boundry
        while (left < right){
            mid = left + (right-left)/2;
            if (a[mid] == target) right = mid;
            else if (a[mid] > target) right = mid-1;
            else left = mid + 1;
        }
        if (a[left] != target) return ans;
        else ans[0] = left;
        //find right boundry
        left = 0; right = a.length-1;
        while (left < right){
            mid = left + (right+1-left)/2;
            if (a[mid] == target) left = mid;
            else if (a[mid] > target) right = mid-1;
            else left = mid + 1;
        }
        if (a[left] != target) return ans;
        else ans[1] = left;
        return ans;

    }
}