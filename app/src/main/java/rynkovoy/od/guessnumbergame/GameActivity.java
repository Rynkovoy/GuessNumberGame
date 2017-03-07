package rynkovoy.od.guessnumbergame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etMyNumber;

    Button btnAnswer;
    Button btnAgain;
    Button btnExit;

    LinearLayout layoutAnswers;

    TextView tvTimer;

    Timer timer;
    TimerTask timerTask;
    TextView tvPoints;
    TextView tvTry;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        etMyNumber = (EditText) findViewById(R.id.etMyNumber);
        btnAnswer = (Button) findViewById(R.id.btnAnswer);
        btnAgain = (Button) findViewById(R.id.btnAgain);
        btnExit = (Button) findViewById(R.id.btnExit);
        layoutAnswers = (LinearLayout) findViewById(R.id.layoutAnswers);

        btnAnswer.setOnClickListener(this);
        btnAgain.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvTry = (TextView) findViewById(R.id.tvTry);

        tvTimer.setText("Time: " + sec);
        tvPoints.setText("Points: " + points);
        tvTry.setText("Try: " + countTry);

       timerStart();
    }

    int numberLength = 1000;
    int randomNumber = (int) (Math.random() * numberLength + 1);
    int countTry = 0;
    int points = 100;
    int sec = 0;
    final int DIALOG_SAVE = 1;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAnswer:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btnAnswer.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                try {
                    if (randomNumber > Integer.parseInt(etMyNumber.getText() + "")) {
                        TextView tv = new TextView(this);
                        tv.setText("The number more than " + Integer.parseInt(etMyNumber.getText() + ""));
                        layoutAnswers.addView(tv);
                        etMyNumber.setText("");
                        countTry++;
                        points -= 5;
                    } else if (randomNumber < Integer.parseInt(etMyNumber.getText() + "")) {
                        TextView tv = new TextView(this);
                        tv.setText("The number less than " + Integer.parseInt(etMyNumber.getText() + ""));
                        layoutAnswers.addView(tv);
                        etMyNumber.setText("");
                        countTry++;
                        points -= 5;
                    } else if (randomNumber == Integer.parseInt(etMyNumber.getText() + "")) {
                        TextView tv = new TextView(this);
                        tv.setText("-*Congratulations!*- You guessed! =)");
                        tv.setTextColor(Color.rgb(50, 205, 50));
                        layoutAnswers.addView(tv);
                        etMyNumber.setText("");
                        timerStop();
                        new AlertDialog.Builder(this)
                                .setTitle("Congratulations!")
                                .setMessage("Do you want to save the result?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getBaseContext(), SaveScoreActivity.class);
                                        intent.putExtra("points", points + "");
                                        intent.putExtra("try", countTry + "");
                                        intent.putExtra("time", sec + "");
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                                    }
                                })
                                .setIcon(android.R.drawable.ic_menu_save)
                                .show();
                    }
                }catch (Exception e) {
                    TextView tv = new TextView(this);
                    tv.setText("Enter some number!");
                    tv.setTextColor(Color.rgb(255, 0, 0));
                    layoutAnswers.addView(tv);
                    etMyNumber.setText("");
                }

                if (points == 0) {
                    TextView tv = new TextView(this);
                    tv.setText("Game over!");
                    tv.setTextColor(Color.rgb(255, 0, 0));
                    layoutAnswers.addView(tv);
                    etMyNumber.setText("");
                    new AlertDialog.Builder(this)
                            .setTitle("Game over!")
                            .setMessage("Do you want to try again?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getBaseContext(), GameActivity.class));
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();
                }
                break;
            case R.id.btnAgain:
                randomNumber = (int) (Math.random() * 10 + 1);
                layoutAnswers.removeAllViews();
                etMyNumber.setText("");
                break;
            case R.id.btnExit:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        tvPoints.setText("Points: " + points);
        tvTry.setText("Try: " + countTry);

    }



    public void timerStop() {
        timer.cancel();
    }

    public void timerStart() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerMethod();
            }
        }, 0, 1000);
    }

    public void timerMethod() {
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            if (sec < 10) {
                tvTimer.setText("Time: 0" + sec);
                sec++;
            }else {
                tvTimer.setText("Time: " + sec);
                sec++;
            }
        }
    };
}
