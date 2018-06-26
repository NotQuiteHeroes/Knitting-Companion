package com.eckstein.paige.knittingcompanion.Projects;

import android.os.Parcel;
import android.os.Parcelable;

import com.eckstein.paige.knittingcompanion.Yarn.Yarn;

import java.util.ArrayList;

/**
 * Project class holds instance of a single project
 * which includes a project name, pattern name, start and end dates,
 * total yards per skein, total yards used, total skeins used,
 * yarn colorway, notes and size made
 */
public class Project implements Parcelable {
    private String startDate, endDate;
    private String patternName, projectName;
    private int yardageUsed;
    private String note;
    private float size;
    private ArrayList<String> notes;
    private Yarn yarn;
    private ArrayList<Yarn> allYarns;
    private Needle needle;
    private ArrayList<Needle> allNeedles;

    /**
     * Default constructor - set all values to empty
     */
    public Project() {
        startDate = "--/--/--";
        endDate = "--/--/--";
        patternName = "";
        projectName = "";
        yardageUsed = 0;
        note = "";
        size = 0;
        notes = new ArrayList<>();
        yarn = new Yarn();
        allYarns = new ArrayList<>();
        needle = new Needle();
        allNeedles = new ArrayList<>();
    }

    /**
     * Constructor given all information
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
     * @param weight string: yarn weight category
     * @param fiber string: yarn fiber content
     * @param needleSize float: size of needle
     * @param needleSizeType string: type of sizing (US or Metric)
     * @param needleType string: type of needles used (straight, dpn, circular, etc.)
     * @param needleLength string: lenght of needle (either 30", etc, for circular, or standard, sock, etc.)
     */
    public Project(String startDate, String endDate, String patternName, String projectName,
            String yarnName, int totalYardage, int yardageUsed, String colorway, String note,
            float size, int totalSkeins, String weight, String fiber, float needleSize, String
                   needleSizeType, String needleType, String needleLength) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.patternName = patternName;
        this.projectName = projectName;
        this.yardageUsed = yardageUsed;
        this.note = note;
        this.size = size;
        notes = new ArrayList<>();
        yarn = new Yarn(yarnName, colorway, weight, fiber, totalYardage, totalSkeins);
        allYarns = new ArrayList<>();
        allYarns.add(yarn);
        needle = new Needle(needleSize, needleSizeType, needleType, needleLength);
        allNeedles = new ArrayList<>();
        allNeedles.add(needle);
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
        return yarn.getName();
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
    public String getColorWay(int index) 
    {
        if(index == -1)
        {
        return yarn.getColorway();
        }
        return(allYarns.get(index).getColorway());
    }

