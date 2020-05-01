package tech.ronpainter.rockpaperscissors;

/**
 * Paper game piece and outcome vs other game pieces
 */
public class Paper extends GamePiece {

    public Paper() {
        super(PieceType.PAPER);
    }

    public Outcome calcOutcome(GamePiece opponent) {
        if(opponent == null) {
            return Outcome.WIN;
        }
        switch (opponent.PIECE_TYPE) {
            case ROCK:
                return Outcome.WIN;
            case PAPER:
                return Outcome.TIE;
            case SCISSORS:
                return Outcome.LOSS;
            default:
                throw new IllegalArgumentException("Paper calcOutcome: Opponents piece type is unknown: " + opponent.PIECE_TYPE);
        }
    }
}
