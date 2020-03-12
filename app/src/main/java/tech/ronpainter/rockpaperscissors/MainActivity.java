package tech.ronpainter.rockpaperscissors;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements GameControllerCallbacks,
            PiecesButtonFragment.OnPieceClickCallback {
    private static final String TAG = "MainActivity";

    private TextView txtMessage;
    private TextView txtOpponentMessage;

    public Player player1 = new Player(false);
    public Player player2 = new Player(true);

    GameController gameController;

    PiecesButtonFragment fragment;

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

        fragment = new PiecesButtonFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.input_container, fragment).commit();

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChoicePicked(PieceType pieceType) {
        gameController.stopCountdown();
        piecesEnabled(false);

        gameController.setPlayerChoice(gameController.getPlayer1(), pieceType);

        gameController.getPlayer2().setRandomChoice();
        txtMessage.setText("You: "+ gameController.getOutComeMsg(1));
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

    @Override
    public void tooLate() {
        gameController.getPlayer2().setRandomChoice();
        txtMessage.setText("You were too late: "+ gameController.getOutComeMsg(1));
        txtOpponentMessage.setText("Opponent: " + gameController.getOutComeMsg(2) + " (" + gameController.getPlayer2().getChoice().getPieceType() + ")");
        btnStart.setEnabled(true);
    }

    @Override
    public void piecesEnabled(boolean enable) {
        fragment.piecesEnabled(enable);
    }

}
