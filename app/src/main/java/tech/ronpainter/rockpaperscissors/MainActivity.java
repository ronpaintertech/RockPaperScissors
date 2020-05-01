package tech.ronpainter.rockpaperscissors;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Main Activity for Rock, Paper, Scissors
 *
 * Two players, currently just playing against the computer/phone
 *
 * Note: You will see some code related to a future enhancement where I want to use the phone's
 * camera to detect if your hand making the rock paper scissors instead of using the buttons.
 */
public class MainActivity extends AppCompatActivity implements GameControllerCallbacks,
            PiecesButtonFragment.OnPieceClickCallback,
            PiecesCameraAIFragment.OnPieceDetectedCallback {
    private static final String TAG = "MainActivity";

    public enum PiecesInputType { BUTTONS, CAMERA_AI }

    private TextView txtMessage;
    private TextView txtOpponentMessage;

    public Player player1 = new Player(false);
    public Player player2 = new Player(true);

    GameController gameController;

    PiecesInputType mPiecesInputType;
    PiecesButtonFragment mButtonFragment;
    PiecesCameraAIFragment mCameraAIFragment;

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameController = new GameController(MainActivity.this, MainActivity.this);
        gameController.setPlayer1(player1);
        gameController.setPlayer2(player2);

        mButtonFragment = new PiecesButtonFragment();
        mCameraAIFragment = new PiecesCameraAIFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.input_container, mButtonFragment).commit();

        btnStart = findViewById(R.id.btnStart);
        this.txtMessage = findViewById(R.id.txtMessage);
        this.txtOpponentMessage = findViewById(R.id.txtOpponentMessage);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setEnabled(false);
                gameController.startNewRound();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        View inputFragment = findViewById(R.id.input_container);

        switch (id) {
            case R.id.mnuButtons:
                Log.d(TAG, "onOptionsItemSelected: Switch to Buttons fragment");
                getSupportFragmentManager().beginTransaction().replace(R.id.input_container, mButtonFragment).commit();
                return true;
            case R.id.mnuCameraAI:
                Log.d(TAG, "onOptionsItemSelected: Switch to Camera AI fragment");
                getSupportFragmentManager().beginTransaction().replace(R.id.input_container, mCameraAIFragment).commit();
                inputFragment.setVisibility(View.VISIBLE);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Handler for when Player 1 chooses Rock, Paper, Scissors
     * Player 2 is then randomly picked
     * Display to screen the choices and the outcome
     * @param pieceType
     */
    @Override
    public void onChoicePicked(PieceType pieceType) {
        gameController.stopCountdown();
        piecesEnabled(false);

        gameController.setPlayerChoice(gameController.getPlayer1(), pieceType);

        gameController.getPlayer2().setRandomChoice();
        txtMessage.setText("You: "+ gameController.getOutComeMsg(1) + " (" + gameController.getPlayer1().getChoice().getPieceType() + ")");
        txtOpponentMessage.setText("Opponent: " + gameController.getOutComeMsg(2) + " (" + gameController.getPlayer2().getChoice().getPieceType() + ")");
        btnStart.setEnabled(true);
    }

    @Override
    public void updateMessage(String msg) {
        this.txtMessage.setText(msg);
    }

    @Override
    public void updateOpponentMessage(String omsg) {
        this.txtOpponentMessage.setText(omsg);
    }

    /**
     * Method called if Player doesn't make a choice in the time after "Shoot"
     */
    @Override
    public void tooLate() {
        gameController.getPlayer2().setRandomChoice();
        txtMessage.setText("You were too late: "+ gameController.getOutComeMsg(1));
        txtOpponentMessage.setText("Opponent: " + gameController.getOutComeMsg(2) + " (" + gameController.getPlayer2().getChoice().getPieceType() + ")");
        btnStart.setEnabled(true);
    }

    @Override
    public void piecesEnabled(boolean enable) {
        mButtonFragment.piecesEnabled(enable);
    }

}
