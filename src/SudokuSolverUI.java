import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverUI extends JFrame {
    private SudokuSolver solver;
    private JTextField[][] cells = new JTextField[9][9];

    public SudokuSolverUI(SudokuSolver solver) {
        this.solver = solver;
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                if ((i / 3 + j / 3) % 2 == 0) {
                    cells[i][j].setBackground(Color.LIGHT_GRAY);
                }
                gridPanel.add(cells[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolveButtonListener());
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class SolveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        String text = cells[i][j].getText();
                        int value = text.isEmpty() ? 0 : Integer.parseInt(text);
                        solver.set(i, j, value);
                    }
                }
                if (solver.solve()) {
                    int[][] solution = solver.getGrid();
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            cells[i][j].setText(solution[i][j] == 0 ? "" : String.valueOf(solution[i][j]));
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(SudokuSolverUI.this, "No solution found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(SudokuSolverUI.this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            solver.clearAll();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    cells[i][j].setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolverImpl();
        new SudokuSolverUI(solver);
    }
}