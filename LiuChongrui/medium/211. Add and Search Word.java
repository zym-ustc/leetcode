/*
思路：字典树的运用。
当遇到同通配符"."时就枚举26个字母，没什么比较巧妙的方法。
同样poj有道题还有通配符"*",一样的方式处理
 */

public class WordDictionary {

    class TrieNode {
        // Initialize your data structure here.
        TrieNode son[];
        boolean isEnd;
        int num;
        char charactor;
        public TrieNode() {
            son = new TrieNode[26];
            num = 1;
            isEnd = false;
            charactor = ' ';
        }
    }

    private TrieNode root;

    public WordDictionary(){
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++){
            int c_index = word.charAt(i) - 'a';
            if (p.son[c_index] != null){
                p = p.son[c_index];
                p.num++;
            }
            else{
                p.son[c_index] = new TrieNode();
                p = p.son[c_index];
            }
        }
        p.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    private boolean find(TrieNode p, String word,int i,boolean isEnd){
        if (i >= word.length()){
            return isEnd;
        }
        if (word.charAt(i) == '.'){
            for (int j = 0; j < 26; j++){
                if (p.son[j] != null) {
                    boolean ok = find(p.son[j], word, i + 1, p.son[j].isEnd);
                    if (ok) return true;
                }
            }
            return false;
        }
        else
        {
            int c_index = word.charAt(i)-'a';
            if (p.son[c_index] != null){
                //System.out.println(word.charAt(i) + " " + p.son[c_index].isEnd);
                return find(p.son[c_index],word,i+1,p.son[c_index].isEnd);
            }
            else return false;
        }
    }


    public boolean search(String word) {
        return find(root,word,0,false);
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");