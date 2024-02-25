public interface WinningStrategy {
    boolean playerWinner(Board board, Move lastMove);
    void resetBoard(Board board);
}
