package com.example.shan.dbapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String KeyID = "id";
    private static final String DBName = "MyDB";
    private  static  final String TableName = "users";
    private  static  final String username = "user_name";
    private  static  final String useremail = "user_email";

    private Context context;

    private static DBHelper singleton;

    public static DBHelper getDBHelper(Context context) {
        if (singleton == null) {
            singleton = new DBHelper(context);
        }
        singleton.context = context;
        return singleton;
    }

    private DBHelper(Context context) {
        super(context, DBName, null, 1);
        this.context = context;
    }

    // Create Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+ TableName +"("
                + KeyID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + username + " TEXT NOT NULL,"
                + useremail + "  TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        // Create tables again
        onCreate(db);
    }

    public void insertUser(String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(username, name);
        contentValues.put(useremail, email);
        db.insert(TableName, null, contentValues);
    }

    public User getUser(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.rawQuery("SELECT * FROM " + TableName
                                + " WHERE " + KeyID + "=?",
                        new String[] {Integer.toString(userId)} );

        if (cursor != null)
            cursor.moveToFirst();

        User u = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return u;
    }

    public List<User> getAllManufacturers() {
        List<User> mList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2));
                mList.add(user);
            } while (cursor.moveToNext());
        }
        return mList;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(username, user.getUsername());
        values.put(useremail, user.getEmail());
        return db.update(TableName, values, KeyID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public void deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TableName, KeyID + " = ?",
                new String[] { String.valueOf(userId) });
    }
}
