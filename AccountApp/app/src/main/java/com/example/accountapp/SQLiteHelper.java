package com.example.accountapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "account.db";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase myDB;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS Account (email TEXT PRIMARY KEY, password TEXT)";
        db.execSQL(sql);
        db.execSQL("INSERT OR IGNORE INTO Account VALUES ('nttnga@gmail.com','123')");
        db.execSQL("INSERT OR IGNORE INTO Account VALUES ('abc@gmail.com','123')");
        db.execSQL("INSERT OR IGNORE INTO Account VALUES ('admin@gmail.com','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Account");
        onCreate(db);
    }

    public void openDB() throws SQLException {
        if (myDB == null || !myDB.isOpen()) {
            myDB = getWritableDatabase();
        }
    }

    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }
    }

    /**
     * Chèn tài khoản mới
     * @return true nếu thành công, false nếu email đã tồn tại
     */
    public boolean insert(Account acc) {
        openDB();
        ContentValues cv = new ContentValues();
        cv.put("email", acc.getEmail());
        cv.put("password", acc.getPass());
        long result = myDB.insertWithOnConflict("Account", null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        return result != -1;
    }

    public boolean login(String email, String pass) {
        openDB();
        String sql = "SELECT * FROM Account WHERE email=? AND password=?";
        Cursor c = myDB.rawQuery(sql, new String[]{email, pass});
        boolean exists = c.getCount() > 0;
        c.close();
        return exists;
    }

    public boolean checkEmailExists(String email) {
        openDB();
        Cursor c = myDB.rawQuery("SELECT * FROM Account WHERE email=?", new String[]{email});
        boolean exists = c.getCount() > 0;
        c.close();
        return exists;
    }
}
