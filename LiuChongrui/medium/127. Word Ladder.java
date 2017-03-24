/*
思路：此题时126的子问题，具体题解参看126
 */
public class Solution {
    List<List<String>> ans = new ArrayList<List<String>>();
    Map<String,Integer> wordHash = new HashMap<String,Integer>();
    Map<Integer,List<Integer>> neibormap = new HashMap<Integer, List<Integer>>();

    class qelement{
        int val;
        int step;
        public qelement(int val){
            this.val = val;
            step = Integer.MAX_VALUE;
        }
    }

    public void genNeibor(int id,String a){
        StringBuilder sb;
        String str;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < a.length(); i++){
            sb = new StringBuilder(a);
            for (char c = 'a'; c <= 'z'; c++){
                if (c == a.charAt(i)) continue;
                sb.setCharAt(i,c);
                str = sb.toString();
                if (wordHash.containsKey(str)){
                    int num = wordHash.get(str);
                    list.add(num);
                }
            }
        }
        neibormap.put(id,list);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int start = -1;
        int end = -1;
        for (int i = 0; i < wordList.size(); i++){
            if (endWord.equals(wordList.get(i))) end = i;
            if (beginWord.equals(wordList.get(i))) start = i;
            wordHash.put(wordList.get(i),i);
        }
        if (start == -1) wordList.add(beginWord);
        int n = wordList.size();
        for (int i = 0; i < n; i++){
            genNeibor(i,wordList.get(i));
        }
        if (end == -1) return 0;
        return findstep(start,end,n);
    }

    public int findstep(int start,int end,int n){
        qelement[] f = new qelement[n];
        for (int i = 0; i < n; i++){
            f[i] = new qelement(i);
        }
        f[start].step = 0;
        int [] q = new int [n];
        q[0] = start;
        int t = -1,w = 0;
        Integer x;
        List<Integer> list;
        while (t < w){
            t++; x = q[t];
            list = neibormap.get(x);
            for (Integer i:list)
                if (f[i].step > f[x].step + 1) {
                    w++;
                    q[w] = i;
                    f[i].step = f[x].step + 1;
                    if (i == end) return f[end].step+1;
                }
        }
        //
        return 0;

    }
    //
}