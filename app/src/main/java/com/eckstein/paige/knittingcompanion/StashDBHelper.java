package com.eckstein.paige.knittingcompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StashDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "KnittingCompanion.db";
    private static final String TABLE_NAME = "stash";
    private static final String YARN_NAME = "yarn_name";
    private static final String COLORWAY = "colorWay";
    private static final String WEIGHT = "weight";
    private static final String FIBER = "fiber";
    private static final String YARD_PER_SKEIN = "yard_per_skein";
    private static final String SKEINS_OWNED = "skeins_owned";


    public StashDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME +
                "(id integer primary key, yarn_name text, colorWay text, weight text, fiber text," +
                " yard_per_skein text, skeins_owned text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String yarnName, String colorway, String weight, String fiber,
                          String totalYards, String totalSkeins)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(YARN_NAME, yarnName);
        values.put(COLORWAY, colorway);
        values.put(WEIGHT, weight);
        values.put(FIBER, fiber);
        values.put(YARD_PER_SKEIN, totalYards);
        values.put(SKEINS_OWNED, totalSkeins);
        db.insert(TABLE_NAME, null, values);
        db.close();
        return true;
    }

    public Cursor getdata(String yarnName, String colorWay)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from stash WHERE yarn_name = "+yarnName+" AND colorWay = "+colorWay+"", null);
        db.close();
        return res;
    }

    public int numberOfRows()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = ((int) DatabaseUtils.queryNumEntries(db, TABLE_NAME));
        db.close();
        return numRows;
    }

    public boolean updateStash(String yarnName, String colorWay, String weight, String fiber,
                                 String totalYards, String totalSkeins)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(YARN_NAME, yarnName);
        values.put(COLORWAY, colorWay);
        values.put(WEIGHT, weight);
        values.put(FIBER, fiber);
        values.put(YARD_PER_SKEIN, totalYards);
        values.put(SKEINS_OWNED, totalSkeins);
        db.update(TABLE_NAME, values, "yarn_name = ? AND colorWay = ?", new String[] {yarnName, colorWay});
        db.close();
        return true;
    }

    public Integer deleteYarn (String yarnName, String colorWay)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(TABLE_NAME, "yarn_name = ? AND colorWay = ?", new String[] { yarnName, colorWay });
        db.close();
        return delete;
    }

    public ArrayList<Yarn> getAllYarn()
    {
        ArrayList<Yarn> stash = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + "", null);
        res.moveToFirst();

        String yarnName, colorWay, weight, fiber;
        int totalYards, totalSkeins;

        while(!res.isAfterLast())
        {
            yarnName = res.getString(res.getColumnIndex(YARN_NAME));
            colorWay = res.getString(res.getColumnIndex(COLORWAY));
            weight = res.getString(res.getColumnIndex(WEIGHT));
            fiber = res.getString(res.getColumnIndex(FIBER));
            totalYards = Integer.parseInt(res.getString(res.getColumnIndex(YARD_PER_SKEIN)));
            totalSkeins = Integer.parseInt(res.getString(res.getColumnIndex(SKEINS_OWNED)));

            Yarn yarn = new Yarn(yarnName, colorWay, weight, fiber, totalYards, totalSkeins);
            stash.add(yarn);
            res.moveToNext();
        }

        res.close();
        db.close();
        return stash;
    }
}
