package c.jadon.tictactoe;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.widget.TextView.*;

@TargetApi(16)
public class MainActivity extends AppCompatActivity {
    // To be implemented: pop up window displaying the winner
    // For now, use the blinking textview of the winning piece at the top of the screen

    public final int[] players = {R.id.X, R.id.Y};
    public String currentPlayer = "X";
    protected String[][] board = {{"","",""},{"","",""},{"","",""}};
    private String winner;
    private int turnCount = 1;
    TextView info;
    private final int[] grid = {R.id.Position00, R.id.Position01, R.id.Position02, R.id.Position10, R.id.Position11,
    R.id.Position12, R.id.Position20, R.id.Position21, R.id.Position22};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = (TextView) findViewById(R.id.info);
        play();

        Button restart = (Button) findViewById(R.id.Restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    protected void play() {
            View.OnClickListener makeMove = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button) v;
                    b.setEnabled(false);

                    int rowNum = -1;
                    int columnNum = -1;
                    switch (b.getId()) {
                        case R.id.Position00:
                            rowNum = 0;
                            columnNum = 0;
                            break;
                        case R.id.Position01:
                            rowNum = 0;
                            columnNum = 1;
                            break;
                        case R.id.Position02:
                            rowNum = 0;
                            columnNum = 2;
                            break;
                        case R.id.Position10:
                            rowNum = 1;
                            columnNum = 0;
                            break;
                        case R.id.Position11:
                            rowNum = 1;
                            columnNum = 1;
                            break;
                        case R.id.Position12:
                            rowNum = 1;
                            columnNum = 2;
                            break;
                        case R.id.Position20:
                            rowNum = 2;
                            columnNum = 0;
                            break;
                        case R.id.Position21:
                            rowNum = 2;
                            columnNum = 1;
                            break;
                        case R.id.Position22:
                            rowNum = 2;
                            columnNum = 2;
                            break;
                    }
                    b.setText(currentPlayer);
                    b.setTypeface(b.getTypeface(), Typeface.BOLD);
                    b.setTextSize(45);
                    b.setTextColor(getResources().getColor(R.color.black));

                    if (rowNum >= 0) {
                        board[rowNum][columnNum] = currentPlayer;
                    }

                    nextTurn();
                    ++turnCount;
                    if (ThreeinaRow(board) || turnCount == 10) {
                        endGame();
                    }
                }
            };

            for (int tiles : grid) {
                findViewById(tiles).setOnClickListener(makeMove);
            }
    }

    protected void nextTurn() {
        info.setText("Turn ".concat(Integer.toString(turnCount)));
        if (currentPlayer.equals("O")) {
            currentPlayer = "X";
        } else {
            currentPlayer = "O";
        }
    }

    protected Boolean ThreeinaRow(final String[][] gameBoard) {
        String firstPiece;

        for (String[] row: gameBoard) {
            int m = 0;
            firstPiece = row[0];
            for (String piece : row) {
                if (piece.equals(firstPiece)) {
                    ++m;
                }
            }
            if (m == 3 && !firstPiece.isEmpty()) {
                winner = firstPiece;
                return true;
            }
        }

        int columnNum = 0;
        while (columnNum < 3) {
            String rowStartingPiece = gameBoard[0][columnNum];
            int n = 0;
            for (String[] row: gameBoard) {
                if (row[columnNum].equals(rowStartingPiece)) {
                    ++n;
                }
            }
            if (n == 3 && !rowStartingPiece.isEmpty()) {
                winner = rowStartingPiece;
                return true;
            }

            ++columnNum;
        }

        columnNum = 0;
        int p = 0;
        firstPiece = gameBoard[0][0];
        int[] rowIndices = {0,1,2};
        for (int rowNum : rowIndices) {
            if (gameBoard[rowNum][columnNum].equals(firstPiece)) {
                ++p;
            }
            ++columnNum;
            if (p == 3 && !firstPiece.isEmpty()) {
                winner = firstPiece;
                return true;
            }
        }

        columnNum = 2;
        int q = 0;
        firstPiece = gameBoard[0][2];
        for (int rowNum : rowIndices) {
            if (gameBoard[rowNum][columnNum].equals(firstPiece)) {
                ++q;
            }
            --columnNum;
            if (q == 3 && !firstPiece.isEmpty()) {
                winner = firstPiece;
                return true;
            }
        }

        winner = "Tie";
        return false;
    }

    private void endGame() {
        if (winner.equals("Tie")) {
            info.setText("TIE");
            findViewById(players[0]).setBackgroundColor(getResources().getColor(R.color.black));
            findViewById(players[1]).setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            info.setText("Winner ");
            int i = winner.equals("X") ? 0 : 1;
            findViewById(players[i]).setBackgroundColor(getResources().getColor(R.color.purple));
            findViewById(players[i ^ 1]).setBackgroundColor(getResources().getColor(R.color.black));

            Animation blinkingWinner = new AlphaAnimation(0.0f, 1.0f);
            blinkingWinner.setDuration(800);
            blinkingWinner.setRepeatMode(Animation.REVERSE);
            blinkingWinner.setRepeatCount(Animation.INFINITE);
            findViewById(players[i]).startAnimation(blinkingWinner);
        }

        for (int tile : grid) {
            findViewById(tile).setEnabled(false);
        }
    }
}
