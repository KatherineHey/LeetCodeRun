package binarySearch;

public class WaysToSplit {
    public int waysToSplit(int[] nums) {
        int N = nums.length;
        
        // prefix array
        int[] A = new int[N];
        int MOD = (int) (1e9 + 7);
        
        A[0] = nums[0];
        
        for (int i = 1; i < N; i++) {
            A[i] = A[i-1] + nums[i];
        }
        
        int ways = 0;
        for (int i = 1; i < N - 1; ++i) {
            if (A[i - 1] > A[N - 1] - A[i - 1]) break;  // early termination

            int leftBoarder = helper(A, A[i - 1], i, true);
            int rightBorder = helper(A, A[i - 1], i, false);

            if (leftBoarder == -1 || rightBorder == -1) continue;  // none is satisfied

            ways = (ways + (rightBorder - leftBoarder + 1) % MOD) % MOD;
        }
        
        return ways;
    }
    
    // (start, end]
    private int helper(int[] A, int leftSum, int index, boolean searchLeft) {
        int N = A.length;
        int l = index;
        int r = N-2;
        int res = -1;
        
        while (l <= r) {
            int mid = l + (r-l)/ 2;
            int midSum = A[mid] - A[index-1];
            int rightSum = A[N-1] - A[mid];
            if (leftSum <= midSum && midSum <= rightSum) {
                res = mid;
                if (searchLeft) r = mid-1;
                else l = mid+1;
            } else if (leftSum > midSum) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        
        return res;
    }
}
