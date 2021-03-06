package tech.ronpainter.rockpaperscissors;

import java.util.Random;

/**
 * Enums for the piece type and to return a random one
 */
public enum PieceType {
    ROCK,
    PAPER,
    SCISSORS;

    public static PieceType getRandomPieceType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
