/*
思路：水题，括号匹配
 */
public class Solution {
    public boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int top = -1;
        boolean ok = true;
        for (int i = 0; i < s.length(); i++){
            char item = s.charAt(i);
            if (item == '(' || item == '{' || item == '['){
                stack[++top] = item;
            }
            else{
                if (top < 0) {ok = false; break;}
                char op = stack[top--];
                if (op == '(' && item == ')') continue;
                if (op == '[' && item == ']') continue;
                if (op == '{' && item == '}') continue;
                ok = false; break;

            }
        }
        if (top >= 0) ok = false;
        if (ok == false) return false;
        else return true;
    }
}