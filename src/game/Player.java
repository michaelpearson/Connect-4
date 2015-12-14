package game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Player implements IPlayer {
    int a;
    @Nullable @Override public int takeTurn(@NotNull IBoard board) {
        //The best AI ever.
        return a++;
    }
}
