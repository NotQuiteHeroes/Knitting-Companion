package com.eckstein.paige.knittingcompanion.Counters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Counter class holds instance of a single counter
 * which has three values to form a knitting counter - 000
 * Each counter needs a project name for association
 */
public class Counter implements Parcelable {

    private int ones, tens, hundreds;
    private String name, projectName;

    /**
     * Constructor that only takes project name
     * @param projectName String name of project counter is being used for
     */
    public Counter(String projectName) {
        name = "";
        this.projectName = projectName;
        ones = 0;
        tens = 0;
        hundreds = 0;
    }

    /**
     * Constructor that takes project name as well as counter name
     * @param projectName String name of project counter is being used for
     * @param name String name of counter (ex: rows, repeats, etc.)
     */
    public Counter(String projectName, String name) {
        this.projectName = projectName;
        this.name = name;
        ones = 0;
        tens = 0;
        hundreds = 0;
    }

    /**
     * Constructor that takes project name, counter name, and int values for each
     * counter position - 000
     * @param name String name of counter (ex: rows, repeats, etc.)
     * @param projectName String name of project counter is being used for
     * @param ones int ones position value (001)
     * @param tens int tens position value (010)
     * @param hundreds int hundreds position value (100)
     */
    public Counter(String name, String projectName, int ones, int tens, int hundreds) {
        this.name = name;
        this.projectName = projectName;
        this.ones = ones;
        this.tens = tens;
        this.hundreds = hundreds;
    }

    /**
     * getter for counter name
     * @return String counter name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for project name
     * @return String project name counter is being used for
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * setter for counter name
     * @param newName String new counter name
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * setter for project name
     * @param newProject String project name that counter is being used for
     */
    public void setProjectName(String newProject) {
        projectName = newProject;
    }

    /**
     * getter for one's position of counter
     * @return int value in one's position of counter
     */
    public int getOnes() {
        return ones;
    }

    /**
     * getter for ten's position of counter
     * @return int value in ten's position of counter
     */
    public int getTens() {
        return tens;
    }

    /**
     * getter for hundred's position of counter
     * @return int value in hundred's position of counter
     */
    public int getHundreds() {
        return hundreds;
    }

    /**
     * increment the counter
     * because counter is made up of three unrelated values, must make sure incrementation is
     * valid
     */
    public void increment() {
        //if ones position is 0-8...
        if (ones < 9) {
            //add one
            ones++;
        //if ones position is 9...
        } else {
            //reset ones position to 0
            ones = 0;
            //if tens position is 0-8...
            if (tens < 9) {
                //add one
                tens++;
            //if tens position is 9...
            } else {
                //reset tens position to 0
                tens = 0;
                //if hundreds position is 0-8...
                if (hundreds < 9) {
                    //add one
                    hundreds++;
                 //if hundrends position is 9...
                } else {
                    //counter = 999, reset all to 0
                    hundreds = 0;
                }
            }
        }

    }

    /**
     * decrement the counter
     * because counter is made up of three unrelated values, must make sure decrementation
     * is valid
     */
    public void decrement() {
        //if ones is 1-9...
        if (ones >= 0) {
            //subtract from ones
            ones--;
        //if ones is 0...
        } else {
            //roll back ones to 9
            ones = 9;
            //if tens is 1-9...
            if (tens >= 0) {
                //subtract one
                tens--;
             //if tens is 0...
            } else {
                //roll back tens to 9
                tens = 9;
                //if hundreds is 1-9...
                if (hundreds >= 0) {
                    //subtract one
                    hundreds--;
                 //counted all the way down, reset all to 000
                } else {
                    ones = 0;
                    tens = 0;
                    hundreds = 0;
                }
            }
        }
    }

    /**
     * clear the counter to 000
     */
    public void reset() {
        ones = 0;
        tens = 0;
        hundreds = 0;
    }

    /**
     * Describe Contents required by Parcelable
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * How to save information - used by parcelable
     * @param dest Parcel to write to
     * @param flags required by parcelable
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(projectName);
        dest.writeString(name);
        dest.writeInt(ones);
        dest.writeInt(tens);
        dest.writeInt(hundreds);
    }

    /**
     * Constructor used by parcelable
     * must read in same order written out
     * @param in Parcel to create Counter object from
     */
    private Counter(Parcel in) {
        this.projectName = in.readString();
        this.name = in.readString();
        this.ones = in.readInt();
        this.tens = in.readInt();
        this.hundreds = in.readInt();
    }

    /**
     * Required by parcelable
     */
    public static final Parcelable.Creator<Counter> CREATOR = new Parcelable.Creator<Counter>() {
        @Override
        public Counter createFromParcel(Parcel source) {
            return new Counter(source);
        }

        @Override
        public Counter[] newArray(int size) {
            return new Counter[size];
        }
    };
}
