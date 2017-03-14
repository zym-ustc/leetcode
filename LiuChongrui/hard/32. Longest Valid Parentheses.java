/*
思路：用括号匹配的方法，我们可以得到每个左括号成功匹配的右括号序号（如果合法的话），且在这个匹配段内括号一定是是合法的。
    这里的方式是：
    1.当前是左括号，则进栈。
    2.当前是右括号，查看栈顶元素，
        a)栈顶元素是左括号，则栈顶元素与当前右括号匹配，出栈。
        b)栈顶元素是右括号，则栈中的元素已经不能匹配到合法段了，置栈空。
这样子我们可以把这个字符串分成了很多个合法段，这些合法段有些可能挨在一起，也可能是隔开的，我们要把挨在一起的拼起来组合成
一个大的合法段，最后算得最长的合法段长度。
 */

public class Solution {
    public int longestValidParentheses(String s) {
        char[] stack = new char[s.length()];
        int[] stack_id = new int[s.length()];
        int[] to_end = new int[s.length()];
        int nowans = 0, maxans = 0, top = -1;
        for (int i = 0; i < s.length(); i++){
            //intial
            char item = s.charAt(i);
            to_end[i] = -1;

            if (item == '('){
                top++;
                stack[top] = item;
                stack_id[top] = i;
            }
            else {
                if (top == -1) {
                    continue;
                }
                char ret = stack[top];
                int id = stack_id[top];
                top--;
                if (ret == '(') {
                    to_end[id] = i;
                }
                else{
                    top = -1;
                }
            }
        }
        int tail = 0;
        while (tail < s.length()){
            if (to_end[tail] < 0){
                tail++; nowans = 0;
            }
            else {
                nowans += to_end[tail] - tail + 1;
                tail = to_end[tail]+1;
                if (nowans > maxans) maxans = nowans;
            }
        }
        return maxans;
    }
}