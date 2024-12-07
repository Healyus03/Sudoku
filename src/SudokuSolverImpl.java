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
            return solve(r, c + 1); // Skip filled cells
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(r, c)) {
                grid[r][c] = num;
                if (solve(r, c + 1)) {
                    return true;
                }
                grid[r][c] = 0; // Backtrack
            }
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

        return false;
    }

    @Override
    public boolean isAllValid() {
        return false;
    }

    @Override
    public void setGrid(int[][] m) {

    }

    @Override
    public int[][] getGrid() {
        return new int[0][];
    }
}
