/*
思路：生成合法的括号匹配。递归（s,l,r）
l表示左括号的数目，r表示右括号的数目
当 l > 0 时可以填入左括号，
当 r > l 时可以填入右括号
PS：不能写成 dfs(s.append("("))的形式，s回溯时不会删除。
 */

public class Solution {
    List<String> ans;
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<String>();
        StringBuilder s = new StringBuilder();
        gen(s,n,n);
        return ans;
    }
    public void gen(StringBuilder s,int l,int r){
        if (l == 0 && r == 0) {
            ans.add(s.toString());
            return;
        }
        if (l > 0){
            s.append('(');
            gen(s,l-1,r);
            s.delete(s.length()-1,s.length());
        };
        if (r > l){
            s.append(')');
            gen(s,l,r-1);
            s.delete(s.length()-1,s.length());
        }
    }
}