package com.eckstein.paige.knittingcompanion.Projects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Project class holds instance of a single project
 * which includes a project name, pattern name, start and end dates,
 * total yards per skein, total yards used, total skeins used,
 * yarn colorway, notes and size made
 */
public class Project implements Parcelable {
    private String startDate, endDate;
    private String patternName, yarnName, projectName;
    private int totalYardage, yardageUsed, totalSkeins;
    private String colorWay, note;
    private float size;
    private ArrayList<String> notes;

    /**
     * Default constructor - set all values to empty
     */
    public Project() {
        startDate = "--/--/--";
        endDate = "--/--/--";
        patternName = "";
        yarnName = "";
        projectName = "";
        totalYardage = 0;
        yardageUsed = 0;
        colorWay = "";
        note = "";
        size = 0;
        totalSkeins = 0;
        notes = new ArrayList<>();
    }

    /**
     * Constructor given common information
     * @param startDate String: start date of project mm/dd/yy
     * @param endDate String: end date of project mm/dd/yy
     * @param patternName String: pattern name
     * @param projectName String: project name
     * @param yarnName String: name of yarn
     * @param totalYardage int: total yards per skein
     * @param yardageUsed int: total yards used in project
     * @param colorway String: colorway of yarn used
     * @param note String: project note
     * @param size float: size made
     * @param totalSkeins int: total number of skeins used
     */
    public Project(String startDate, String endDate, String patternName, String projectName,
            String yarnName, int totalYardage, int yardageUsed, String colorway, String note,
            float size, int totalSkeins) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.patternName = patternName;
        this.projectName = projectName;
        this.yarnName = yarnName;
        this.totalYardage = totalYardage;
        this.yardageUsed = yardageUsed;
        this.colorWay = colorway;
        this.note = note;
        this.size = size;
        this.totalSkeins = totalSkeins;
        notes = new ArrayList<>();
    }

    /**
     * getter for start date
     * @return String: project start date mm/dd/yy
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * getter for end date
     * @return String: project end date mm/dd/yy
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * getter for pattern name
     * @return String: pattern name used in project
     */
    public String getPatternName() {
        return patternName;
    }

    /**
     * getter for yarn name
     * @return String: name of yarn used in project
     */
    public String getYarnName() {
        return yarnName;
    }

    /**
     * getter for project name
     * @return String: name of project
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * getter for color way
     * @return String: colorway of yarn used in project
     */
    public String getColorWay() {
        return colorWay;
    }

    /**
     * getter for total yardage
     * @return int: total yards per skein
     */
    public int getTotalYardage() {
        return totalYardage;
    }

    /**
     * getter for yardage used
     * @return int: total number of yards used in project
     */
    public int getYardageUsed() {
        return yardageUsed;
    }

    /**
     * getter for size
     * @return float: size made
     */
    public float getSize() {
        return size;
    }

    /**
     * get all notes for a project
     * @return ArrayList of individual notes
     */
    public ArrayList<String> getAllNotes() {
        return notes;
    }


    /**
     * get single note for project
     * @param index int: position of note to retrieve
     * @return String: individual note for project
     */
    public String getNote(int index) {
        if (notes.size() > 0) {
            return notes.get(index);
        } else {
            return "";
        }
    }

    /**
     * getter for skeins
     * @return int: total number of skeins used in project
     */
    public int getSkeins() {
        return totalSkeins;
    }

    /**
     * setter for start date
     * @param newStart String: project start date mm/dd/yy
     */
    public void setStartDate(String newStart) {
        startDate = newStart;
    }

    /**
     * setter for end date
     * @param newEnd String: project end date mm/dd/yy
     */
    public void setEndDate(String newEnd) {
        endDate = newEnd;
    }

    /**
     * setter for pattern name
     * @param pattern String: name of pattern used in project
     */
    public void setPatternName(String pattern) {
        patternName = pattern;
    }

    /**
     * setter for yarn name
     * @param yarn String: name of yarn used in project
     */
    public void setYarnName(String yarn) {
        yarnName = yarn;
    }

    /**
     * setter for project name
     * @param name String: name of project
     */
    public void setProjectName(String name) {
        projectName = name;
    }

    /**
     * setter for total yardage
     * @param yards int: total yards per skein
     */
    public void setTotalYardage(int yards) {
        totalYardage = yards;
    }

    /**
     * setter for yardage used
     * @param yards int: total yards used in project
     */
    public void setYardageUsed(int yards) {
        yardageUsed = yards;
    }

    /**
     * setter for color way
     * @param colorway String: colorway of yarn used in project
     */
    public void setColorWay(String colorway) {
        colorWay = colorway;
    }

    /**
     * setter for size
     * @param newSize float: size made
     */
    public void setSize(float newSize) {
        size = newSize;
    }

    /**
     * setter for note
     * @param note String: note for project
     */
    public void setNote(String note) {
        notes.add(note);
    }

    /**
     * setter for skeins
     * @param skeins int: total number of skeins used in project
     */
    public void setSkeins(int skeins) {
        totalSkeins = skeins;
    }

    /**
     * for parcelable
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * How to save information - used by paraceable
     * @param dest Parcel to write information to
     * @param flags required by parcelable
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(patternName);
        dest.writeString(yarnName);
        dest.writeString(projectName);
        dest.writeInt(totalYardage);
        dest.writeInt(yardageUsed);
        dest.writeString(colorWay);
        dest.writeFloat(size);
        dest.writeList(notes);

    }

    /**
     * Constructor used by parcelable
     * order read in must match order written out
     * @param in Parcel to read in from
     */
    private Project(Parcel in) {
        this.startDate = in.readString();
        this.endDate = in.readString();
        this.patternName = in.readString();
        this.yarnName = in.readString();
        this.projectName = in.readString();
        this.totalYardage = in.readInt();
        this.yardageUsed = in.readInt();
        this.colorWay = in.readString();
        this.size = in.readFloat();
        this.notes = in.readArrayList(null);
    }

    /**
     * used by parcelable
     */
    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
}
