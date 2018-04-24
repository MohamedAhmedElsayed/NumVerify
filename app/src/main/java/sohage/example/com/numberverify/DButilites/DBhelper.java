package sohage.example.com.numberverify.DButilites;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mohamed_Ahmed on 16/04/2018.
 */
public class DBhelper extends SQLiteOpenHelper {// this class acts as a management for verification DB 
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "Verification";
    private final static String CREATE_TABLE = "create table " + HistoryTable.TABLE_NAME + "(" +
            HistoryTable._ID + " INTEGER PRIMARY KEY , " +
            HistoryTable.CARRIER + " TEXT NOT NULL ," +
            HistoryTable.COUNTRY_CODE + " TEXT NOT NULL, " +
            HistoryTable.COUNTRY_NAME + " TEXT NOT NULL," +
            HistoryTable.COUNTRY_PREFIX + " TEXT NOT NULL ," +
            HistoryTable.INTERNATIONAL_FORMAT + " TEXT NOT NULL ," +
            HistoryTable.LINE_TYPE + " TEXT NOT NULL ," +
            HistoryTable.LOCATION + " TEXT NOT NULL," +
            HistoryTable.VALID + " Boolean NOT NULL," +
            HistoryTable.MOBILE_NUMBER + " TEXT NOT NULL," +
            HistoryTable.LOCAL_FORMAT + " TEXT NOT NULL)";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HistoryTable.TABLE_NAME);
        onCreate(db);
    }
}
