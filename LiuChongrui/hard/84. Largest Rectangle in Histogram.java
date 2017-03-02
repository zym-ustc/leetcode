/*
思路：最容易想到的方法是，我们枚举一个立柱的高度X，左右扩展找到第一次比它矮的作为边界，复杂度为O（n^2）
        正解：从左到右扫描，依次将元素进栈，栈中需要维护一个上升序列（...<Z<Y），当前元素X如果比栈顶元素Y矮时，我们可以确定Y的右边界是X，
        又因为栈中元素是升序的，所以Y的左边界是栈顶的第二个元素,所以Y的双边界找到，退栈计算，然后X又和新栈顶元素Z比较大小。。。
        直到当前栈顶元素比X小，X进栈。
        为了最后将所有的栈中元素都退栈计算，我们最后添加一个元素0进栈。算法复杂度为O（n）
 */

public class Solution {
    public int largestRectangleArea(int[] nums) {

        int top = 0;
        int [] stack = new int[nums.length+2];
        stack[0] = -1;
        int ans = 0;
        for (int i = 0; i <= nums.length; i++){
            int x = (i == nums.length? 0:nums[i]);
            if (top == 0 || x >= nums[stack[top]]){
                stack[++top] = i;
            }
            else{
                while (top > 0 && x <= nums[stack[top]]){
                    int area = (i - stack[top - 1] - 1) * nums[stack[top]];
                    ans = Math.max(ans,area);
                    top--;
                }
                stack[++top] = i;
            }
        }
        return ans;
    }
}