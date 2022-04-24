```
class TrieNode {
    // Initialize your data structure here.
    char c;
    TrieNode[] children; 
    boolean isLeaf;
    
    public TrieNode(char curr) {
        c = curr;
        children = new TrieNode[26];
    }

}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode(' ');
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        char[] word_char = word.toCharArray();
        if(word_char.length == 0)  
            return;

        TrieNode cur = root;
        for(int i = 0 ; i < word_char.length; i++){
            char c = word_char[i];
            
            if(cur.children[c-'a'] == null){
                cur.children[c-'a'] = new TrieNode(c);
            }
            
            cur = cur.children[c-'a'];
        }
        
        cur.isLeaf = true;
    }

    // Returns if the word is in the trie.
    
    public boolean search(String word) {
        TrieNode cur = root;
        
        for(int i = 0 ; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.children[c-'a'] == null){
                return false;
            }
            
            cur = cur.children[c-'a'];
        }
        
        return cur.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(int i = 0 ; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.children[c-'a'] == null){
                return false;
            }
        
            cur = cur.children[c-'a'];
        }
        
        return true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
```
