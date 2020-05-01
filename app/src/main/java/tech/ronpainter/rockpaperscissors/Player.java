package tech.ronpainter.rockpaperscissors;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Holds info on the player, including what piece they chose (or a random pick) and the outcome
 * of their choice.
 */
public class Player {
    private static final String TAG = "Player";
    private boolean isComputer = false;
    private GamePiece choice;

    public Player(boolean isComputer) {
        this.isComputer = isComputer;
    }

    public GamePiece getChoice() {
        return choice;
    }

    public void setChoice(@NonNull PieceType choice) {

        switch (choice) {
            case ROCK:
                this.choice = new Rock();
                break;
            case PAPER:
                this.choice = new Paper();
                break;
            case SCISSORS:
                this.choice = new Scissors();
                break;
        }
    }

    public void setRandomChoice() {
        PieceType randomPieceType = PieceType.getRandomPieceType();
        Log.d(TAG, "setChoice: randomPiece Type = " + randomPieceType);
        setChoice(randomPieceType);
        Log.d(TAG, "setChoice: ends");
    }

    public void resetChoice() {
        this.choice = null;
    }

    public Outcome calcOutcome(Player opponent) {
        // if choice is null, this player was probably Too late, so loss
        if(choice == null) {
            return Outcome.LOSS;
        }
        return choice.calcOutcome(opponent.getChoice());
    }
}
