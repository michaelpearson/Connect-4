package game;


import game.exceptions.InvalidMove;
import org.jetbrains.annotations.Nullable;

public abstract class Board implements IBoard, IBoardConfiguration {
    protected IPlayer[] players;
    protected int currentPlayerIndex = 0;
    protected boolean gameFinished;


    public Board(IPlayer[] players) {
        this.players = players;
    }

    public abstract boolean placeDisc(int position, int playerIndex);
    @Nullable public abstract Integer getWinningPlayerIndex();
    @Nullable public abstract Disc.PlayerColour getPlayerColor(int index);


    public void nextMove() {
        if(gameFinished) {
            return;
        }
        if(!placeDisc(players[currentPlayerIndex].takeTurn(this), currentPlayerIndex)) {
            throw new InvalidMove(String.format("Invalid move performed by player %d", currentPlayerIndex));
        }
        if(++currentPlayerIndex >= players.length) {
            currentPlayerIndex = 0;
        }

        Integer winningIndex;
        if((winningIndex = getWinningPlayerIndex()) != null) {
            gameFinished = true;
            System.out.println(String.format("Game finished, winning index: %d", winningIndex));
        }

    }

    public int getCurrentPlayerIndex() {
        return(currentPlayerIndex);
    }
}
