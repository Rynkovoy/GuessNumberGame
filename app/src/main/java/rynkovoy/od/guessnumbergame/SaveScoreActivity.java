package rynkovoy.od.guessnumbergame;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SaveScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPoints;
    private EditText etTime;
    private EditText etTry;

    private Button btnCancle;
    private Button btnSave;

    private DataBase database;

    //SharedPreferences sharedPreferences;

//    String SAVE_FIRST_NAME = "save_first_name";
//    String SAVE_LAST_NAME = "save_last_name";
//    String SAVE_POINT = "save_points";
//    String SAVE_TIME = "save_time";
//    String SAVE_TRY = "save_try";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_score);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPoints = (EditText) findViewById(R.id.etPoints);
        etTime = (EditText) findViewById(R.id.etTime);
        etTry = (EditText) findViewById(R.id.etTry);

        String countTry = getIntent().getStringExtra("try");
        String points = getIntent().getStringExtra("points");
        String time = getIntent().getStringExtra("time");


        etPoints.setText(points);
        etTry.setText(countTry);
        etTime.setText(time);

        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);
        btnCancle.setOnClickListener(this);

        database = new DataBase(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                writeDB();
                etFirstName.setEnabled(false);
                etLastName.setEnabled(false);
                Toast.makeText(SaveScoreActivity.this, "SAVED", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancle:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


    private void writeDB() {
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBase.KEY_FIRST_NAME, etFirstName.getText().toString());
        contentValues.put(DataBase.KEY_LAST_NAME, etLastName.getText().toString());
        contentValues.put(DataBase.KEY_POINTS, etPoints.getText().toString());
        contentValues.put(DataBase.KEY_TRY, etTry.getText().toString());
        contentValues.put(DataBase.KEY_TIME, etTime.getText().toString());

        sqLiteDatabase.insert(DataBase.TABLE_SCORE, null, contentValues);
    }

//    private void save() {
//        sharedPreferences = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(SAVE_FIRST_NAME, etFirstName.getText().toString());
//        editor.putString(SAVE_LAST_NAME, etLastName.getText().toString());
//        editor.putString(SAVE_POINT, etPoints.getText().toString());
//        editor.putString(SAVE_TIME, etTime.getText().toString());
//        editor.putString(SAVE_TRY, etTry.getText().toString());
//        editor.commit();
//        Toast.makeText(SaveScoreActivity.this, "Saved", Toast.LENGTH_SHORT).show();
//    }

}





















