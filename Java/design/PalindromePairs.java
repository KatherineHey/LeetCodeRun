package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 336. Palindrome Pairs
 * https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
 * http://www.allenlipeng47.com/blog/index.php/2016/03/15/palindrome-pairs/
 * @author Katherine
 *
 */
public class PalindromePairs {
    class TrieNode {
        // the n1.isWord field provides no information about the word itself,
        // which makes it impossible to distinguish the two words.
        // So it is necessary to modify the structure of the TrieNode so that we can identify the word it represents.
        // One easy way is to have an integer field to remember the index of the word in the words array.
        int index;
        
        // the list will record the indices of all words satisfying the following two conditions:
        // 1. each word has a suffix represented by the current TrieNode;
        // 2. the rest of the word forms a palindrome.
        List<Integer> list;
        TrieNode[] next;
        
        TrieNode() {
            index = -1;
            list = new ArrayList<Integer>();
            next = new TrieNode[26];
        }
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        TrieNode root = new TrieNode();
        
        for (int i = 0 ; i < words.length; i++) {
            addTrieNode(root, words[i], i);
        }
        
        for (int i = 0 ; i < words.length; i++) {
            search(words, i, root, res);
        }
        
        return res;
    }
    
    // Index is the index of word in the array
    public void addTrieNode(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i>= 0; i--) {
            int j = word.charAt(i) - 'a'; // assuming all lower case
            
            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }
            
            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }
            
            root = root.next[j];
        }
        
        root.list.add(index);
        root.index = index;
    }
    
    public void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0 ; j < words[i].length(); j++) {
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length()-1)) {
                res.add(Arrays.asList(i, root.index));
            }
            
            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) return;
        }
        
        if (root != null && root.list.size()>0) {// assume 'xyxabc' is in trie, now try 'cba'
            for (int j : root.list) {
                if (i != j) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
    }
    
    private boolean isPalindrome(String word, int i, int j) {
        while(i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        String[] words = {"lls","s","sssll"};
        PalindromePairs pp = new PalindromePairs();
        List<List<Integer>> result = pp.palindromePairs(words);
        System.out.println(result);
    }
}
