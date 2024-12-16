import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuSolverTest {

    @Test
    public void testSolveEmptySudoku() {
        SudokuSolver solver = new SudokuSolverImpl();
        assertTrue(solver.solve(), "Empty Sudoku should be solvable");
    }

    @Test
    public void testSolvePredefinedSudoku() {
        SudokuSolver solver = new SudokuSolverImpl();
        int[][] predefinedSudoku = {
                {0, 0, 8, 0, 0, 9, 0, 6, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 5},
                {1, 0, 2, 5, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 9, 0},
                {0, 5, 0, 0, 0, 0, 6, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 2, 8},
                {4, 1, 0, 6, 0, 8, 0, 0, 0},
                {8, 6, 0, 0, 3, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0}
        };
        solver.setGrid(predefinedSudoku);
        assertTrue(solver.solve(), "Predefined Sudoku should be solvable");
    }

    @Test
    public void testSolveUnsolvableSudoku() {
        SudokuSolver solver = new SudokuSolverImpl();
        int[][] unsolvableSudoku = {
                {5, 1, 6, 8, 4, 9, 7, 3, 2},
                {3, 0, 7, 6, 0, 5, 0, 0, 0},
                {8, 0, 9, 7, 0, 0, 0, 6, 5},
                {1, 3, 5, 0, 6, 0, 9, 0, 7},
                {4, 7, 2, 5, 9, 1, 0, 0, 6},
                {9, 6, 8, 3, 7, 0, 0, 5, 0},
                {2, 5, 3, 1, 8, 6, 0, 7, 4},
                {6, 8, 4, 2, 0, 7, 5, 0, 0},
                {7, 9, 1, 0, 5, 0, 6, 0, 8}
        };
        solver.setGrid(unsolvableSudoku);
        assertFalse(solver.solve(), "Unsolvable Sudoku should not be solvable");
    }

    @Test
    public void testSolveUnsolvableSudoku2() {
        SudokuSolver solver = new SudokuSolverImpl();
        int[][] unsolvableSudoku = {
                {5, 0, 0, 5, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        solver.setGrid(unsolvableSudoku);
        assertFalse(solver.solve(), "Unsolvable Sudoku should not be solvable");
    }

    @Test
    public void testSetAndGet() {
        SudokuSolver solver = new SudokuSolverImpl();
        solver.set(0, 0, 5);
        assertEquals(5, solver.get(0, 0), "Value at (0,0) should be 5");
    }

    @Test
    public void testClear() {
        SudokuSolver solver = new SudokuSolverImpl();
        solver.set(0, 0, 5);
        solver.clear(0, 0);
        assertEquals(0, solver.get(0, 0), "Value at (0,0) should be cleared");
    }

    @Test
    public void testClearAll() {
        SudokuSolver solver = new SudokuSolverImpl();
        solver.set(0, 0, 5);
        solver.clearAll();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(0, solver.get(i, j), "All values should be cleared");
            }
        }
    }

    @Test
    public void testIsValid() {
        SudokuSolver solver = new SudokuSolverImpl();
        solver.set(0, 0, 5);
        assertTrue(solver.isValid(0, 0), "Value at (0,0) should be valid");
    }

    @Test
    public void testIsAllValid() {
        SudokuSolver solver = new SudokuSolverImpl();
        solver.set(0, 0, 5);
        assertTrue(solver.isAllValid(), "All values should be valid");
    }

    @Test
    public void testSetGrid() {
        SudokuSolver solver = new SudokuSolverImpl();
        int[][] grid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        solver.setGrid(grid);
        assertArrayEquals(grid, solver.getGrid(), "Grid should be set correctly");
    }
}