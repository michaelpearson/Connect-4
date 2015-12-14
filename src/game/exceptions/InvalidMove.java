package game.exceptions;

/**
 * Created by mpearson on 14/12/2015.
 */
public class InvalidMove extends RuntimeException {
    public InvalidMove() {
    }

    public InvalidMove(String message) {
        super(message);
    }

    public InvalidMove(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMove(Throwable cause) {
        super(cause);
    }

    public InvalidMove(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
