package game;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GameBoard extends Board {

    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 10;
    private static final int CONNECTION_LENGTH = 4;

    private Disc board[][] = new Disc[BOARD_WIDTH][BOARD_HEIGHT];
    private Disc.PlayerColour[] colours;

    public GameBoard(IPlayer[] players) {
        super(players);
        colours = new Disc.PlayerColour[players.length];
        for(int a = 0;a < colours.length; a++) {
            colours[a] = Disc.PlayerColour.values()[a];
        }
    }

    @Override public int getBoardWidth() {
        return BOARD_WIDTH;
    }

    @Override public int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    @Override public boolean placeDisc(int position, int playerIndex) {
        if(position < 0 || position >= getBoardWidth()) {
            return(false);
        }
        int yPosition = -1;
        BoardPosition p = new BoardPosition(0, 0);
        for(int y = 0; y < getBoardHeight(); y++) {
            BoardPosition.getPosition(p, position, y);
            if(getPosition(p) != null) {
                break;
            }
            yPosition = y;
        }
        if(yPosition == -1) {
            return false;
        }
        BoardPosition.getPosition(p, position, yPosition);
        placeDisc(p, new Disc(getPlayerColor(playerIndex)));
        return(true);
    }

    private void placeDisc(BoardPosition p, Disc disc) {
        board[p.getX()][p.getY()] = disc;
    }

    /**
     * Will return the first winning player, there should never be two winning players.
     * @return The index of the winning player
     */
    @Nullable @Override public Integer getWinningPlayerIndex() {
        for(int x = 0;x < getBoardWidth(); x++) {
            for(int y = 0; y < getBoardHeight(); y++) {
                if(board[x][y] == null) {
                    continue;
                }
                Disc.PlayerColour winningColor = checkHorizontal(x, y);
                if(winningColor != null) {
                    return(getPlayerIndexFromColor(winningColor));
                }
            }
        }
        return null;
    }

    private Disc.PlayerColour checkHorizontal(int x, int y) {
        int found = 1;
        Disc.PlayerColour c = board[x][y].getPlayerColor();
        int rX = x + 1;
        while(found < CONNECTION_LENGTH) {
            if(getColorAtPosition(rX++, y) == c) {
                found++;
            } else {
                break;
            }
        }
        int lX = x - 1;
        while(found < CONNECTION_LENGTH) {
            if(getColorAtPosition(lX--, y) == c) {
                found++;
            } else {
                break;
            }
        }
        return found >= CONNECTION_LENGTH ? c : null;
    }


    private boolean checkAll(BoardPosition startingPosition, BoardPosition deltas[]) {
        Disc.PlayerColour c = getColorAtPosition(startingPosition.getX(), startingPosition.getY());
        if(c == null) {
            return(false);
        }

    }

    @Nullable @Override public Disc.PlayerColour getPlayerColor(int index) {
        if(index < 0 || index >= colours.length) {
            return(null);
        }
        return colours[index];
    }

    @Nullable private Integer getPlayerIndexFromColor(Disc.PlayerColour color) {
        for(int index = 0; index < colours.length; index++) {
            if(colours[index] == color) {
                return(index);
            }
        }
        return(null);
    }

    @Nullable public Disc getPosition(@NotNull BoardPosition position) {
        return board[position.getX()][position.getY()];
    }

    private Disc.PlayerColour getColorAtPosition(int x, int y) {
        if(x > 0 && y > 0 && x < getBoardWidth() && y < getBoardHeight() && board[x][y] != null) {
            return board[x][y].getPlayerColor();
        }
        return(null);
    }
}
