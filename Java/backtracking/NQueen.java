package backtracking;

import java.util.ArrayList;
import java.util.List;

import utility.IO;

public class NQueen {
    char[][] chess;

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        chess = new char[n][n];
        solveBoard(res, new ArrayList<StringBuilder>(), 0, n);

        return res;
    }

    public void solveBoard(List<List<String>> res, List<StringBuilder> tmp, int rowIdx, int n) {
        if (rowIdx >= n) {
            addToStringList(res, tmp, n);
            return;
        }

        StringBuilder row = new StringBuilder();
        for (int colIdx = 0; colIdx < n; colIdx++) {
            if (validSpot(rowIdx, colIdx)) {
                row.append("Q");
                // fill up the dot to the end of this row
                tmp.add(row);

                chess[rowIdx][colIdx] = 'Q';
                solveBoard(res, tmp, rowIdx + 1, n);

                // recover row
                row.deleteCharAt(row.length() - 1); // remove last Q
                row.append("."); // add a dot to replace removed Q

                // recover tmp
                tmp.remove(tmp.size() - 1);

                chess[rowIdx][colIdx] = '.';
            } else {
                row.append(".");
            }
        }
    }

    public void addToStringList(List<List<String>> res, List<StringBuilder> tmp, int n) {
        List<String> row = new ArrayList<String>();
        for (StringBuilder sb : tmp) {
            String tmpRow = sb.toString();
            if (sb.length() != n) {
                int fillUp = n - sb.length();
                while (fillUp > 0) {
                    tmpRow += ".";
                    fillUp--;
                }
            }

            row.add(tmpRow);
        }

        res.add(row);
    }

    public boolean validSpot(int row, int col) {
        int n = chess.length;

        // check whole row
        for (int i = 0; i < n; i++) {
            if (i != col && chess[row][i] == 'Q')
                return false;
        }

        // check whole col
        for (int i = 0; i < n; i++) {
            if (i != row && chess[i][col] == 'Q')
                return false;
        }

        // check diagonals to left up
        for (int x = 1; row - x >= 0 && col - x >= 0; x++) {
            if (chess[row - x][col - x] == 'Q')
                return false;
        }

        // check diagonals to right up
        for (int x = 1; row - x >= 0 && col + x < n; x++) {
            if (chess[row - x][col + x] == 'Q')
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        NQueen q = new NQueen();
        List<List<String>> res = q.solveNQueens(4);
        IO.printArrayList2D(res);
    }
}
