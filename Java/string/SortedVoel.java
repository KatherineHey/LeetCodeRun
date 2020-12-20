package string;

/**
 * 5555. Count Sorted Vowel Strings
 * @author Katherine
 *
 */
public class SortedVoel {
    public int countVowelStrings(int n) {
        int[] vowels = {1,1,1,1,1};
        
        for (int i = 1; i < n; i++) {
            vowels[0] = vowels[0] + vowels[1] + vowels[2] + vowels[3] + vowels[4];
            vowels[1] = vowels[1]+ vowels[2] + vowels[3] + vowels[4];
            vowels[2] = vowels[2] + vowels[3] + vowels[4];
            vowels[3] = vowels[3] +  vowels[4];
        }
        
        return vowels[0] + vowels[1] + vowels[2] + vowels[3] + vowels[4];
    }
}
