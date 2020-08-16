package BinaryIndexedTree;
/**
 * Tutorial for binary indexed tree
 * https://www.topcoder.com/community/competitive-programming/tutorials/binary-indexed-trees/
 *
 */
public class NumArray {
    int[] fenwickTree; // index 1 to nums.length
    int[] nums;
    FenwickTree ft;
    
    public class FenwickTree {
        public void initializeFenwickTree() {
            for (int i = 0; i < nums.length; i++) {
                update(i+1, nums[i]);
            }
        }
        
        public void update(int i, int delta) {
            while (i < fenwickTree.length) {
                fenwickTree[i]+= delta;
                
                // update leaf to parent
                i += Integer.lowestOneBit(i);
            }
        }
        
        /**
         * Query the sum of first i elements
         * @param i
         * @return
         */
        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += fenwickTree[i];
                i-= Integer.lowestOneBit(i);
            }
            
            return sum;
        }
    }
    
    public NumArray(int[] nums) {
        this.nums = nums;
        this.fenwickTree = new int[nums.length + 1];
        ft = new FenwickTree();
        ft.initializeFenwickTree();
    }
    
    public void update(int i, int val) {
        ft.update(i+1, val - nums[i] /*delta*/);
        nums[i] = val;
    }
    
    public int sumRange(int i, int j) {
        return ft.query(j+1) - ft.query(i);
    }
    
    public static void main(String[] args) {
        int[] num = {1,3,5};
        
        NumArray na = new NumArray(num);
        System.out.println(na.sumRange(0, 2));
        na.update(1, 2);
        System.out.println(na.sumRange(0, 2));
    }
}
