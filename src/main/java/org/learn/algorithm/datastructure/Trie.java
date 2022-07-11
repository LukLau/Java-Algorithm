package org.learn.algorithm.datastructure;

/**
 * 字典树
 *
 * @author luk
 * @date 2021/4/14
 */
public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        TrieNode p = root;
        char[] words = word.toCharArray();
        for (char c : words) {
            if (p.next[c - 'a'] == null) {
                p.next[c - 'a'] = new TrieNode();
            }
            p = p.next[c - 'a'];
        }
        p.word = word;
    }

    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        char[] words = word.toCharArray();

        TrieNode p = root;
        for (char c : words) {
            if (p.next[c - 'a'] == null) {
                return false;
            }
            p = p.next[c - 'a'];
        }
        return word.equals(p.word);
    }

    public boolean searchII(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        char[] words = word.toCharArray();

        return internalSearch(root, 0, words);
    }

    private boolean internalSearch(TrieNode p, int start, char[] words) {
        if (start == words.length) {
            return root.word != null;
        }
        char word = words[start];
        if (word == '.') {
            for (TrieNode nextTrieNode : p.next) {
                if (nextTrieNode != null && internalSearch(nextTrieNode, start + 1, words)) {
                    return true;
                }
            }
        } else {
            if (p.next[word - 'a'] == null) {
                return false;
            }
            return internalSearch(p.next[word - 'a'], start + 1, words);
        }
        return false;
    }


    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return false;
        }
        TrieNode p = root;
        char[] words = prefix.toCharArray();
        for (char c : words) {
            if (p.next[c - 'a'] == null) {
                return false;
            }
            p = p.next[c - 'a'];
        }
        return true;
    }

    static class TrieNode {
        public TrieNode[] next;
        public String word;

        public TrieNode() {
            next = new TrieNode[26];
        }
    }

}

