package com.example.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";

    public static final String TABLE_NAME = "USERS";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VAL = "val";

    private static final String SQL_CREATE_TABLE_STATEMENT = "CREATE TABLE " + TABLE_NAME +
                                                             "(" +
                                                                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                                COLUMN_NAME + " TEXT UNIQUE, " +
                                                                COLUMN_VAL + " INTEGER" +
                                                             ")";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // is called the first time the db is accessed. Code creates a new table

        db.execSQL(SQL_CREATE_TABLE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) { // if the db is updated (columns added), it updates that

    }

    public boolean addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues(); // To je nek map, v kateri pise v katere columne grejo keri podatki
                                                // NOTE: Id-ja ni treba dat ker je autoincrement
        cv.put(COLUMN_NAME, user.getName());
        cv.put(COLUMN_VAL, user.getVal());

        // dodamo row v table
        // dobimo new rowId (primary key)
        long newRowId = db.insert(TABLE_NAME, null, cv);// 1) ime table, 2) nullColumnHack, 3) value map

        if (newRowId == -1) { // ce newRowId == -1, potem ni bil uspesen insert()

            return false;
        }
        return true;
    }

    public User getUserByName(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        // setup za SELECT query
        // ---

        // dodamo, da iscemo kjer je COLUMN_NAME == username
        // torej WHERE statement v SQLite
        String selection = COLUMN_NAME + "= ?";
        String[] selectionArgs = { username };

        // ---

        // nazaj dobimo cursor, ki kaze na dani user column. Lahko premikamo po njih
        // je nek array of items
        Cursor cursor = db.query(
                TABLE_NAME,    // table iz katere SELECTA
                null,
                selection,     // COLUMNS za WHERE
                selectionArgs, // VALUES za WHERE
                null,
                null,
                null,
                null
            );

        User currentUser;
        if (cursor.moveToFirst()) { // ce smo dobili result

            // result (column od tega userja) shranimo in damo v User object
            int userId = cursor.getInt(0);
            String userName = cursor.getString(1);
            int userVal = cursor.getInt(2);

            currentUser = new User(userId, userName, userVal);
        }
        else { // ce se ni nic prebralo

            currentUser = null;
        }

        cursor.close();
        return currentUser;
    }

    public User getUserById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        // setup za SELECT query
        // ---

        // v tem primeru delamo brez selectionArgs, da pokazem da se da tudi tako
        String selection = COLUMN_ID + "= " + id;

        // ---

        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                null,
                null
        );

        User currentUser;
        if (cursor.moveToFirst()) { // ce smo dobili result

            int userId = cursor.getInt(0);
            String userName = cursor.getString(1);
            int userVal = cursor.getInt(2);

            currentUser = new User(userId, userName, userVal);
        }
        else { // ce se ni nic prebralo

            currentUser = null;
        }

        cursor.close();
        return currentUser;
    }

    // returns: true
    // if error returns: false
    public boolean incrementUserValueById(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        // we get the current val and the new value
        int currentVal = this.getUserById(id).getVal();
        int newVal = currentVal + 1;

        // we put the newVal in the ContentValues
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_VAL, newVal);

        // we get the row to update the value to (based on the id)
        // NOTE: we could also use the selectionArgs with "?" instead of the '+ id'
        String selection = COLUMN_ID + " LIKE " + id;

        // we update the currentVal to newVal
        // count == number of updated items
        int count = db.update(TABLE_NAME, cv, selection, null);

        if (count == 0) { // if it did not update the row

            return false;
        }
        return true;
    }

}
