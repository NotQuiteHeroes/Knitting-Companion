package com.eckstein.paige.knittingcompanion.DatabaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eckstein.paige.knittingcompanion.Yarn.Yarn;

import java.util.ArrayList;

/**
 * Database helper for Stash
 */
public class StashDBHelper extends SQLiteOpenHelper {
    //Database information
    private static final String DATABASE_NAME = "KnittingCompanionStash.db";
    private static final String TABLE_NAME = "myStash";
    private static final String YARN_NAME = "yarn_name";
    private static final String COLORWAY = "colorWay";
    private static final String WEIGHT = "weight";
    private static final String FIBER = "fiber";
    private static final String YARD_PER_SKEIN = "yard_per_skein";
    private static final String SKEINS_OWNED = "skeins_owned";


    /**
     * Constructor
     * @param context Context of Activity database is being created in
     */
    public StashDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Create Stash table in database
     * @param db Database to create Stash table in
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                "(id integer primary key, yarn_name text, colorWay text, weight text, fiber text," +
                " yard_per_skein text, skeins_owned text)");
    }

    /**
     * On upgrade, delete old Table and re-create it
     * @param db Database to upgrade table in
     * @param oldVersion int: old version of table
     * @param newVersion int: new version of table
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * insert new yarn row in Stash table
     * @param yarnName String: name of yarn to add
     * @param colorway String: colorway of yarn to add
     * @param weight String: weight classification of yarn to add
     * @param fiber String: fiber content of yarn
     * @param totalYards String: conversion of int total yards per skein of yarn
     * @param totalSkeins String: conversion of int total skeins of yarn in stash
     * @return true upon completion of insert
     */
    public boolean insert(String yarnName, String colorway, String weight, String fiber,
                          String totalYards, String totalSkeins) {
        //writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //values holds row information of yarn
        ContentValues values = new ContentValues();

        //add yarn information to values
        values.put(YARN_NAME, yarnName);
        values.put(COLORWAY, colorway);
        values.put(WEIGHT, weight);
        values.put(FIBER, fiber);
        values.put(YARD_PER_SKEIN, totalYards);
        values.put(SKEINS_OWNED, totalSkeins);
        //insert row into Stash table
        db.insert(TABLE_NAME, null, values);
        db.close();
        return true;
    }

    /**
     * get row from stash table given yarn name and colorway
     * @param yarnName String: name of yarn to retrieve
     * @param colorWay String: colorway of yarn to retrieve
     * @return Cursor res holding row information from query
     */
    public Cursor getdata(String yarnName, String colorWay) {
        //readable database
        SQLiteDatabase db = this.getReadableDatabase();
        //get row where yarn name and colorway matched provided names
        Cursor res = db.rawQuery("select * from stash WHERE yarn_name = " + yarnName + " AND colorWay = " + colorWay + "", null);
        db.close();
        return res;
    }

    /**
     * get total number of rows in Stash table
     * @return int: total number of rows in Stash table
     */
    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = ((int) DatabaseUtils.queryNumEntries(db, TABLE_NAME));
        db.close();
        return numRows;
    }

    /**
     * update a row in the Stash table
     * @param yarnName String: name of yarn to update in stash
     * @param colorWay String: colorway of yarn
     * @param weight String: weight classification of yarn
     * @param fiber String: Fiber content of yarn
     * @param totalYards String: conversion of int total yards in skein
     * @param totalSkeins String: conversion of int total skeins in stash
     * @return
     */
    public boolean updateStash(String yarnName, String colorWay, String weight, String fiber,
                               String totalYards, String totalSkeins) {
        //writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //values holds row information
        ContentValues values = new ContentValues();

        //add updated values of row in values
        values.put(YARN_NAME, yarnName);
        values.put(COLORWAY, colorWay);
        values.put(WEIGHT, weight);
        values.put(FIBER, fiber);
        values.put(YARD_PER_SKEIN, totalYards);
        values.put(SKEINS_OWNED, totalSkeins);

        //update row where yarn name and colorway match provided names
        db.update(TABLE_NAME, values, "yarn_name = ? AND colorWay = ?", new String[]{yarnName, colorWay});
        db.close();
        return true;
    }

    /**
     * Delete row from Stash table
     * @param yarnName String: name of yarn to delete information
     * @param colorWay String: name of colorway
     * @return int: number of affected rows
     */
    public Integer deleteYarn(String yarnName, String colorWay) {
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(TABLE_NAME, "yarn_name = ? AND colorWay = ?", new String[]{yarnName, colorWay});
        db.close();
        return delete;
    }

    /**
     * get all rows from Stash table
     * create Yarn objects from each row's information
     * and add to ArrayList of Yarn objects
     * @return ArrayList of yarn objects created from all rows in Stash table
     */
    public ArrayList<Yarn> getAllYarn() {
        ArrayList<Yarn> stash = new ArrayList<>();
        //readable database
        SQLiteDatabase db = this.getReadableDatabase();
        //select all from Stash table
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + "", null);
        //move to first row
        res.moveToFirst();

        //Yarn fields
        String yarnName, colorWay, weight, fiber;
        int totalYards, totalSkeins;

        //while there are rows to continue with...
        while (!res.isAfterLast()) {
            //get individual yarn information
            yarnName = res.getString(res.getColumnIndex(YARN_NAME));
            colorWay = res.getString(res.getColumnIndex(COLORWAY));
            weight = res.getString(res.getColumnIndex(WEIGHT));
            fiber = res.getString(res.getColumnIndex(FIBER));
            totalYards = Integer.parseInt(res.getString(res.getColumnIndex(YARD_PER_SKEIN)));
            totalSkeins = Integer.parseInt(res.getString(res.getColumnIndex(SKEINS_OWNED)));

            //create Yarn object with retrieved information
            Yarn yarn = new Yarn(yarnName, colorWay, weight, fiber, totalYards, totalSkeins);
            //add to ArrayList
            stash.add(yarn);
            //move to next row
            res.moveToNext();
        }

        res.close();
        db.close();
        return stash;
    }
}
