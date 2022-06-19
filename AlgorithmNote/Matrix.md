Leetcode 48. Rotate Image

You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

```java
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        for (int layer = 0; layer < (n/2); layer++) {
            rotateLayer(matrix, layer);
        }
    }
    
    // outside whole circle call it one layer
    // first layer being layer=0
    public void rotateLayer(int[][] mat, int layer) {
        int n = mat.length;
        
        int end = n - layer - 1; // inclusive
        int start = layer;
        int len = end - start + 1;
        
        // temporarilly save the first col
        int[] firstCol = new int[len];
        for (int i = start; i <= end; i++) {
            firstCol[i-start] = mat[i][start];
        }
        
        
        for (int j = start; j <= end-1; j++) {
            int corresponding = end - (j-start);
            
            //  left      = bottom
            mat[j][start] = mat[end][j];
            
            //   bottom = right
            mat[end][j] = mat[corresponding][end];
            
            //    right = top
            mat[corresponding][end] = mat[start][corresponding];
            
            //       top              = left
            mat[start][corresponding] = firstCol[j-start];
        }
    }
```

Leetcode 542. 01 Matrix

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

```java
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> q = new LinkedList<int[]>();
        int m = mat.length;
        int n = mat[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0)
                    q.offer(new int[]{i, j});
                else
                    mat[i][j] = -1; // marked as not processed
            }
        } 
        
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        // BFS from 0 to all -1s
        // Directly update in mat while progressing
        while (!q.isEmpty()) {
            int[] node = q.poll();
            
            for (int[] dir: dirs) {
                int x = node[0] + dir[0];
                int y = node[1] + dir[1];
                
                // as long as it's process, the first assigned is the shortest
                if (x < 0 || x >= m || y < 0 || y >= n || mat[x][y] != -1)
                    continue;
                
                mat[x][y] = mat[node[0]][node[1]]+1;
                q.offer(new int[]{x, y});
            }
        }
        
        return mat;
    }
```
