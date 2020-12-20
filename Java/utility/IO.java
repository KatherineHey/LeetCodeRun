package utility;

import java.util.List;

public class IO {
    
    /** A utility function to print array of size n.
     */
    public static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println(); 
    }
    
    public static void print2DArray(int arr[][]) 
    { 
        int m = arr.length; 
        int n = arr[0].length;
        for (int i=0; i<m; i++) {
            for (int j = 0 ; j < n; j++) {
                System.out.print(arr[i][j]+" "); 
            }
            
            System.out.println(); 
        }
    }
    
    public static <T> void printArrayList(List<T> arr) 
    { 
        for (int i=0; i<arr.size(); ++i) 
            System.out.print(arr.get(i)+" "); 
        System.out.println();
    }
}
