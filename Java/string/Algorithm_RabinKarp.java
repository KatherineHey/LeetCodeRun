package string;

import java.math.BigInteger;
import java.util.Random;

/**
 * https://algs4.cs.princeton.edu/53substring/RabinKarp.java.html
 * note: Las Vegas Rule. The pattern match is guaranteed
 * @author Katherine
 *
 */

public class Algorithm_RabinKarp {
    private String pat;      // the pattern  // needed only for Las Vegas

    private int m;           // pattern length
    private long q;          // a large prime, small enough to avoid long overflow
    private int R;           // radix
    private long RM;         // R^(M-1) % Q
    
    private long patternHash;           // hash for pattern

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public Algorithm_RabinKarp (String pat) {
        this.pat = pat;
        this.m = pat.length();
        this.R = 256;
        this.q = 101;//longRandomPrime();
        
        // -----------!!!!-------precompute R^(m-1) % q for use in removing leading digit-----!!!!!!--------
        RM = 1;
        
        for (int i = 0 ; i < m-1; i++) {
            RM = (R * RM) % q;
        }

        this.patternHash = hash(pat, m);
    }
    
    // Compute hash for key[0..m-1]. 
    private long hash(String key, int m) { 
        long tmphash = 0;
        
        for (int i = 0; i < m; i++) {
            tmphash = (tmphash*R + key.charAt(i)) % q;
        }
        
        return tmphash;
    }
    
 // Las Vegas version: does pat[] match txt[i..i-m+1] ?
    private boolean check(String txt, int i) {
        // Only when hash values match, then check if the characters match
        for (int j = 0 ; j < m; j++) {
            if (txt.charAt(j + i) != pat.charAt(j))
                return false;
        }
        
        return true;
    }
    
    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param  txt the text string
     * @return the index of the first occurrence of the pattern string
     *         in the text string; n if no such match
     */
    public int search(String txt) {
        int txtlen = txt.length();
        if (txtlen < m) return -1;
        
        // offset 0
        long partialtxthash = hash(txt, m);
        if (patternHash == partialtxthash && check(txt, 0))
            return 0;
        
        // start from 1
        for (int i = m; i < txtlen; i++) {
            // remove leading digit hash add trailing digit hash
            long partialtxtminus = txt.charAt(i-m);
            partialtxthash = ((partialtxthash - partialtxtminus*RM) * R + txt.charAt(i)) % q;
            
            // We might get negative value of t, converting it 
            // to positive 
            if (partialtxthash < 0) 
                partialtxthash = (partialtxthash + q); 
            
            int startIndex = i - m + 1;
            if (partialtxthash == patternHash && check(txt, startIndex))
                return startIndex;
        }
        
        return -1;
    }
    
    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        
        return prime.longValue();
    }
    
    /** 
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String txt = "RGEEKS"; 
        String pat = "GEEK"; 
        Algorithm_RabinKarp ark = new Algorithm_RabinKarp(pat);
        System.out.println(ark.search(txt));
    }
}
