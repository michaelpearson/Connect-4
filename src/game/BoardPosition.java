package game;

import org.jetbrains.annotations.NotNull;

public class BoardPosition {
    @NotNull private int x;
    @NotNull private int y;
    public BoardPosition(@NotNull int x, @NotNull int y) {
        this.x = x;
        this.y = y;
    }

    @NotNull public int getX() {
        return x;
    }

    @NotNull public int getY() {
        return y;
    }

    @NotNull public static BoardPosition getPosition(@NotNull BoardPosition position, int x, int y) {
        position.x = x;
        position.y = y;
        return(position);
    }
}
