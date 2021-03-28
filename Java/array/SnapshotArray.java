package array;

import java.util.TreeMap;

public class SnapshotArray {
    TreeMap<Integer, Integer>[] snapshotArr;
    int snapshot_id;
    public SnapshotArray(int length) {
        snapshotArr = new TreeMap[length];
        snapshot_id = 0;
        
        // Initially, each element equals 0.
        for (int i = 0; i < length; i++) {
            snapshotArr[i] = new TreeMap<Integer, Integer>();
            snapshotArr[i].put(0, 0);
        }
    }
    
    public void set(int index, int val) {
        snapshotArr[index].put(snapshot_id, val);
    }
    
    public int snap() {
        return snapshot_id ++;
    }
    
    public int get(int index, int snap_id) {
        return snapshotArr[index].floorEntry(snap_id).getValue();
    }
}
