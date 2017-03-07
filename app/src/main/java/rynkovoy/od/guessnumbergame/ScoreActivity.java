package rynkovoy.od.guessnumbergame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    Button btnCancle2;

    LinearLayout layoutScore;

    DataBase database;


//    ScoreActivity(SharedPreferences sp){
//
//    }
    String SAVE_FIRST_NAME = "save_first_name";
    String SAVE_LAST_NAME = "save_last_name";
    String SAVE_POINT = "save_points";
    String SAVE_TIME = "save_time";
    String SAVE_TRY = "save_try";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        btnCancle2 = (Button) findViewById(R.id.btnCancle2);
        layoutScore = (LinearLayout) findViewById(R.id.layoutScore);
        database = new DataBase(this);
        readDB();


        btnCancle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScoreActivity.this, MainActivity.class));
            }
        });
    }

    private void readDB() {
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(DataBase.TABLE_SCORE, null, null, null, null, null, null);

        int indexID = cursor.getColumnIndex(DataBase.KEY_ID);
        int indexFirstName = cursor.getColumnIndex(DataBase.KEY_FIRST_NAME);
        int indextLastName = cursor.getColumnIndex(DataBase.KEY_LAST_NAME);
        int indexPoints = cursor.getColumnIndex(DataBase.KEY_POINTS);
        int indexTry = cursor.getColumnIndex(DataBase.KEY_TRY);
        int indexTime = cursor.getColumnIndex(DataBase.KEY_TIME);

        if (cursor.moveToFirst()) {
            do {
                Log.d("myLog",
                          "ID - " + cursor.getInt(indexID)
                        + "First Name - " + cursor.getString(indexFirstName)
                        + "Last Name - " + cursor.getString(indextLastName)
                        + "Points - " + cursor.getInt(indexPoints)
                        + "Try - " + cursor.getInt(indexTry)
                        + "Time - " + cursor.getInt(indexTime)

                );

                LinearLayout newLayout = new LinearLayout(this);
                newLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


               // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.gravity = Gravity.CENTER;
//                params.weight = 1;

                TextView tvFirstName = new TextView(this);
                tvFirstName.setText(cursor.getString(indexFirstName));
                tvFirstName.setMinWidth((int) getResources().getDimension(R.dimen.widthName));
                tvFirstName.setMaxWidth((int) getResources().getDimension(R.dimen.widthName));
//                tvFirstName.setLayoutParams(params);

                TextView tvLastName = new TextView(this);
                tvLastName.setText(cursor.getString(indextLastName));
                tvLastName.setMinWidth((int) getResources().getDimension(R.dimen.widthName));
                tvLastName.setMaxWidth((int) getResources().getDimension(R.dimen.widthName));
//                tvLastName.setLayoutParams(params);

                TextView tvPoints = new TextView(this);
                tvPoints.setText(cursor.getString(indexPoints));
                tvPoints.setMinWidth((int) getResources().getDimension(R.dimen.widthPoint));
                tvPoints.setMaxWidth((int) getResources().getDimension(R.dimen.widthPoint));
//                tvPoints.setLayoutParams(params);

                TextView tvTry = new TextView(this);
                tvTry.setText(cursor.getString(indexTry));
                tvTry.setMinWidth((int) getResources().getDimension(R.dimen.widthTry));
                tvTry.setMaxWidth((int) getResources().getDimension(R.dimen.widthTry));
//                tvTry.setLayoutParams(params);

                TextView tvTime = new TextView(this);
                tvTime.setText(cursor.getString(indexTime));
                tvTime.setMinWidth((int) getResources().getDimension(R.dimen.widthTime));
                tvTime.setMaxWidth((int) getResources().getDimension(R.dimen.widthTime));
//                tvTime.setLayoutParams(params);


                newLayout.addView(tvFirstName);
                newLayout.addView(tvLastName);
                newLayout.addView(tvPoints);
                newLayout.addView(tvTry);
                newLayout.addView(tvTime);

                layoutScore.addView(newLayout);

            }while (cursor.moveToNext());
        }else Log.d("MyLog", "0 rows");
        cursor.close();
    }































//    void load() {
//        Singleton singleton = new Singleton();
//
//        sharedPreferences = singleton.getSharedPreferences();
//
//        sharedPreferences = getPreferences(MODE_PRIVATE);
//        TextView tvFirstName = new TextView(this);
//        TextView tvLastName = new TextView(this);
//        TextView tvPoints = new TextView(this);
//        TextView tvTry = new TextView(this);
//        TextView tvTime = new TextView(this);
//
//        tvFirstName.setText(sharedPreferences.getString(SAVE_FIRST_NAME, "unnamed"));
//        tvLastName.setText(sharedPreferences.getString(SAVE_LAST_NAME, "unnamed"));
//        tvPoints.setText(sharedPreferences.getString(SAVE_POINT, "-"));
//        tvTry.setText(sharedPreferences.getString(SAVE_TRY, "-"));
//        tvTime.setText(sharedPreferences.getString(SAVE_TIME, "-"));
//
//        LinearLayout layout = new LinearLayout(this);
//        layout.setOrientation(LinearLayout.HORIZONTAL);
//        layout.addView(tvFirstName);
//        layout.addView(tvLastName);
//        layout.addView(tvPoints);
//        layout.addView(tvTry);
//        layout.addView(tvTime);
//
//        layoutScore = (LinearLayout) findViewById(R.id.layoutScore);
//        layoutScore.addView(layout);
//    }

}
