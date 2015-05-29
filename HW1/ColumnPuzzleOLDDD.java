package HW1;
/**
 * 
 * @author Matthew Gruber <mgruber1>
 * @section A
 *
 */

// You may not import any additional classes or packages.
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Random;

public class ColumnPuzzleOLDDD implements MouseListener {

	// Do not add, change, or delete any global variables or you will receive
	// a zero on this assignment.
	public JFrame window;
	public int numMoves;
	public Color[][] grid;
	public Color[] colorsList;
	private final int NUM_OF_COLUMNS = 6;

	// Do not change this method except to change the number of rows for
	// testing. It doesn't matter which number you leave in the main method
	// when you submit.
	public static void main(String[] args) {
		new ColumnPuzzleOLDDD(3);
	}

	// Do not change this method.
	public ColumnPuzzleOLDDD(int numRows) {
		numMoves = 0;
		setup(numRows);
		shuffle();
		createGUI();
	}

	/**
	 * The setup method initializes the Column Puzzle.
	 * 
	 * 1. Initialize the grid array with the specified rows and 6 columns. 2.
	 * Initialize each column with the color corresponding to the same index in
	 * colorsList. 3. Set the bottom-right cell to BLACK.
	 */
	public void setup(int numRows) {
		// Use only these colors.
		colorsList = new Color[] { Color.RED, Color.YELLOW, Color.GREEN,
				Color.BLUE, Color.CYAN, Color.MAGENTA };

		grid = new Color[numRows][NUM_OF_COLUMNS];

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++)
				grid[i][j] = colorsList[j];
		}
		grid[numRows - 1][NUM_OF_COLUMNS - 1] = Color.BLACK;

	}

	/**
	 * Returns the number of rows in the ColumnPuzzle.
	 */
	public int getNumRows() {
		return grid.length;
	}

	/**
	 * Returns the number of columns in the ColumnPuzzle.
	 */
	public int getNumCols() {
		return grid[0].length;
	}

	/**
	 * Returns the current grid.
	 */
	public Color[][] getGrid() {
		return grid;
	}

	/**
	 * Returns the number of moves.
	 */
	public int getNumMoves() {
		return numMoves;
	}

	/**
	 * Swaps the colors at the given points.
	 */
	public void swap(int row1, int col1, int row2, int col2) {

		Color tempColor = grid[row1][col1];
		grid[row1][col1] = grid[row2][col2];
		grid[row2][col2] = tempColor;
	}

	/**
	 * Complete the shuffle method. You must use the following algorithm:
	 * 
	 * 1. Create a new Random object. 2. Pick a random color location in the
	 * grid array (using a random row and random column) 3. Swap the color with
	 * another color at a random location using the swap method you wrote 4.
	 * Repeat this process until you have performed 100 color swaps.
	 */
	public void shuffle() {
		Random randNum = new Random();
		int row1, row2, col1, col2, numRows;

		numRows = getNumRows();

		for (int i = 0; i < 100; i++) {
			row1 = randNum.nextInt(numRows);
			col1 = randNum.nextInt(NUM_OF_COLUMNS);
			row2 = randNum.nextInt(numRows);
			col2 = randNum.nextInt(NUM_OF_COLUMNS);
			swap(row1, col1, row2, col2);
		}
	}

	/**
	 * Sets the title on the grid Window. You should look at the API for JFrame
	 * to learn how to update the title.
	 */
	public void setTitle(String title) {

		window.setTitle(title);

	}

	/**
	 * Gets the title on the Grid Window
	 */
	public String getTitle() {
		return window.getTitle();
	}

	/**
	 * Returns true if (and only if) the puzzle has been solved. A puzzle is
	 * "solved" when each column consists of exactly one color, or one color and
	 * the black square.
	 */
	public boolean isSolved() {
		int numRows = getNumRows();
		Color currentColor;

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				currentColor = grid[0][j];
				if (grid[i][j] != currentColor && grid[i][j] != Color.BLACK) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the index of the adjacent black square (horizontally or
	 * vertically). It returns a int[] of (-1,-1) if no adjacent black square is
	 * found.
	 */
	public int[] adjacentBlackSquare(int row, int col) {

		int[] blackSquare = { -1, -1 };

		if (row - 1 >= 0 && grid[row - 1][col] == Color.BLACK) {
			blackSquare[0] = row - 1;
			blackSquare[1] = col;
		} else if (row + 1 < getNumRows() && grid[row + 1][col] == Color.BLACK) {
			blackSquare[0] = row + 1;
			blackSquare[1] = col;
		} else if (col - 1 >= 0 && grid[row][col - 1] == Color.BLACK) {
			blackSquare[0] = row;
			blackSquare[1] = col - 1;
		} else if (col + 1 < getNumCols() && grid[row][col + 1] == Color.BLACK) {
			blackSquare[0] = row;
			blackSquare[1] = col + 1;
		}
		return blackSquare;

	}

	/**
	 * Implement this mouseClicked method. Use the following algorithm:
	 * 
	 * 1. Find an adjacent black square to the point. 2. If there is no black
	 * square, update the title to say "Illegal move" and return. 3. If there is
	 * a black square, swap the black square and the clicked square (which
	 * shifts the clicked square into the blank space). 4. Update the number of
	 * moves. 5. Update the title. If this move solved the puzzle, update the
	 * title to say "You won!". Otherwise, update the title to say "X moves",
	 * where X is the number of moves so far.
	 */
	public void mousePressed(MouseEvent e) {
		// Stops any moves from being made once the puzzle is solved.
		if (isSolved())
			return;

		// Gets the row and column of the clicked square
		int row = e.getY() / 100;
		int col = e.getX() / 100;

		int[] blackSquare = adjacentBlackSquare(row, col);
		if (blackSquare[0] == -1) {
			setTitle("Illegal move");
		} else {
			swap(blackSquare[0], blackSquare[1], row, col);
			numMoves++;
			setTitle(numMoves + " moves");
		}

		if (isSolved())
			setTitle("You won!");

	}

	/*
	 * Do not change anything below this line. If you do, you will receive a 0.
	 * 
	 * You are, however, encouraged to read it. It's OK if you don't understand
	 * every line, but you can probably understand what it's doing in general.
	 */

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		paint();
	}

	public void mouseClicked(MouseEvent e) {
	}

	private void createGUI() {
		if (grid == null || grid.length == 0) {
			System.out.println("You did not initialize the grid! You must "
					+ "initialize the grid to run the puzzle!");
			System.exit(0);
		}

		// Create the window.
		JFrame.setDefaultLookAndFeelDecorated(true);
		window = new JFrame("Column Puzzle");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		// Set up the content pane.
		Container cp = window.getContentPane();
		cp.setLayout(new GridLayout(grid.length, NUM_OF_COLUMNS));
		cp.addMouseListener(this);

		// Add panels.
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				JPanel cell = new JPanel();
				cell.setPreferredSize(new Dimension(100, 100));
				cell.setBackground(grid[row][col]);
				cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				cp.add(cell);
			}
		}

		paint();
	}

	public void dispose() {
		if (window != null) {
			window.setVisible(false);
			window.dispose();
		}
	}

	public void paint() {
		Container cp = window.getContentPane();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				Color curr = grid[row][col];
				cp.getComponent(getCellId(row, col)).setBackground(curr);
			}
		}
		window.pack();
		window.setVisible(true);
	}

	private int getCellId(int row, int col) {
		return row * NUM_OF_COLUMNS + col;
	}
}
