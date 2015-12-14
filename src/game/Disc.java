package game;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Disc {
    @NotNull
    private static Map<PlayerColour, Integer> colorsRemaining = new HashMap<>();
    @NotNull private PlayerColour player;

    enum PlayerColour {
        RED (Color.RED),
        YELLOW (Color.YELLOW);

        @NotNull private final Color playerColor;

        @NotNull public Color getColor() {
            return playerColor;
        }

        PlayerColour(Color playerColor) {
            this.playerColor = playerColor;
        }

    }

    static {
        colorsRemaining.put(PlayerColour.RED, 100);
        colorsRemaining.put(PlayerColour.YELLOW, 100);
    }


    @Nullable
    public Disc getDisk(PlayerColour player) {
        if(colorsRemaining.get(player) == 0) {
            return(null);
        }
        colorsRemaining.put(player, colorsRemaining.get(player) - 1);
        return(new Disc(player));
    }

    public Disc(PlayerColour player) {
        if(player == null) {
            throw new IllegalArgumentException();
        }
        this.player = player;
    }

    public PlayerColour getPlayerColor() {
        return(player);
    }
}
