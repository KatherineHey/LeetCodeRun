package dfs;

import java.util.Stack;

public class FriendCircles {
    boolean[] visited;
    int Circles;
    
    public int findCircleNumIterative(int[][] M) {
        Stack<Integer> dfs = new Stack<Integer>();
        int N = M.length;
        boolean[] visited = new boolean[N];
        int circle = 0;

        for (int i = 0 ; i < N; i++) {
            if (!visited[i]) {
                circle++;
                
                dfs.push(i);
                
                while (!dfs.isEmpty()) {
                    int cur = dfs.pop();
                    visited[cur] = true;
                    for (int j = 0 ; j < N; j++) {
                        if (M[cur][j] == 1 && !visited[j]) {
                            dfs.push(j);
                        }
                    }
                }
            }
        }
        
        return circle;
    }
    
    public int findCircleNum(int[][] M) {
        int N = M.length;
        visited = new boolean[N];
        
        for (int i = 0 ; i < N; i++) {
            if (!visited[i]) {
                dfs(M, i);
                Circles++;
            }
            
        }
        
        return Circles;
    }

    public void dfs(int[][] M, int i) {
        for (int j = 0 ; j < M.length; j++) {
            if (i == j) continue;
            
            if (!visited[j] && M[i][j] == 1) {
                visited[j] = true;
                dfs(M, j);
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] friends = {{1,1,0},
        {1,1,0},
        {0,0,1}};
        
        FriendCircles fc = new FriendCircles();
        //System.out.println(fc.findCircleNum(friends));
        System.out.println(fc.findCircleNumIterative(friends));
    }
    
}
