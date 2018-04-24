package sohage.example.com.numberverify.DButilites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sohage.example.com.numberverify.Model.ResponseModel;

/**
 * Created by Mohamed_Ahmed on 16/04/2018.
 */
public class DBoperations {
    SQLiteDatabase writableDB;
    SQLiteDatabase readableDB;
    private DBhelper dbhelper;

    public DBoperations(Context c) {//initialize writer or readable DB
        if (c != null) {
            dbhelper = new DBhelper(c);
            writableDB = dbhelper.getWritableDatabase();
            readableDB = dbhelper.getReadableDatabase();
        }
    }

    public long InsertRow(ResponseModel model) {//to insert record to DB
        ContentValues values = new ContentValues();
        values.put(HistoryTable.CARRIER, model.getCarrier());
        values.put(HistoryTable.COUNTRY_CODE, model.getCountry_code());
        values.put(HistoryTable.COUNTRY_NAME, model.getCountry_name());
        values.put(HistoryTable.LOCATION, model.getLocation());
        values.put(HistoryTable.LOCAL_FORMAT, model.getLocal_format());
        values.put(HistoryTable.INTERNATIONAL_FORMAT, model.getInternational_format());
        values.put(HistoryTable.LINE_TYPE, model.getLine_type());
        values.put(HistoryTable.VALID, model.getValid());
        values.put(HistoryTable.MOBILE_NUMBER, model.getNumber());
        values.put(HistoryTable.COUNTRY_PREFIX, model.getCountry_prefix());
        return writableDB.insert(HistoryTable.TABLE_NAME, null, values);//return number of inserted rows
    }

    public List<ResponseModel> SelectAll() {//to select all table contents 
        List<ResponseModel> HistoryResult = new ArrayList<>();
        String query = "select * from " + HistoryTable.TABLE_NAME;
        Cursor cursor = readableDB.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            ResponseModel model = new ResponseModel();
            model.setInternational_format(cursor.getString(cursor.getColumnIndex(HistoryTable.INTERNATIONAL_FORMAT)));
            model.setCountry_name(cursor.getString(cursor.getColumnIndex(HistoryTable.COUNTRY_NAME)));
            model.setCarrier(cursor.getString(cursor.getColumnIndex(HistoryTable.CARRIER)));
            model.setCountry_code(cursor.getString(cursor.getColumnIndex(HistoryTable.COUNTRY_CODE)));
            model.setCountry_prefix(cursor.getString(cursor.getColumnIndex(HistoryTable.COUNTRY_PREFIX)));
            model.setLine_type(cursor.getString(cursor.getColumnIndex(HistoryTable.LINE_TYPE)));
            model.setLocation(cursor.getString(cursor.getColumnIndex(HistoryTable.LOCATION)));
            model.setNumber(cursor.getString(cursor.getColumnIndex(HistoryTable.MOBILE_NUMBER)));
            model.setValid(cursor.getString(cursor.getColumnIndex(HistoryTable.VALID)));
            model.setLocal_format(cursor.getString(cursor.getColumnIndex(HistoryTable.LOCAL_FORMAT)));
            HistoryResult.add(model);
        }
        cursor.close();
        return HistoryResult;
    }
}
