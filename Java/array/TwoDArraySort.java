package array;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import utility.IO;

public class TwoDArraySort {
    public void merge(Integer[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0]==o2[0]? o1[1]-o2[1]: o1[0]- o2[0]);
    }
    
    public String reverseWords(String s) {
        return Arrays.stream(s.split(" ")).filter(w->w.length()>0).sorted((o1, o2) -> -1).collect( Collectors.joining( " " ));
    }
    
    public static void main(String[] args) {
        Integer[][] interval = {{1,4}, {0,4}, {0,3}, {0,5}};
        TwoDArraySort tw = new TwoDArraySort();
        tw.merge(interval);
        
        IO.print2DArray(interval);
    }
}
