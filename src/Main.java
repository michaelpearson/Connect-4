import game.*;

public class Main {
    public static void main(String[] argv) {
        new GameWindow(new GameBoard(new IPlayer[] {new Player(), new Player()}));
    }
}
