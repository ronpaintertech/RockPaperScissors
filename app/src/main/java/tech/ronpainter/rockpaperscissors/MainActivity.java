package tech.ronpainter.rockpaperscissors;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements GameControllerCallback {
    private static final String TAG = "MainActivity";

    private TextView txtMessage;
    private TextView txtOpponentMessage;

    public Player player1 = new Player(false);
    public Player player2 = new Player(true);

    GameController gameController;

    Button btnRock;
    Button btnPaper;
    Button btnScissors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameController = new GameController(MainActivity.this, MainActivity.this);
        gameController.setPlayer1(player1);
        gameController.setPlayer2(player2);


        btnRock = findViewById(R.id.btnRock);
        btnPaper = findViewById(R.id.btnPaper);
        btnScissors = findViewById(R.id.btnScissors);
        disablePieces();
        Button btnStart = findViewById(R.id.btnStart);
        this.txtMessage = findViewById(R.id.txtMessage);
        this.txtOpponentMessage = findViewById(R.id.txtOpponentMessage);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameController.stopCountdown();
                disablePieces();
                Button btnPicked = (Button) v;
                String btnPickedText = btnPicked.getText().toString();

                if (btnPickedText.equals(getString(R.string.rock))) {
                    gameController.setPlayerChoice(gameController.getPlayer1(), PieceType.ROCK);
                } else if (btnPickedText.equals(getString(R.string.paper))) {
                    gameController.setPlayerChoice(gameController.getPlayer1(), PieceType.PAPER);
                } else if (btnPickedText.equals(getString(R.string.scissors))){
                    gameController.setPlayerChoice(gameController.getPlayer1(), PieceType.SCISSORS);
                }
                gameController.getPlayer2().setRandomChoice();
                txtMessage.setText("You: "+ gameController.getOutComeMsg(1));
                txtOpponentMessage.setText("Opponent: " + gameController.getOutComeMsg(2) + " (" + gameController.getPlayer2().getChoice().getPieceType() + ")");
           }
        };

        btnRock.setOnClickListener(listener);
        btnPaper.setOnClickListener(listener);
        btnScissors.setOnClickListener(listener);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }

    @Override
    public void disablePieces() {
        btnRock.setEnabled(false);
        btnPaper.setEnabled(false);
        btnScissors.setEnabled(false);
    }

    @Override
    public void enablePieces() {
        btnRock.setEnabled(true);
        btnPaper.setEnabled(true);
        btnScissors.setEnabled(true);
    }
}
