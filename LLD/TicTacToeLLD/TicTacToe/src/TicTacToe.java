import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("This is demonstration of Tic-Tac-Toe game");
        ArrayList<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(1, "Josh", 'X'));
        players.add(new BotPlayer(2, "Bot", 'O', 0, BotPlayingStrategyName.RANDOM));

        GameController controller = new GameController();
        Game game = controller.createGame(3, players, WinningStrategyName.ORDERONESTRATEGY);

        while(true) {
            controller.executeMove(game, players.get(0));
            if (controller.getWinner(game) == players.get(0)) {
                System.out.println(players.get(0).getName() + " Won!");
                break;
            }
            if (controller.getGameStatus(game)==GameStatus.OVER) {
                System.out.println("Its draw");
                break;
            }
            controller.executeMove(game, players.get(1));
            if (controller.getWinner(game) == players.get(1)) {
                System.out.println(players.get(1).getName() + " Won!");
                break;
            }
            if (controller.getGameStatus(game)==GameStatus.OVER) {
                System.out.println("Its draw");
                break;
            }
            controller.displayBoard(game);
        }
        controller.displayBoard(game);
        System.out.println("Enter number of moves to undo");
        int moves = sc.nextInt();
        controller.undoMove(game, moves);
        System.out.println("Current board..");
        controller.displayBoard(game);
        while(true) {
            controller.executeMove(game, players.get(0));
            if (controller.getWinner(game) == players.get(0)) {
                System.out.println(players.get(0).getName() + " Won!");
                break;
            }
            if (controller.getGameStatus(game)==GameStatus.OVER) {
                System.out.println("Its draw");
                break;
            }
            controller.executeMove(game, players.get(1));
            if (controller.getWinner(game) == players.get(1)) {
                System.out.println(players.get(1).getName() + " Won!");
                break;
            }
            if (controller.getGameStatus(game)==GameStatus.OVER) {
                System.out.println("Its draw");
                break;
            }
            controller.displayBoard(game);
        }
        controller.displayBoard(game);

        System.out.println("Replaying game");
        controller.replayGame(game);
        controller.displayBoard(game);
        while(true) {
            controller.executeMove(game, players.get(0));
            if (controller.getWinner(game) == players.get(0)) {
                System.out.println(players.get(0).getName() + " Won!");
                break;
            }
            if (controller.getGameStatus(game)==GameStatus.OVER) {
                System.out.println("Its draw");
                break;
            }
            controller.executeMove(game, players.get(1));
            if (controller.getWinner(game) == players.get(1)) {
                System.out.println(players.get(1).getName() + " Won!");
                break;
            }
            if (controller.getGameStatus(game)==GameStatus.OVER) {
                System.out.println("Its draw");
                break;
            }
            controller.displayBoard(game);
        }
        controller.displayBoard(game);
    }
}