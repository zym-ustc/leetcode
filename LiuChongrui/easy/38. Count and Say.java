/*
很简单的模拟题，注意一个细节是，用+号连接String 比用StringBuilder的append方法慢很多，所以这里采用Stringbuilder
 */
public class Solution {
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++){
            StringBuilder news = new StringBuilder();
            int count = 0;
            for (int j = 0; j < s.length(); j++){
                if (j == 0) {count = 1; continue;}
                if (s.charAt(j) == s.charAt(j-1)) count++;
                else {
                    news.append(count);
                    news.append(s.charAt(j-1));
                    count = 1;
                }
            }
            news.append(count);
            news.append(s.charAt(s.length()-1));
            s = news.toString();
            //System.out.println(s);

        }
        return s;
    }
}