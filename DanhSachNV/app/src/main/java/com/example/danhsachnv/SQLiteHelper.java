package com.example.danhsachnv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "nhanvien.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "NhanVien";
    private static final String COL_ID = "ma";
    private static final String COL_NAME = "ten";
    private static final String COL_AGE = "tuoi";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " TEXT PRIMARY KEY, " +
                COL_NAME + " TEXT, " +
                COL_AGE + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert nhân viên
    public void insert(Employee emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID, emp.getId());
        values.put(COL_NAME, emp.getName());
        values.put(COL_AGE, emp.getAge());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Delete theo mã
    public void delete(String ma) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + "=?", new String[]{ma});
        db.close();
    }

    // Lấy tất cả, sort theo tên
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_NAME + " ASC", null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(COL_AGE));
                list.add(new Employee(id, name, age));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


    public void insertSampleData() {
        if (getAll().isEmpty()) {
            insert(new Employee("NV-001", "Nguyễn Đại Nhân", 30));
            insert(new Employee("NV-002", "Trần Đại Nghĩa", 28));
            insert(new Employee("NV-003", "Hoàng Đại Lê", 35));
            insert(new Employee("NV-004", "Phạm Đại Trí", 42));
            insert(new Employee("NV-005", "Trương Đại Tín", 25));
            insert(new Employee("NV-006", "Hồ Đại Đức", 40));
        }
    }
}