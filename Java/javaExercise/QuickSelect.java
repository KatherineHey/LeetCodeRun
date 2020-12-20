package javaExercise;

import java.util.ArrayList;

public class QuickSelect {
    // k is 0 based
    public int quickSelect(ArrayList<Integer> list, int k) {
        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            int pivotIndex = partition(list, l, r);
            if (k == pivotIndex) {
                System.out.println("ha");
                return list.get(k);
            }
            else if (k < pivotIndex)
                r = pivotIndex - 1;
            else
                l = pivotIndex+1;
        }

        return list.get(k);
    }

    private int partition(ArrayList<Integer> list, int l, int r) {
        int pivot = list.get(r);
        // move pivot to end if using a random index for pivot
        int pivotIndex = l;
        for (int i = l; i < r; i++) {
            if (list.get(i) <= pivot) {
                swap(list, i, pivotIndex);
                pivotIndex++;
            }
                
        }
        
        // Move pivot to its final place
        swap(list, pivotIndex, r);
        return pivotIndex;
    }

    private void swap(ArrayList<Integer> list, int l, int r) {
        int tmp = list.get(l);
        list.set(l, list.get(r));
        list.set(r, tmp);
    }

    public static void main(String[] args) {
        QuickSelect qs = new QuickSelect();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(41);
        list.add(32);
        list.add(8);
        list.add(12);
        list.add(21);
        list.add(22);
        System.out.println(qs.quickSelect(list, 3));
        
    }
}
