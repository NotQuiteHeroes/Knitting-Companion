package com.eckstein.paige.knittingcompanion.Yarn;

import android.os.Parcel;
import android.os.Parcelable;

public class Yarn implements Parcelable {

    private String name, colorway, weight, fiber;
    private int totalYards, totalSkeins, id;
    private boolean discontinued;
    private int grams;
    private float rating_average;
    private int wpi;
    private int yardage;
    private String yarn_company_name;

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

    public String getName() {
        return name;
    }

    public String getColorway() {
        return colorway;
    }

    public String getFiber() {
        return fiber;
    }

    public String getWeight() {
        return weight;
    }

    public int getTotalSkeins() {
        return totalSkeins;
    }

    public int getTotalYards() {
        return totalYards;
    }

    public int getId() {
        return id;
    }

    public boolean getDiscontinued() {
        return discontinued;
    }

    public int getGrams() {
        return grams;
    }

    public float getRating_average() {
        return rating_average;
    }

    public int getWpi() {
        return wpi;
    }

    public int getYardage() {
        return yardage;
    }

    public String getCompanyName() {
        return yarn_company_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setTotalSkeins(int totalSkeins) {
        this.totalSkeins = totalSkeins;
    }

    public void setTotalYards(int totalYards) {
        this.totalYards = totalYards;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public void setRating_average(float rating_average) {
        this.rating_average = rating_average;
    }

    public void setWpi(int wpi) {
        this.wpi = wpi;
    }

    public void setYardage(int yardage) {
        this.yardage = yardage;
    }

    public void setCompanyName(String yarn_company_name) {
        this.yarn_company_name = yarn_company_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
