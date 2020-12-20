package array;

public class CheckArrayFormationThroughConcatenation {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int i = 0; 
        while( i < arr.length) {
            int originalI = i;
            int arrNum = arr[i];
            int row = findRow(arrNum, pieces);
            if (row == -1) return false;
            // check the following of the arr till the end od the pieces in that row
            
            for (int j = 1; j < pieces[row].length; j++) {
                if (arr[i+j] != pieces[row][j]) return false;
                if (i >= arr.length) return false;
            }
            
            i = originalI + pieces[row].length;
        }
        
        return true;
    }
    
    public int findRow(int arrNum, int[][] pieces) {
        for (int i = 0 ; i < pieces.length; i++) {
            if (arrNum == pieces[i][0])
                return i;
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        CheckArrayFormationThroughConcatenation ca = new CheckArrayFormationThroughConcatenation();
        int[] arr = {37,69,3,74,46};
                ;
        int[][] pieces = {
                {37,69,3,74,46}
        };
        System.out.println(ca.canFormArray(arr, pieces));
    }
}
