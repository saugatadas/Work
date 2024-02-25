public class Cell {
    private CellState cellstate;
    private char symbol;

    Cell() {
        cellstate = CellState.EMPTY;
        symbol = '.';
    }

    Cell(Cell that) {
        cellstate = that.cellstate;
        symbol = that.symbol;
    }

    void displayCell() {
        System.out.print(symbol);
    }

    char getSymbol() {
        return symbol;
    }

    CellState getState() {
        return cellstate;
    }

    void setSymbol(char symbol) {
        // TODO throw exception if already filled
        this.symbol = symbol;
        cellstate = CellState.FILLED;
    }
}