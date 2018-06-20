package com.manan.mchat.SQLDatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Suneja's on 20-06-2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "database_name";

    // Table Names
    private static final String DB_TABLE = "table_name";
    private static final String USER_NAME = "user_name";
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + DB_TABLE + "(" +
            USER_NAME + " TEXT);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        // create new table
        onCreate(db);
    }


    public void addName(String name) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, name);
        database.insert(DB_TABLE, null, cv);
        Log.d("prerna", "name added");
    }

    public void updateName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        db.update(DB_TABLE, contentValues, null, null);
        Log.d("prerna", "name updated");
    }

    public String retrieveName() {
        String selectQuery = "SELECT  * FROM " + DB_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            cursor.close();
            Log.d("prerna", "name1 retrieved");
            return name;
        }
        cursor.close();
        Log.d("prerna", "name2 retrieved");
        return null;

    }
}