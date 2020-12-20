package string;

public class RearrangeSpacesBetweenWords {
    public String reorderSpaces(String text) {
        int wordsCount = 0;
        int spacesCount = 0;
        
        char[] textCh = text.toCharArray();
        
        for (int i = 0 ; i < textCh.length; i++) {
            if (textCh[i] == ' ') {
                spacesCount++;
                if (i > 0 && textCh[i-1] != ' ') wordsCount++;
            } else if (i == textCh.length-1) {
                wordsCount++;
            }
        }
        
        if (wordsCount == 1 && spacesCount == 0) return text;
        if (wordsCount == 1) {
            String word = text.trim();
            return word+getEndSpaces(spacesCount);
        }
        
        int eachSpaces = spacesCount / (wordsCount-1);
        StringBuilder eachSpace = new StringBuilder();
        for (int i = 0; i < eachSpaces; i++) {
            eachSpace.append(" ");
        }
        int endSpaces = spacesCount % (wordsCount-1);
        
        String[] words = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i= 0 ; i < words.length; i++) {
            if (words[i].length() == 0) continue;
            if (i != words.length - 1)
                sb.append(words[i]).append(eachSpace);
            else
                sb.append(words[i]);
        }
        
        sb.append(getEndSpaces(endSpaces));
        
        return sb.toString();
    }
    
    public String getEndSpaces(int endSpaces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < endSpaces; i++) {
            sb.append(" ");
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        RearrangeSpacesBetweenWords r = new RearrangeSpacesBetweenWords();
        System.out.println("start--"+r.reorderSpaces("a")+"---end");
    }
}
