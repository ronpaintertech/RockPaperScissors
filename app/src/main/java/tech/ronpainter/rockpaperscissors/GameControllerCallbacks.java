package tech.ronpainter.rockpaperscissors;

public interface GameControllerCallbacks {
    public void updateMessage(String msg);
    public void updateOpponentMessage(String omsg);
    public void tooLate();
    public void piecesEnabled(boolean enable);
}
