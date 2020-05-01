package tech.ronpainter.rockpaperscissors;

/**
 * Interface for when using the GameController
 */
public interface GameControllerCallbacks {
    public void updateMessage(String msg);
    public void updateOpponentMessage(String omsg);
    public void tooLate();
    public void piecesEnabled(boolean enable);
}
