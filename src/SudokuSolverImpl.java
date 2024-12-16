public class SudokuSolverImpl implements SudokuSolver {
    private int[][] grid = new int[9][9];

    @Override
    public boolean solve() {
        return solve(0, 0);
    }

    private boolean solve(int r, int c){
        if (r == 9) {
            return true; // Reached the end of the grid
        }
        if (c == 9) {
            return solve(r + 1, 0); // Move to the next row
        }
        if (grid[r][c] != 0) {
            if(isValid(r, c)) {
                return solve(r, c + 1); // Move to the next column
            }
            return false;
        }

        for (int num = 1; num <= 9; num++) {
            grid[r][c] = num;
            if (isValid(r, c) && solve(r, c + 1)) {
                return true; // Move to the next column

            }
            grid[r][c] = 0; // Backtrack
        }
        return false; // No solution found
    }

    @Override
    public void set(int row, int col, int digit) {
        grid[row][col] = digit;
    }

    @Override
    public int get(int row, int col) {
        return grid[row][col];
    }

    @Override
    public void clear(int row, int col) {
        grid[row][col] = 0;
    }

    @Override
    public void clearAll() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                grid[i][j] = 0;
            }
        }
    }

    @Override
    public boolean isValid(int row, int col) {
        int num = grid[row][col];
        for (int i = 0; i < 9; i++) {
            if (i != col && grid[row][i] == num) {
                return false;
            }
            if (i != row && grid[i][col] == num) {
                return false;
            }
            int boxRow = row - row % 3 + i / 3;
            int boxCol = col - col % 3 + i % 3;
            if ((boxRow != row || boxCol != col) && grid[boxRow][boxCol] == num) {
                return false;
            }
        }
        return true;

    }

    @Override
    public boolean isAllValid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] != 0 && !isValid(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void setGrid(int[][] m) {
        grid = m;

    }

    @Override
    public int[][] getGrid() {
        return grid;
    }
}
