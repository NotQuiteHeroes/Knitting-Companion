package com.eckstein.paige.knittingcompanion.DatabaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eckstein.paige.knittingcompanion.Counters.Counter;

import java.util.ArrayList;

public class CounterDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "KnittingCompanionCounter.db";
    private static final String TABLE_NAME = "counters";
    private static final String PROJECT_NAME = "project_name";
    private static final String COUNTER_NAME = "counter_name";
    private static final String ONES = "ones";
    private static final String TENS = "tens";
    private static final String HUNDREDS = "hundreds";


    public CounterDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                "(id integer primary key, project_name text, counter_name text, ones text, tens text," +
                " hundreds text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String projectName, String counterName, String ones, String tens,
                          String hundreds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROJECT_NAME, projectName);
        values.put(COUNTER_NAME, counterName);
        values.put(ONES, ones);
        values.put(TENS, tens);
        values.put(HUNDREDS, hundreds);
        db.insert(TABLE_NAME, null, values);
        db.close();
        return true;
    }

    public Cursor getdata(String projectName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where project_name=" + projectName + "", null);
        db.close();
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = ((int) DatabaseUtils.queryNumEntries(db, TABLE_NAME));
        db.close();
        return numRows;
    }

    public boolean updateCounter(String projectName, String counterName, String ones, String tens,
                                 String hundreds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROJECT_NAME, projectName);
        values.put(COUNTER_NAME, counterName);
        values.put(ONES, ones);
        values.put(TENS, tens);
        values.put(HUNDREDS, hundreds);
        db.update(TABLE_NAME, values, "project_name = ? AND counter_name = ?", new String[]{projectName, counterName});
        db.close();
        return true;
    }

    public Integer deleteCounter(String projectName, String counterName) {
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(TABLE_NAME, "project_name = ? AND counter_name = ?", new String[]{projectName, counterName});
        db.close();
        return delete;
    }

    public ArrayList<Counter> getAllCounters() {
        ArrayList<Counter> counters = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + "", null);
        res.moveToFirst();

        String projectName, counterName;
        int ones, tens, hundreds;

        while (!res.isAfterLast()) {
            projectName = res.getString(res.getColumnIndex(PROJECT_NAME));
            counterName = res.getString(res.getColumnIndex(COUNTER_NAME));
            ones = Integer.parseInt(res.getString(res.getColumnIndex(ONES)));
            tens = Integer.parseInt(res.getString(res.getColumnIndex(TENS)));
            hundreds = Integer.parseInt(res.getString(res.getColumnIndex(HUNDREDS)));
            Counter counter = new Counter(counterName, projectName, ones, tens, hundreds);
            counters.add(counter);
            res.moveToNext();
        }

        res.close();
        db.close();
        return counters;
    }
}

