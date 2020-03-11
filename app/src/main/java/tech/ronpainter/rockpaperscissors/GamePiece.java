package tech.ronpainter.rockpaperscissors;

public abstract class GamePiece {

    PieceType PIECE_TYPE;

    public GamePiece(PieceType pieceType) {
        PIECE_TYPE = pieceType;
    }

    private GamePiece() {
    }

    public abstract Outcome calcOutcome(GamePiece opponent);

    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
