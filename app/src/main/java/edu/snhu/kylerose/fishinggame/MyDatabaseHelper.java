package edu.snhu.kylerose.fishinggame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // Variables for database
    private Context context;
    private static String DATABASE_NAME = "FishingGame.db";
    private static final int DATABASE_VERSION = 1;

    // Variables for login table
    public static final String TABLE_NAME = "my_login";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "user_name";
    public static final String COLUMN_PASSWORD = "user_password";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Creating database with login table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_USERNAME + " TEXT, " +
                        COLUMN_PASSWORD + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    // Method for adding account to database
    public void insertAccount(String userName, String userPassword){
        // Open database
        SQLiteDatabase db = this.getWritableDatabase();
        // Getting content values
        ContentValues cv = new ContentValues();
        cv.put(MyDatabaseHelper.COLUMN_USERNAME, userName);
        cv.put(MyDatabaseHelper.COLUMN_PASSWORD, userPassword);

        // Adding values to database
        long result = db.insert(TABLE_NAME,null,cv);
        // if insert fails, display failure message
        if (result == -1) {
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        // else, display success message
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
