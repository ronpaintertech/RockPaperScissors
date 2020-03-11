package tech.ronpainter.rockpaperscissors;

public class Scissors extends GamePiece {

    public Scissors() {
        super(PieceType.SCISSORS);
    }

    public Outcome calcOutcome(GamePiece opponent) {
        if (opponent == null) {
            return Outcome.WIN;
        }
        switch (opponent.PIECE_TYPE) {
            case ROCK:
                return Outcome.LOSS;
            case PAPER:
                return Outcome.WIN;
            case SCISSORS:
                return Outcome.TIE;
            default:
                throw new IllegalArgumentException("Scissors calcOutcome: Opponents piece type is unknown: " + opponent.PIECE_TYPE);
        }
    }
}
