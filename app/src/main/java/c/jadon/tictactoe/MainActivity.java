package c.jadon.tictactoe;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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

// App on the Play Store: https://play.google.com/store/apps/details?id=c.jadon.tictactoe
@TargetApi(16)
public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
