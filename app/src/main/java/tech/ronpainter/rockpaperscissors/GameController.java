package tech.ronpainter.rockpaperscissors;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Central controller for the game.
 * Stores the players, starts the countdown, calls for determining outcome, resets the round
 */
public class GameController extends ContextWrapper {

    private static final String TAG = "GameController";

    GameControllerCallbacks gcCallback = null;

    private Player player1;
    private Player player2;

    private int msgI = 0;
    private String[] countMessage = new String[6];

    private CountDownTimer countDownTimer;
    private long interval;

    private GameController(Context base) {
        super(base);
        countMessage[0] = getString(R.string.ready);
        countMessage[1] = getString(R.string.rock);
        countMessage[2] = getString(R.string.paper);
        countMessage[3] = getString(R.string.scissors);
        countMessage[4] = getString(R.string.shoot);
        countMessage[5] = getString(R.string.tooLate);
    }

    public GameController(@NonNull Context base, @NonNull GameControllerCallbacks callback) {
        this(base);
        this.gcCallback = callback;
    }

    public void startNewRound() {
        Log.d(TAG, "startNewRound: starts");
        player1.resetChoice();
        player2.resetChoice();
        gcCallback.piecesEnabled(false);
        gcCallback.updateOpponentMessage("");
        msgI = 0;

        long totalMills = 6000L;
        interval = (totalMills / countMessage.length);

        countDownTimer = new CountDownTimer(totalMills, interval) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "onTick: " + msgI + " - " + countMessage[msgI]);

                if (countMessage[msgI].equals(getString(R.string.shoot))) {
                    gcCallback.piecesEnabled(true);
                }

                gcCallback.updateMessage(countMessage[msgI]);

                if (countMessage[msgI].equals(getString(R.string.tooLate))) {
                    gcCallback.piecesEnabled(false);
                    gcCallback.tooLate();
                }

                msgI++;
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    public void stopCountdown() {
        countDownTimer.cancel();
    }

    public void setPlayerChoice(Player player, PieceType pieceType) {
        player.setChoice(pieceType);
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Outcome getOutcome(int playerNum) {
        if (playerNum == 1) {
            return player1.calcOutcome(player2);
        } else {
            return player2.calcOutcome(player1);
        }

    }

    public String getOutComeMsg(int playerNum) {
        Outcome outcome = getOutcome(playerNum);
        return Outcome.getOutcomeMsg(getBaseContext(), outcome);
    }


}
