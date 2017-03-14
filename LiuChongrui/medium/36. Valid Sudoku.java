/*
思路：水题，和求数独那题一样，三个二维数组判重，行，列，小方阵。
 */
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] square = new boolean[9][10];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (board[i][j] == '.') continue;
                int x = board[i][j] - '0';
                //System.out.println(x+" "+i+""+j+" "+(i/3*3+j/3));
                if (square[i/3*3+j/3][x] || row[i][x] || col[j][x]) return false;
                row[i][x] = true;
                col[j][x] = true;
                square[i/3*3+j/3][x] = true;
            }
        }
        return true;
    }
}