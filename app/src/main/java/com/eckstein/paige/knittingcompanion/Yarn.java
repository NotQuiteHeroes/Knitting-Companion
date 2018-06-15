package com.eckstein.paige.knittingcompanion;

import android.os.Parcel;
import android.os.Parcelable;

public class Yarn implements Parcelable{

    private String name, colorway, weight, fiber;
    private int totalYards, totalSkeins;

    Yarn()
    {
        name = "";
        colorway = "";
        weight = "";
        fiber = "";
        totalYards = 0;
        totalSkeins = 0;
    }

    Yarn(String name, String colorway, String weight, String fiber, int totalYards, int totalSkeins)
    {
        this.name = name;
        this.colorway = colorway;
        this.weight = weight;
        this.fiber = fiber;
        this.totalYards = totalYards;
        this.totalSkeins = totalSkeins;
    }

    public String getName()
    {
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(colorway);
        dest.writeString(weight);
        dest.writeString(fiber);
        dest.writeInt(totalYards);
        dest.writeInt(totalSkeins);

    }

    private Yarn(Parcel in)
    {
        this.name = in.readString();
        this.colorway = in.readString();
        this.weight = in.readString();
        this.fiber = in.readString();
        this.totalYards = in.readInt();
        this.totalSkeins = in.readInt();
    }

    public static final Parcelable.Creator<Yarn> CREATOR = new Parcelable.Creator<Yarn>()
    {
        @Override
        public Yarn createFromParcel(Parcel source)
        {
            return new Yarn(source);
        }

        @Override
        public Yarn[] newArray(int size)
        {
            return new Yarn[size];
        }
    };
}
