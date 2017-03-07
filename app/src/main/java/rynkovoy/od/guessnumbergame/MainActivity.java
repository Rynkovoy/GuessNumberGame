package rynkovoy.od.guessnumbergame;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnStart;
    Button btnScore;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnScore = (Button) findViewById(R.id.btnScore);
        btnExit = (Button) findViewById(R.id.btnExit);

        btnStart.setOnClickListener(this);
        btnScore.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {

        final int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStart:
                startActivity(new Intent(this, GameActivity.class));
                break;
            case R.id.btnScore:
                startActivity(new Intent(this, ScoreActivity.class));
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }
}
