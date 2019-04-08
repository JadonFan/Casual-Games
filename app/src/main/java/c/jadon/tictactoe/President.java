package c.jadon.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;

public class President extends CardGames implements OnTouchListener{
    private PlayingTable table;
    private float x, y;
    private float offShiftX = -2;
    private Bitmap selectedCard;
    private int cardWidth, cardHeight;
    private boolean gameDone = false;
    private boolean playTurn = false;
    private CardGames[] playerHands = new CardGames[4];
    MotionEvent globalEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        table = new PlayingTable(this);
        setContentView(table);
        table.setOnTouchListener(this);
        playGame();
    }

    public CardGames[] showHands() {
        return playerHands;
    }

    protected void playGame() {
        while (!hasWinner()) {

        }
    }

    protected boolean hasWinner() {
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        table.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        table.resume();
    }

    /*
    public Bitmap findCardImg(int cardValue) {
        // The border_button file is currently a placeholder
        Bitmap cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.border_button);
        switch (cardValue) {
            case 1:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.ACE);
                break;
            case 2:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.TWO);
                break;
            case 3:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.THREE);
                break;
            case 4:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.FOUR);
                break;
            case 5:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.FIVE);
                break;
            case 6:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.SIX);
                break;
            case 7:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.SEVEN);
                break;
            case 8:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.EIGHT);
                break;
            case 9:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.NINE);
                break;
            case 10:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.TEN);
                break;
            case 11:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.ELEVEN);
                break;
            case 12:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.TWELVE);
                break;
            case 13:
                cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.THIRTEEN);
                break;
            default:
                // cardImg = BitmapFactory.decodeResource(getResources(), R.drawable.ERRORCARD);
        }
        cardWidth = cardImg.getWidth();
        cardHeight = cardImg.getHeight();
        return cardImg;
    }
    */

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        globalEvent = event;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            x = event.getX();
            y = event.getY();
        }
        return true;
    }

    public class PlayingTable extends SurfaceView implements Runnable {
        Thread moveCards = null;
        SurfaceHolder holder;
        boolean contShowCards = true;

        public PlayingTable(Context context) {
            super(context);
            holder = getHolder();
        }

        @Override
        public boolean performClick () {
            super.performClick();
            return true;
        }

        @Override
        public void run() {
            if (!holder.getSurface().isValid()) {
                return;
            }
            Canvas canvas = holder.lockCanvas();
            canvas.drawARGB(255, 100, 100, 100);
            x = canvas.getWidth()/2 + offShiftX;
            ++offShiftX;
            y = 0;

            while (playTurn) {
                canvas.drawBitmap(selectedCard, x, y, null);

                // If a card is dragged to approximately the centre of the canvas, assume that
                // that card wants to be played by the player
                if (x >= canvas.getWidth()/4 && x <= (3 * canvas.getWidth())/4 && y >= canvas.getHeight()/4
                    && y <= (3 * canvas.getHeight())/4) {
                    canvas.drawBitmap(selectedCard, canvas.getWidth()/2 - cardWidth/2,
                            canvas.getHeight()/2 - cardHeight/2, null);
                    playTurn = false;
                    --offShiftX;
                }
            }

            holder.unlockCanvasAndPost(canvas);
        }

        public void pause() {
            contShowCards = false;
        }

        public void resume() {
            contShowCards = true;
        }
    }
}
