package dynamicprogramming;

/**
 * 877. Stone Game
 * @author Katherine
 * https://leetcode.com/problems/stone-game/discuss/154610/DP-or-Just-return-true
   * 典型的博弈问题，也是一个典型的min-max问题。通常使用算diff的方法把min-max转为求max。
 *
 */
public class StoneGame {
    /**
     * dp[i][j] actually means maximum(alex stone - lee stone) and maximum(lee stone - alex stone) alternatively,
     * depending on who picks from piles[i]~piles[j] first.
        If alex picks from piles[i]~piles[j] first, then dp[i][j] means maximum(alex stone - lee stone);
        If Lee pick from piles[i]~piles[j] frist, then dp[i][j] means maximum(lee stone - alex stone) .
        
        
        let's make an assumption now it's Lee choosing.

        There are two parts in the max function:
        1st part: piles[i] - dp[i+1][j], if lee picks pile i, then
        Lee stone - David stone = piles[i] (Lee picks pile i) + {piles Lee picked in piles [i+1, j]} - {piles David picked in piles [i+1, j] }
        = piles[i] - ({piles David picked in piles [i+1, j] } - {piles Lee picked in piles [i+1, j]})
        = piles[i] - dp[i+1][j]
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int N = piles.length;
        
        int[][] dp = new int[N][N];
        for (int i = 0 ; i < N; i++) dp[i][i] = piles[i];

        for (int d = 1 ; d < N; d++) { /*length*/
            for (int i = 0; i < N-d; i++) {
                dp[i][i+d] = Math.max(piles[i]- dp[i+1][i+d], piles[i+d]-dp[i][i+d-1]);
            }
        }
        
        return dp[0][N-1]>0;
    }
}
