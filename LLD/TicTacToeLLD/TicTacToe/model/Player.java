abstract class Player {
    private int id;
    private String name;
    private char symbol;

    Player(int id, String name, char symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public abstract void makeMove(Game game);
    public abstract PlayerType getPlayerType();
    public char getSymbol() {
        return symbol;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
