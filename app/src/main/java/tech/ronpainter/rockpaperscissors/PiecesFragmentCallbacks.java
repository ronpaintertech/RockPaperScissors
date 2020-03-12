package tech.ronpainter.rockpaperscissors;

public interface PiecesFragmentCallbacks {
    void onChoicePicked(PieceType pieceType);
    void piecesEnabled(boolean enable);
}
