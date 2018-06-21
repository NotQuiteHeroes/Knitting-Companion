package com.eckstein.paige.knittingcompanion.DatabaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eckstein.paige.knittingcompanion.Counters.Counter;

import java.util.ArrayList;

/**
 * Database helper for Counter objects
 */
public class CounterDBHelper extends SQLiteOpenHelper {
    //database information
    private static final String DATABASE_NAME = "KnittingCompanionCounter.db";
    private static final String TABLE_NAME = "counters";
    private static final String PROJECT_NAME = "project_name";
    private static final String COUNTER_NAME = "counter_name";
    private static final String ONES = "ones";
    private static final String TENS = "tens";
    private static final String HUNDREDS = "hundreds";

    /**
     * Constructor
     * @param context Context that ProjectDBHelper is being created in
     */
    public CounterDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Create Counters table in database
     * @param db Database to create Counters table in
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                "(id integer primary key, project_name text, counter_name text, ones text, tens text," +
                " hundreds text)");
    }

    /**
     * When updating table, drop old one and create new one
     * @param db Database to update table in
     * @param oldVersion int old version of table
     * @param newVersion int new version of table
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Insert new row into Counters table
     * @param projectName String: Name of project counter belongs to
     * @param counterName String: name of counter (ex: rows, repeats, etc.)
     * @param ones String: string conversion of ones position in counter
     * @param tens String: string conversion of tens position in counter
     * @param hundreds String: string conversion of hundreds position in counter
     * @return
     */
    public boolean insert(String projectName, String counterName, String ones, String tens,
                          String hundreds) {
        //get database with write permission
        SQLiteDatabase db = this.getWritableDatabase();
        //hold values to insert
        ContentValues values = new ContentValues();

        //add counter variables to values
        values.put(PROJECT_NAME, projectName);
        values.put(COUNTER_NAME, counterName);
        values.put(ONES, ones);
        values.put(TENS, tens);
        values.put(HUNDREDS, hundreds);

        //insert values to Counter table
        db.insert(TABLE_NAME, null, values);
        db.close();
        return true;
    }

    /**
     * get row from Counter's table where project name matches provided project name
     * @param projectName project name associated with counter to retrieve
     * @return Cursor holding query results
     */
    public Cursor getdata(String projectName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where project_name=" + projectName + "", null);
        db.close();
        return res;
    }

    /**
     * get total number of rows in Counter table
     * @return int: total number of rows in Counter table
     */
    public int numberOfRows() {
        //get database with read permission
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = ((int) DatabaseUtils.queryNumEntries(db, TABLE_NAME));
        db.close();
        return numRows;
    }

    /**
     * Update an existing row in Counter table
     * @param projectName String: name of project associated with counter to be updated
     * @param counterName String: name of counter to be updated
     * @param ones String: string version of ones position in counter
     * @param tens String: string version of tens position in counter
     * @param hundreds String: string version of hundreds position in counter
     * @return return true upon updating row
     */
    public boolean updateCounter(String projectName, String counterName, String ones, String tens,
                                 String hundreds) {
        //get database with write permission
        SQLiteDatabase db = this.getWritableDatabase();
        //create values object to hold row information
        ContentValues values = new ContentValues();

        //add new information for row to be updated
        values.put(PROJECT_NAME, projectName);
        values.put(COUNTER_NAME, counterName);
        values.put(ONES, ones);
        values.put(TENS, tens);
        values.put(HUNDREDS, hundreds);

        //update the row in Counter table where project name matches provided and counter name matches provided
        db.update(TABLE_NAME, values, "project_name = ? AND counter_name = ?", new String[]{projectName, counterName});
        db.close();
        return true;
    }

    /**
     * delete row from Counter table
     * @param projectName String project name associated with counter to be deleted
     * @param counterName String name of counter to be deleted
     * @return int: number of rows affected
     */
    public Integer deleteCounter(String projectName, String counterName) {
        //database with write permission
        SQLiteDatabase db = this.getWritableDatabase();
        //delete row where project name and counter name match provided names
        int delete = db.delete(TABLE_NAME, "project_name = ? AND counter_name = ?", new String[]{projectName, counterName});
        db.close();
        return delete;
    }

    /**
     * get all rows from database
     * create Counter objects for each
     * and add to ArrayList
     * @return ArrayList of Counter objects created from all rows in the Counter table
     */
    public ArrayList<Counter> getAllCounters() {
        //holds Counter objects created from db query
        ArrayList<Counter> counters = new ArrayList<>();
        //readable database
        SQLiteDatabase db = this.getReadableDatabase();
        //get all from Counter table
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + "", null);
        //move to first row
        res.moveToFirst();

        //Counter object fields
        String projectName, counterName;
        int ones, tens, hundreds;

        //while still rows to go through...
        while (!res.isAfterLast()) {
            //get and parse all columns from row
            projectName = res.getString(res.getColumnIndex(PROJECT_NAME));
            counterName = res.getString(res.getColumnIndex(COUNTER_NAME));
            ones = Integer.parseInt(res.getString(res.getColumnIndex(ONES)));
            tens = Integer.parseInt(res.getString(res.getColumnIndex(TENS)));
            hundreds = Integer.parseInt(res.getString(res.getColumnIndex(HUNDREDS)));
            //create Counter object with parsed data above
            Counter counter = new Counter(counterName, projectName, ones, tens, hundreds);
            //add Counter object to ArrayList
            counters.add(counter);
            //go to next row
            res.moveToNext();
        }

        res.close();
        db.close();
        return counters;
    }
}

