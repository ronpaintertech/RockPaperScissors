package tech.ronpainter.rockpaperscissors;

/**
 * Rock game piece and outcome vs other game pieces
 */
class Rock extends GamePiece {

    public Rock() {
        super(PieceType.ROCK);
    }

    public Outcome calcOutcome(GamePiece opponent) {
        // if opponent is null, probably they were too late
        if(opponent == null) {
            return Outcome.WIN;
        }
        switch (opponent.PIECE_TYPE) {
            case ROCK:
                return Outcome.TIE;
            case PAPER:
                return Outcome.LOSS;
            case SCISSORS:
                return Outcome.WIN;
            default:
                throw new IllegalArgumentException("Rock calcOutcome: Opponents piece type is unknown: " + opponent.PIECE_TYPE);
        }
    }
}
