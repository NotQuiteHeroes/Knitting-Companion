package com.eckstein.paige.knittingcompanion.Yarn;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Yarn class holds instance of a single yarn
 * which includes a name, colorway, weight category, fiber,
 * total yards per skein, total skeins owned, id number,
 * if it is discontinued or not, weight in grams, its average
 * rating, wpi, yardage, and company na,e
 */
public class Yarn implements Parcelable {

    private String name, colorway, weight, fiber;
    private int totalYards, totalSkeins, id;
    private boolean discontinued;
    private int grams;
    private float rating_average;
    private int wpi;
    private int yardage;
    private String yarn_company_name;

    /**
     * Default constructor
     * set all values to empty
     */
    public Yarn() {
        id = 0;
        name = "";
        colorway = "";
        weight = "";
        fiber = "";
        totalYards = 0;
        totalSkeins = 0;
        discontinued = false;
        grams = 0;
        rating_average = 0;
        wpi = 0;
        yardage = 0;
        yarn_company_name = "";
    }

    /**
     * Constructor with common fields populated
     * @param name String: name of yarn
     * @param colorway String: name of colorway
     * @param weight String: weight category
     * @param fiber String: fiber content
     * @param totalYards int: total yards per skein
     * @param totalSkeins int: number of skeins owned
     */
    public Yarn(String name, String colorway, String weight, String fiber, int totalYards, int totalSkeins) {
        this.name = name;
        this.colorway = colorway;
        this.weight = weight;
        this.fiber = fiber;
        this.totalYards = totalYards;
        this.totalSkeins = totalSkeins;
        discontinued = false;
        grams = 0;
        rating_average = 0;
        wpi = 0;
        yardage = 0;
        yarn_company_name = "";
    }

    /**
     * Constructor for use with id
     * @param id int: yarn id (from Ravelry)
     * @param name String: yarn name
     * @param colorway String: yarn colorway
     * @param weight String: weight category
     * @param fiber String: fiber content
     * @param totalYards int: total yards per skein
     * @param totalSkeins int: total skeins owned
     */
    public Yarn(int id, String name, String colorway, String weight, String fiber, int totalYards, int totalSkeins) {
        this.id = id;
        this.name = name;
        this.colorway = colorway;
        this.weight = weight;
        this.fiber = fiber;
        this.totalYards = totalYards;
        this.totalSkeins = totalSkeins;
        discontinued = false;
        grams = 0;
        rating_average = 0;
        wpi = 0;
        yardage = 0;
        yarn_company_name = "";
    }

    /**
     * Constructor for Ravelry search results
     * @param discontinued boolean: yarn is discontinued or not
     * @param grams int: yarn weight in grams
     * @param id int: yarn id (from Ravelry)
     * @param name String: yarn name
     * @param rating_average float: yarn's average rating (from Ravelry)
     * @param wpi int: wpi (wraps per inch)
     * @param yardage int: total yardage per skein
     * @param yarn_company_name String: yarn company name
     */
    public Yarn(boolean discontinued, int grams, int id, String name, float rating_average, int wpi, int yardage, String yarn_company_name) {
        this.discontinued = discontinued;
        this.grams = grams;
        this.id = id;
        this.name = name;
        this.rating_average = rating_average;
        this.wpi = wpi;
        this.yardage = yardage;
        this.yarn_company_name = yarn_company_name;
    }

    /**
     * getter for yarn name
     * @return String: yarn name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for yarn colorway
     * @return String: yarn colorway
     */
    public String getColorway() {
        return colorway;
    }

    /**
     * getter for fiber content
     * @return String: fiber content of yarn
     */
    public String getFiber() {
        return fiber;
    }

    /**
     * getter for weight category
     * @return String: weight category (Bulky, worsted, etc.)
     */
    public String getWeight() {
        return weight;
    }

    /**
     * getter for total skeins owned
     * @return int: total skeins owned
     */
    public int getTotalSkeins() {
        return totalSkeins;
    }

    /**
     * getter for total yards per skein
     * @return int: total yards per skein
     */
    public int getTotalYards() {
        return totalYards;
    }

