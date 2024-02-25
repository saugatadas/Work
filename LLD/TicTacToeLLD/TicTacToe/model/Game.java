import java.util.ArrayList;

public class Game {
    ArrayList<Player> players;
    ArrayList<Board> boards;
    Board currentBoard;
    WinningStrategy winningStrategy;
    GameStatus currentStatus;
    Player winner;

    Game(ArrayList<Player> players, WinningStrategy winningStrategy, int dimension) {
        this.players = players;
        this.winningStrategy = winningStrategy;
        boards = new ArrayList<>();
        currentBoard = new Board(dimension);
        currentStatus = GameStatus.INPROGRESS;
        winner = null;
    }

    public GameStatus getCurrentStatus() {
        return currentStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public int getDimension() {
        return currentBoard.getDimension();
    }

    public void makeMove(int row, int col, Player player) {
        currentBoard.setSymbol(row, col, player.getSymbol());
        boards.add(new Board(currentBoard));
        if (winningStrategy.playerWinner(currentBoard, new Move(row, col, player.getSymbol()))) {
            currentStatus = GameStatus.OVER;
            winner = player;
        }
        if (currentBoard.getNumFilled()== currentBoard.getDimension() * currentBoard.getDimension()) {
            currentStatus = GameStatus.OVER;
        }
    }

    public boolean isFilled(int row, int col) {
        return currentBoard.isFilled(row, col);
    }

    public void undo(int count) {
        int n = boards.size() - count;
        ArrayList<Board> boardsCopy = new ArrayList<>();
        for (int i=0; i<n; i++)
            boardsCopy.add(boards.get(i));
        currentBoard = boardsCopy.get(n-1);
        boards = boardsCopy;
        winningStrategy.resetBoard(currentBoard);
        currentStatus = GameStatus.INPROGRESS;
        winner = null;
    }

    public void displayBoard() {
        currentBoard.displayBoard();
    }

    public void replayGame() {
        boards = new ArrayList<>();
        currentBoard = new Board(currentBoard.getDimension());
        winningStrategy.resetBoard(currentBoard);
        currentStatus = GameStatus.INPROGRESS;
        winner = null;
    }

    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public static class GameBuilder {
        private ArrayList<Player> players;
        private WinningStrategyName strategyName;
        private int dimension;

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setWinningStrategy(WinningStrategyName strategy) {
            this.strategyName = strategy;
            return this;
        }

        public GameBuilder setPlayers(ArrayList<Player> players) {
            this.players = players;
            return this;
        }

        public Game build() {
            WinningStrategyFactory factory = new WinningStrategyFactory();
            WinningStrategy winningStrategy = factory.getWinningStrategy(strategyName, dimension);
            return new Game(players, winningStrategy, dimension);
        }
    }

}
