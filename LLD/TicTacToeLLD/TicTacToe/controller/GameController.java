import java.util.ArrayList;

public class GameController {

    Game createGame(int dimension, ArrayList<Player> players, WinningStrategyName strategyName) {
        return Game.builder().
                setDimension(dimension).
                setPlayers(players).
                setWinningStrategy(strategyName).
                build();
    }

    void displayBoard(Game game) {
        game.displayBoard();
    }

    GameStatus getGameStatus(Game game) {
        return game.getCurrentStatus();
    }

    void executeMove(Game game, Player player) {
        player.makeMove(game);
    }

    Player getWinner(Game game) {
        return game.getWinner();
    }

    void undoMove(Game game, int numOfMove) {
        game.undo(numOfMove);
    }

    void replayGame(Game game) {
        game.replayGame();
    }

}