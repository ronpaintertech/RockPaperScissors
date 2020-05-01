package tech.ronpainter.rockpaperscissors;

/**
 * Interface for when using the Pieces
 */
public interface PiecesFragmentCallbacks {
    void onChoicePicked(PieceType pieceType);
    void piecesEnabled(boolean enable);
}
