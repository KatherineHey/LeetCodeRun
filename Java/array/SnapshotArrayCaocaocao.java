package array;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 1146. Snapshot Array
 * @param index
 * @param snap_id
 * @return
 */
public class SnapshotArrayCaocaocao {
    public static class SnapshotNode {
        public int val;
        
        // <snapshot id, value>
        public HashMap<Integer, Integer> snapshotValues;
        
        public SnapshotNode (int nodeValue) {
            val = nodeValue;
            snapshotValues = new HashMap<Integer, Integer>();
        }
    }
    
    SnapshotNode[] snapshotArr;
    int snapshot_id;
    public SnapshotArrayCaocaocao(int length) {
        snapshotArr = new SnapshotNode[length];
        snapshot_id = -1;
        
        // Initially, each element equals 0.
        for (int i = 0; i < length; i++) {
            snapshotArr[i] = new SnapshotNode(0);
        }
    }
    
    public void set(int index, int val) {
        snapshotArr[index].val = val;
    }
    
    public int snap() {
        snapshot_id ++;
        
        for (SnapshotNode node: snapshotArr) {
            node.snapshotValues.put(snapshot_id, node.val);
        }
        
        return snapshot_id;
    }
    
    public int get(int index, int snap_id) {
        return snapshotArr[index].snapshotValues.get(snap_id);
    }
}
