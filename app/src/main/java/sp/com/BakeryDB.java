package sp.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BakeryDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_table.db";
    private static final int SCHEMA_VERSION = 1;

    public BakeryDB(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user_table (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, "+
                "email TEXT, useradmission TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public Cursor getAll(){
        return(getReadableDatabase().rawQuery(
                "SELECT _id, name, email, useradmission" +
                        "  FROM user_table", null));
    }

    public void insert(String name, String email, String useradmission){
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("email", email);
        cv.put("useradmission", useradmission);

        getWritableDatabase().insert("user_table", null, cv);
    }

    public int delete(){
        return getWritableDatabase().delete("user_table", null, null);

    }
}
