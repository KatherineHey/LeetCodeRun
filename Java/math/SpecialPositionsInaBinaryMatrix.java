package math;

public class SpecialPositionsInaBinaryMatrix {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        boolean[] rows = new boolean[m];
        boolean[] columns = new boolean[n];
        
        for (int i = 0 ; i < m; i++) {
            int rowOneCount = 0;
            for (int j = 0 ; j < n; j++) {
                if (mat[i][j] == 1) rowOneCount++;
            }
            
            if (rowOneCount <= 1) rows[i] = true; // can have special matrix
        }
        
        for (int j = 0 ; j < n; j++) {
            int columnOneCount = 0;
            for (int i = 0 ; i < m; i++) {
                if (mat[i][j] == 1) columnOneCount++;
            }
            
            if (columnOneCount <= 1) columns[j] = true;
        }
        
        int result = 0;
        
        for (int i = 0 ; i < m; i++) {
            for(int j = 0 ; j < n; j++) {
                if (mat[i][j] == 1 && rows[i] && columns[j]) result++;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[][] mat = {{ 1,0,0, 0},
                        {0,0,1, 0},
                        {1,0,0, 0}};
        int[][] mat2 = {{1,0,0},
                        {0,1,0},
                        {0,0,1}};
        int[][] mat3 = {{0,0,0,1},
                        {1,0,0,0},
                        {0,1,1,0},
                        {0,0,0,0}};
        int[][] mat4 = {{0,0,0,0,0},
        {1,0,0,0,0},
        {0,1,0,0,0},
        {0,0,1,0,0},
        {0,0,0,1,1}};
        SpecialPositionsInaBinaryMatrix sp = new SpecialPositionsInaBinaryMatrix();
        System.out.println(sp.numSpecial(mat4));
    }
}
