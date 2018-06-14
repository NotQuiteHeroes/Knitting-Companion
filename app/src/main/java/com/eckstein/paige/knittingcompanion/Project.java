package com.eckstein.paige.knittingcompanion;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Project implements Parcelable{
    private String startDate, endDate;
    private String patternName, yarnName, projectName;
    private int totalYardage, yardageUsed, totalSkeins;
    private String colorWay, note;
    private float size;
    private ArrayList<String> notes;
    private ArrayList<Counter> counters;

    //constructors
    Project()
    {
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
        counters = new ArrayList<>();
    }

    Project(String startDate, String patternName, String yarnName, String projectName, int totalYardage, String colorway, int size)
    {
        this.startDate = startDate;
        endDate = "--/--/--";
        this.patternName = patternName;
        this.yarnName = yarnName;
        this.projectName = "";
        this.totalYardage = totalYardage;
        yardageUsed = 0;
        this.colorWay = colorway;
        note = "";
        this.size = size;
        totalSkeins = 0;
        notes = new ArrayList<>();
        counters = new ArrayList<>();
    }

    //getters
    public String getStartDate()
    {
        return startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public String getPatternName()
    {
        return patternName;
    }

    public String getYarnName()
    {
        return yarnName;
    }

    public String getProjectName() { return projectName; }

    public String getColorWay()
    {
        return colorWay;
    }

    public int getTotalYardage()
    {
        return totalYardage;
    }

    public int getYardageUsed()
    {
        return yardageUsed;
    }

    public float getSize()
    {
        return size;
    }

    public ArrayList<Counter> getAllCounters() {
        return counters;
    }

    public ArrayList<String> getAllNotes() {
        return notes;
    }

    public Counter getCounter(int index)
    {
        return counters.get(index);
    }

    public String getNote(int index)
    {
        return notes.get(index);
    }

    public int getSkeins()
    {
        return totalSkeins;
    }

    //setters
    public void setStartDate(String newStart)
    {
        startDate = newStart;
    }

    public void setEndDate(String newEnd)
    {
        endDate = newEnd;
    }

    public void setPatternName(String pattern)
    {
        patternName = pattern;
    }

    public void setYarnName(String yarn)
    {
        yarnName = yarn;
    }

    public void setProjectName(String name) { projectName = name; }

    public void setTotalYardage(int yards)
    {
        totalYardage = yards;
    }

    public void setYardageUsed(int yards)
    {
        yardageUsed = yards;
    }

    public void setColorWay(String colorway)
    {
        colorWay = colorway;
    }

    public void setSize(float newSize)
    {
        size = newSize;
    }


    public void setNote(String note)
    {
        notes.add(note);
    }

    public void setCounters(Counter newCounter) {
        counters.add(newCounter);
    }

    public void setSkeins(int skeins)
    {
        totalSkeins = skeins;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
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
        dest.writeList(counters);

    }

    private Project(Parcel in)
    {
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
        this.counters = in.readArrayList(null);
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>()
    {
        @Override
        public Project createFromParcel(Parcel source)
        {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size)
        {
            return new Project[size];
        }
    };
}
