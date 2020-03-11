package tech.ronpainter.rockpaperscissors;

public interface GameControllerCallback {
    public void updateMessage(String msg);
    public void updateOpponentMessage(String omsg);
    public void tooLate();
    public void disablePieces();
    public void enablePieces();
}
