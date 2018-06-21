package com.eckstein.paige.knittingcompanion.DatabaseHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eckstein.paige.knittingcompanion.Projects.Project;

import java.util.ArrayList;

/**
 * Database helper for Projects
 */
public class ProjectDBHelper extends SQLiteOpenHelper {

    //Database information
    private static final String DATABASE_NAME = "KnittingCompanionProject.db";
    private static final String TABLE_NAME = "projects";
    private static final String PROJECT_NAME = "project_name";
    private static final String PATTERN_NAME = "pattern_name";
    private static final String YARN_NAME = "yarn_name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String TOTAL_YARDS = "total_yards";
    private static final String YARDS_USED = "yards_used";
    private static final String TOTAL_SKEINS = "skeins";
    private static final String COLORWAY = "colorway";
    private static final String NOTE = "note";
    private static final String SIZE = "size";

    /**
     * Constructor - requires Context of calling Activity
     * @param context Context of Activity database is being created in
     */
    public ProjectDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Create table in database
     * @param db Database to add table to
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                "(id integer primary key, project_name text, pattern_name text, yarn_name text, start_date text," +
                " end_date text, total_yards text, yards_used text, skeins text, colorway text, note " +
                "text, size text)");
    }

    /**
     * On upgrade, delete existing Table and re-create
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
     * Insert new row in Projects table
     * @param projectName String: name of project
     * @param patternName String: name of pattern
     * @param yarnName String: yarn name
     * @param start: String: start date mm/dd/yy
     * @param end String: end date mm/dd/yy
     * @param totalYards String: total yards per skein
     * @param yardsUsed String: yards used in project
     * @param skeins String: number of skeins of yarn
     * @param colorway String: yarn colorway name
     * @param note  String: Project notes
     * @param size  String: string conversion of float size
     * @return
     */
    public boolean insert(String projectName, String patternName, String yarnName, String start,
                          String end, String totalYards, String yardsUsed, String skeins, String
                                  colorway, String note, String size) {
        //writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //values object to add row information to
        ContentValues values = new ContentValues();

        //add project information to values
        values.put(PROJECT_NAME, projectName);
        values.put(PATTERN_NAME, patternName);
        values.put(YARN_NAME, yarnName);
        values.put(START_DATE, start);
        values.put(END_DATE, end);
        values.put(TOTAL_YARDS, totalYards);
        values.put(YARDS_USED, yardsUsed);
        values.put(TOTAL_SKEINS, skeins);
        values.put(COLORWAY, colorway);
        values.put(NOTE, note);
        values.put(SIZE, size);
        //insert row into table
        db.insert(TABLE_NAME, null, values);
        db.close();
        return true;
    }

    /**
     * Get row from Project table
     * @param projectName String: name of the project to pull information for
     * @return Cursor res holding query result
     */
    public Cursor getdata(String projectName) {
        //readable database
        SQLiteDatabase db = this.getReadableDatabase();
        //query to get all information on rows with project name matching provided name
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where project_name=" + projectName + "", null);
        db.close();
        return res;
    }

    /**
     * get total number of rows in Project table
     * @return int: total number of rows in Project table
     */
    public int numberOfRows() {
        //readable database
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = ((int) DatabaseUtils.queryNumEntries(db, TABLE_NAME));
        db.close();
        return numRows;
    }

    /**
     * Update row in Project table
     * @param projectName String: name of project to update
     * @param patternName String: pattern name of project to update
     * @param yarnName String: yarn name of project to update
     * @param start String: start date of project to update mm/dd/yy
     * @param end String: end date of project to update mm/dd/yy
     * @param totalYards String: conversion of int total yards in skein
     * @param yardsUsed String: conversion of int total yards used in project
     * @param skeins String: total number of skeins used in project to update
     * @param colorway String: colorway name of project
     * @param note String: note of project to update
     * @param size String: conversion of float size of project
     * @return
     */
    public boolean updateProject(String projectName, String patternName, String yarnName, String start,
                                 String end, String totalYards, String yardsUsed, String skeins, String
                                         colorway, String note, String size) {
        //writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //hold row information
        ContentValues values = new ContentValues();

        //add updated project information to values
        values.put(PROJECT_NAME, projectName);
        values.put(PATTERN_NAME, patternName);
        values.put(YARN_NAME, yarnName);
        values.put(START_DATE, start);
        values.put(END_DATE, end);
        values.put(TOTAL_YARDS, totalYards);
        values.put(YARDS_USED, yardsUsed);
        values.put(TOTAL_SKEINS, skeins);
        values.put(COLORWAY, colorway);
        values.put(NOTE, note);
        values.put(SIZE, size);
        //update row where project name matches provided project name
        db.update(TABLE_NAME, values, "project_name = ? ", new String[]{projectName});
        db.close();
        return true;
    }

    /**
     * delete row in table where project name matches provided name
     * @param projectName String: name of the project to delete
     * @return int: number of rows affected
     */
    public Integer deleteProject(String projectName) {
        //writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //delete row where project name matches provided name
        int delete = db.delete(TABLE_NAME, "project_name = ? ", new String[]{projectName});
        db.close();
        return delete;
    }

    /**
     * get all rows from Project table
     * create Project objects from each row
     * add to ArrayList of Project objects
     * @return ArrayList of all Project objects created from Project table rows
     */
    public ArrayList<Project> getAllProjects() {
        //arraylist to hold all Projects created from rows in Project table
        ArrayList<Project> projects = new ArrayList<>();
        //readable database
        SQLiteDatabase db = this.getReadableDatabase();
        //get all rows from Project table
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + "", null);
        //move to first row
        res.moveToFirst();

        //Project fields
        String patternName, projectName, yarnName;
        String startDate, endDate;
        int totalYards, yardsUsed, totalSkeins;
        String colorway, note;
        float size;

        //while there are rows to continue with...
        while (!res.isAfterLast()) {
            //get each column field in row
            patternName = res.getString(res.getColumnIndex(PATTERN_NAME));
            projectName = res.getString(res.getColumnIndex(PROJECT_NAME));
            yarnName = res.getString(res.getColumnIndex(YARN_NAME));
            startDate = res.getString(res.getColumnIndex(START_DATE));
            endDate = res.getString(res.getColumnIndex(END_DATE));
            totalYards = Integer.parseInt(res.getString(res.getColumnIndex(TOTAL_YARDS)));
            yardsUsed = Integer.parseInt(res.getString(res.getColumnIndex(YARDS_USED)));
            totalSkeins = Integer.parseInt(res.getString(res.getColumnIndex(TOTAL_SKEINS)));
            colorway = res.getString(res.getColumnIndex(COLORWAY));
            note = res.getString(res.getColumnIndex(NOTE));
            size = Float.parseFloat(res.getString(res.getColumnIndex(SIZE)));

            //create Project object with row information
            Project project = new Project(startDate, endDate, patternName, projectName,
                    yarnName, totalYards, yardsUsed, colorway, note, size, totalSkeins);
            //add Project objected to ArrayList
            projects.add(project);
            //move to next row
            res.moveToNext();
        }

        res.close();
        db.close();
        return projects;
    }
}