    /**
     * getter for total yardage
     * @return int: total yards per skein
     */
    public int getTotalYardage(int index)
    {
        if(index == -1) {
            return yarn.getTotalYards();
        }
        return allYarns.get(index).getTotalYards();
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
    public int getSkeins(int index) {
        if(index == -1)
        {
            return yarn.getTotalSkeins();
        }
        return allYarns.get(index).getTotalSkeins();
    }

    /**
     * getter for yarn weight category
     * @return yarn weight category (worsted, aran, etc.)
     */
    public String getWeight(int index)
    {
        if(index == -1) {
            return yarn.getWeight();
        }
        return allYarns.get(index).getWeight();
    }

    /**
     * getter for yarn fiber content
     * @return fiber content for yarn
     */
    public String getFiber(int index)
    {
        if(index == -1) {
            return yarn.getFiber();
        }
        return allYarns.get(index).getFiber();
    }

    /**
     * getter for needle size
     * @return float: size of needle used for project
     */
    public float getNeedleSize(int index)
    {
        if(index == -1) {
            return needle.getSize();
        }
        return allNeedles.get(index).getSize();
    }

    /**
     * getter for needle size type
     * @return String: sizing style for needles (US or Metric)
     */
    public String getNeedleSizeType(int index)
    {
        if(index == -1) {
            return needle.getSizeType();
        }
        return allNeedles.get(index).getSizeType();
    }

    public String getNeedleType(int index)
    {
        if(index == -1)
        {
            return needle.getType();
        }
        return allNeedles.get(index).getType();
    }

    public String getNeedleLength(int index)
    {
        if(index == -1)
        {
            return needle.getlength();
        }
        return allNeedles.get(index).getlength();
    }

    public int getNumberOfYarns()
    {
        return allYarns.size();
    }

    public int getNumberOfNeedles()
    {
        return allNeedles.size();
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
     * @param name String: name of yarn used in project
     */
    public void setYarnName(String name, int index) {
        if(index == -1) {
            yarn.setName(name);
        }
        else
        {
            allYarns.get(index).setName(name);
        }
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
    public void setTotalYardage(int yards, int index) {
        if(index == -1) {
            yarn.setTotalYards(yards);
        }
        else
        {
            allYarns.get(index).setTotalYards(yards);
        }
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
    public void setColorWay(String colorway, int index) {
        if(index == -1) {
            yarn.setColorway(colorway);
        }
        else
        {
            allYarns.get(index).setColorway(colorway);
        }
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
    public void setSkeins(int skeins, int index) {
        if(index == -1) {
            yarn.setTotalSkeins(skeins);
        }
        else
        {
            allYarns.get(index).setTotalSkeins(skeins);
        }
    }

    /**
     * setter for yarn weight category
     * @param weight String: yarn weight category (Aran, worsted, etc.)
     */
    public void setWeight(String weight, int index)
    {
        if(index == -1) {
            yarn.setWeight(weight);
        }
        else
        {
            allYarns.get(index).setWeight(weight);
        }
    }

    /**
     * setter for yarn fiber content
     * @param fiber String: fiber content of yarn
     */
    public void setFiber(String fiber, int index)
    {
        if(index == -1) {
            yarn.setFiber(fiber);
        }
        else
        {
            allYarns.get(index).setFiber(fiber);
        }
    }

    /**
     * setter for needle size
     * @param size float: size of needle used for project
     */
    public void setNeedleSize(float size, int index)
    {
        if(index == -1) {
            needle.setSize(size);
        }
        else
        {
            allNeedles.get(index).setSize(size);
        }
    }

    /**
     * setter for needle size type
     * @param type String: style of sizing (US or Metric)
     */
    public void setNeedleSizeType(String type, int index)
    {
        if(index == -1) {
            needle.setSizeType(type);
        }
        else
        {
            allNeedles.get(index).setSizeType(type);
        }
    }

    public void setNeedleType(String type, int index)
    {
        if(index == -1)
        {
            needle.setType(type);
        }
        else
        {
            allNeedles.get(index).setType(type);
        }
    }

    public void setNeedleLength(String length, int index)
    {
        if(index == -1)
        {
            needle.setlength(length);
        }
        else
        {
            allNeedles.get(index).setlength(length);
        }
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
        dest.writeString(yarn.getName());
        dest.writeString(projectName);
        dest.writeInt(yarn.getTotalYards());
        dest.writeInt(yardageUsed);
        dest.writeString(yarn.getColorway());
        dest.writeFloat(size);
        dest.writeList(notes);
        dest.writeString(yarn.getWeight());
        dest.writeString(yarn.getFiber());
        dest.writeList(allYarns);
        dest.writeFloat(needle.getSize());
        dest.writeString(needle.getSizeType());
        dest.writeString(needle.getType());
        dest.writeString(needle.getlength());
        dest.writeList(allNeedles);

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
        yarn.setName(in.readString());
        this.projectName = in.readString();
        yarn.setTotalYards(in.readInt());
        this.yardageUsed = in.readInt();
        yarn.setColorway(in.readString());
        this.size = in.readFloat();
        this.notes = in.readArrayList(null);
        yarn.setWeight(in.readString());
        yarn.setFiber(in.readString());
        this.allYarns = in.readArrayList(null);
        needle.setSize(in.readFloat());
        needle.setSizeType(in.readString());
        needle.setType(in.readString());
        needle.setlength(in.readString());
        this.allNeedles = in.readArrayList(null);
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
