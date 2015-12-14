package game;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IBoard {
    @Nullable Disc getPosition(@NotNull BoardPosition position);
}
