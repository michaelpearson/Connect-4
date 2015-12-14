package game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IPlayer {
    @Nullable
    int takeTurn(@NotNull IBoard board);
}
