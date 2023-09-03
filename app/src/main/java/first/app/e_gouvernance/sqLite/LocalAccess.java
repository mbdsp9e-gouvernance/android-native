package first.app.e_gouvernance.sqLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LocalAccess {
    private String dbName = "dbGouvernance.sqlite";
    private Integer dbVersion = 1;
    private MySQLiteOpenHelper dbAccess;
    private SQLiteDatabase db;

    public LocalAccess(Context context) {
        dbAccess = new MySQLiteOpenHelper(context, dbName, null, dbVersion);
    }

    public MySQLiteOpenHelper getDBAccess() {
        return dbAccess;
    }

    public void add(String req) {
        db = dbAccess.getWritableDatabase();
       /* String req = "insert into profil(username, name, surname) values";
        req += "(userTest,test,test)";*/
        db.execSQL(req);
    }

    public void get(String req) {
        db = dbAccess.getReadableDatabase();
        // String req = "select * from profil";
        Cursor cursor = db.rawQuery(req, null);
        cursor.moveToLast();
        if (!cursor.isAfterLast()) {
            String userName = cursor.getString(1);
            String name = cursor.getString(2);
            String surname = cursor.getString(3);
        }
        cursor.close();
        //return profil
    }
}