    /**
     * getter for yarn id
     * @return int: id (from ravelry)
     */
    public int getId() {
        return id;
    }

    /**
     * getter for is discontinued or not
     * @return boolean: true if discontinued, false otherwise
     */
    public boolean getDiscontinued() {
        return discontinued;
    }

    /**
     * getter for weight in grams
     * @return int: weight of yarn in grams
     */
    public int getGrams() {
        return grams;
    }

    /**
     * getter for average rating (from Ravelry)
     * @return float: yarn's average rating
     */
    public float getRating_average() {
        return rating_average;
    }

    /**
     * getter for wpi
     * @return int: wraps per inch of yarn
     */
    public int getWpi() {
        return wpi;
    }

    /**
     * getter for yardage
     * @return int: total yards per skein
     */
    public int getYardage() {
        return yardage;
    }

    /**
     * getter for company name
     * @return String: yarn company name
     */
    public String getCompanyName() {
        return yarn_company_name;
    }

    /**
     * setter for yarn name
     * @param name String: name of yarn
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for colorway
     * @param colorway String: colorway of yarn
     */
    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    /**
     * setter for fiber
     * @param fiber String: fiber content of yarn
     */
    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    /**
     * setter for weight category
     * @param weight String: weight category of yarn (ex: bulky, worsted, aran, etc.)
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * setter for total skeins
     * @param totalSkeins int: total number of skeins owned
     */
    public void setTotalSkeins(int totalSkeins) {
        this.totalSkeins = totalSkeins;
    }

    /**
     * setter for total yards
     * @param totalYards int: number of yards per skein
     */
    public void setTotalYards(int totalYards) {
        this.totalYards = totalYards;
    }

    /**
     * setter for id
     * @param id int: yarn id (from Ravelry)
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setter for discontinued
     * @param discontinued boolean: is discontinued
     */
    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    /**
     * setter for grams
     * @param grams int: yarn weight in grams
     */
    public void setGrams(int grams) {
        this.grams = grams;
    }

    /**
     * setter for average rating
     * @param rating_average float: average rating of yarn (from ravelry)
     */
    public void setRating_average(float rating_average) {
        this.rating_average = rating_average;
    }

    /**
     * setter for wpi
     * @param wpi int wraps per inch
     */
    public void setWpi(int wpi) {
        this.wpi = wpi;
    }

    /**
     * setter for yardage
     * @param yardage int: total yards per skein
     */
    public void setYardage(int yardage) {
        this.yardage = yardage;
    }

    /**
     * setter for company name
     * @param yarn_company_name String: yarn company name (Red Heart, Berroco, etc.)
     */
    public void setCompanyName(String yarn_company_name) {
        this.yarn_company_name = yarn_company_name;
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
     * How to save information - for parcelable
     * @param dest Parcel to save information to
     * @param flags for parcelable
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(colorway);
        dest.writeString(weight);
        dest.writeString(fiber);
        dest.writeInt(totalYards);
        dest.writeInt(totalSkeins);
        dest.writeInt(discontinued ? 1 : 0);
        dest.writeInt(grams);
        dest.writeFloat(rating_average);
        dest.writeInt(wpi);
        dest.writeInt(yardage);
        dest.writeString(yarn_company_name);

    }

    /**
     * Constructor used by parcelable
     * information must be read out in same order it was written in
     * @param in Parcel to read from
     */
    private Yarn(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.colorway = in.readString();
        this.weight = in.readString();
        this.fiber = in.readString();
        this.totalYards = in.readInt();
        this.totalSkeins = in.readInt();
        this.discontinued = in.readInt() != 0;
        this.grams = in.readInt();
        this.rating_average = in.readFloat();
        this.wpi = in.readInt();
        this.yardage = in.readInt();
        this.yarn_company_name = in.readString();
    }

    /**
     * for parcelable
     */
    public static final Parcelable.Creator<Yarn> CREATOR = new Parcelable.Creator<Yarn>() {
        @Override
        public Yarn createFromParcel(Parcel source) {
            return new Yarn(source);
        }

        @Override
        public Yarn[] newArray(int size) {
            return new Yarn[size];
        }
    };
}
