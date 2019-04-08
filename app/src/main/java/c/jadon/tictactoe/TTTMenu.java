package c.jadon.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class TTTMenu extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tttmenu);
        Button onevsone = (Button) findViewById(R.id.playManualOpt);
        Button againstAI = (Button) findViewById(R.id.playAIOpt);

        onevsone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TicTacToe.class).putExtra("activateAI", false));
            }
        });

        againstAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TicTacToe.class).putExtra("activateAI", true));
            }
        });
    }
}
