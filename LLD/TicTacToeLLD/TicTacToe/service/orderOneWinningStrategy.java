import java.util.ArrayList;
import java.util.HashMap;

public class orderOneWinningStrategy implements WinningStrategy {
    private ArrayList<HashMap<Character, Integer>> rowHash;
    private ArrayList<HashMap<Character, Integer>> colHash;
    private HashMap<Character, Integer> diagonalHash;
    private HashMap<Character, Integer> antiDiagonalHash;

    orderOneWinningStrategy(int dimension) {
        rowHash = new ArrayList<>();
        colHash = new ArrayList<>();
        for (int i=0; i<dimension; i++) {
            rowHash.add(new HashMap<>());
            colHash.add(new HashMap<>());
        }
        diagonalHash = new HashMap<>();
        antiDiagonalHash = new HashMap<>();
    }

    @Override
    public boolean playerWinner(Board board, Move lastMove) {
        char symbol = lastMove.getSymbol();
        int row = lastMove.getRow();
        int col = lastMove.getCol();

        HashMap<Character, Integer> rowMap = rowHash.get(row);
        rowMap.put(symbol, rowMap.getOrDefault(symbol, 0)+1);
        rowHash.set(row, rowMap);

        HashMap<Character, Integer> colMap = colHash.get(col);
        colMap.put(symbol, colMap.getOrDefault(symbol, 0)+1);
        colHash.set(col, colMap);

        if (row==col) {
            diagonalHash.put(symbol, diagonalHash.getOrDefault(symbol, 0) + 1);
        }
        if ((row+col)==(board.getDimension()-1)) {
            antiDiagonalHash.put(symbol, antiDiagonalHash.getOrDefault(symbol, 0) + 1);
        }

        int rowCount = rowHash.get(row).getOrDefault(symbol, 0);
        int colCount = colHash.get(col).getOrDefault(symbol, 0);
        int diagCount = diagonalHash.getOrDefault(symbol, 0);
        int antiDiagCount = antiDiagonalHash.getOrDefault(symbol, 0);

        if (rowCount == board.getDimension() ||
            colCount == board.getDimension() ||
            diagCount == board.getDimension() ||
            antiDiagCount == board.getDimension())
            return true;

        return false;
    }

    @Override
    public void resetBoard(Board board) {
        int dimension = board.getDimension();
        rowHash = new ArrayList<>();
        colHash = new ArrayList<>();
        for (int i=0; i<dimension; i++) {
            rowHash.add(new HashMap<>());
            colHash.add(new HashMap<>());
        }
        diagonalHash = new HashMap<>();
        antiDiagonalHash = new HashMap<>();

        for (int i=0; i<board.getDimension(); i++) {
            for (int j=0; j<board.getDimension(); j++) {
                char symbol = board.getSymbol(i, j);
                HashMap<Character, Integer> rowMap = rowHash.get(i);
                rowMap.put(symbol, rowMap.getOrDefault(symbol, 0)+1);
                rowHash.set(i, rowMap);

                HashMap<Character, Integer> colMap = colHash.get(j);
                colMap.put(symbol, colMap.getOrDefault(symbol, 0)+1);
                colHash.set(j, colMap);

                if (i==j) {
                    diagonalHash.put(symbol, diagonalHash.getOrDefault(symbol, 0) + 1);
                }
                if ((i+j) == (board.getDimension()-1)) {
                    antiDiagonalHash.put(symbol, antiDiagonalHash.getOrDefault(symbol, 0) + 1);
                }
            }
        }
    }
}
