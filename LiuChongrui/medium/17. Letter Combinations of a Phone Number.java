/*
思路：很简单的一个搜索题，最好建立几个常量数组来处理字母的匹配
 */
public class Solution {
    List<String> ans;
    final int[] con = {0,0,3,3,3,3,3,4,3,4};   //数字对应几个字母
    final int[] sta = {0,0,0,3,6,9,12,15,19,22};  //开头字母序号
    public void dfs(String s,int index,StringBuilder res){
        if (index == s.length()){
            ans.add(res.toString());
        }
        else{
            int x = s.charAt(index)-'0';
            for (int i = sta[x]; i < sta[x]+con[x]; i++){
                res.append((char)(i+(int)'a'));
                dfs(s,index+1,res);
                res.deleteCharAt(res.length()-1);
            }
        }
    }
    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<String>();
        if (digits.length() == 0) return ans;
        dfs(digits,0,new StringBuilder());
        return ans;
    }
}