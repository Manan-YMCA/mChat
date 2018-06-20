package com.manan.mchat.SQLDatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Suneja's on 19-06-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "database_name";

    // Table Names
    private static final String DB_TABLE = "table_image";

    // column names

    private static final String KEY_IMAGE = "image_data";

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "(" +
            KEY_IMAGE + " BLOB);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table
        db.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        // create new table
        onCreate(db);
    }

    public void addImage(byte[] image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IMAGE, image);
        database.insert(DB_TABLE, null, cv);
        Log.d("prerna", "image added");
    }

    public void updateImage(byte[] image) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IMAGE, image);
        database.update(DB_TABLE, cv, null, null);
        Log.d("prerna", "image updated");
    }

    public byte[] retrieveImage() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cur = database.query(true, DB_TABLE, null,
                null, null, null, null,
                null, null);
        Log.d("prerna", Integer.toString(cur.getCount()));
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(KEY_IMAGE));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }

}