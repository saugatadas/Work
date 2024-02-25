import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private int dimension;
    private Cell[][] board;
    private int numFilled;

    Board(int dimension) {
        this.dimension = dimension;

        board = new Cell[dimension][dimension];
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                board[i][j] = new Cell();
            }
        }
        numFilled = 0;
    }

    Board(Board that) {
        this.dimension = that.dimension;
        this.numFilled = that.numFilled;
        board = new Cell[dimension][dimension];
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                board[i][j] = new Cell(that.board[i][j]);
            }
        }
    }

    public void displayBoard() {
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                board[i][j].displayCell();
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void setSymbol(int row, int col, char symbol) {
        board[row][col].setSymbol(symbol);
        numFilled++;
    }

    public char getSymbol(int row, int col) {
        return board[row][col].getSymbol();
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isFilled(int row, int col) {
        if (board[row][col].getState()==CellState.FILLED)
            return true;
        else
            return false;
    }

    public int getNumFilled() {
        return numFilled;
    }
}
