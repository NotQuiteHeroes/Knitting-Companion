package com.eckstein.paige.knittingcompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "KnittingCompanion.db";
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

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME +
        "(id integer primary key, project_name text, pattern_name text, yarn_name text, start_date text," +
                " end_date text, total_yards text, yards_used text, skeins text, colorway text, note " +
                "text, size text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String projectName, String patternName, String yarnName, String start,
                          String end, String totalYards, String yardsUsed, String skeins, String
                                  colorway, String note, String size)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
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
        db.insert(TABLE_NAME, null, values);
        db.close();
        return true;
    }

    public Cursor getdata(String projectName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where project_name="+projectName+"", null);
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

    public boolean updateProject(String projectName, String patternName, String yarnName, String start,
                                 String end, String totalYards, String yardsUsed, String skeins, String
                                         colorway, String note, String size)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
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
        db.update(TABLE_NAME, values, "project_name = ? ", new String[] {projectName});
        db.close();
        return true;
    }

    public Integer deleteProject (String projectName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(TABLE_NAME, "project_name = ? ", new String[] { projectName });
        db.close();
        return delete;
    }

    public ArrayList<Project> getAllProjects()
    {
        ArrayList<Project> projects = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + "", null);
        res.moveToFirst();

        String patternName, projectName, yarnName;
        String startDate, endDate;
        int totalYards, yardsUsed, totalSkeins;
        String colorway, note;
        float size;

        while(!res.isAfterLast())
        {
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
            Project project = new Project(startDate, endDate, patternName, projectName,
                    yarnName, totalYards, yardsUsed, colorway, note, size, totalSkeins);
            projects.add(project);
            res.moveToNext();
        }

        res.close();
        db.close();
        return projects;
    }
}
