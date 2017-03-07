package rynkovoy.od.guessnumbergame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anton on 22.09.2016.
 */
public class DataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "scoreDataBase";
    public static final String TABLE_SCORE = "information";

    public static final String KEY_ID = "_id";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_POINTS = "points";
    public static final String KEY_TRY = "try";
    public static final String KEY_TIME = "time";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_SCORE + "("
                + KEY_ID + " integer primary key, "
                + KEY_FIRST_NAME + " text, "
                + KEY_LAST_NAME + " text, "
                + KEY_POINTS + " integer, "
                + KEY_TRY + " integer, "
                + KEY_TIME + " integer" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exist " + TABLE_SCORE);
        onCreate(sqLiteDatabase);
    }
}
