package math;

public class CountUnhappyFriends {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        boolean[] happy = new boolean[n];
        
        for (int[] pair: pairs) {
            int a = pair[0];
            int b = pair[1];
            if (preferences[a][0] == b) happy[a] = true;
        }
        
        return 0;
    }
}
