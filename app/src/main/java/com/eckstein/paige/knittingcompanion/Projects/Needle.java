package com.eckstein.paige.knittingcompanion.Projects;

import android.os.Parcel;
import android.os.Parcelable;

public class Needle implements Parcelable {

    private float size;
    private String sizeType;
    private String type;
    private String length;

    Needle()
    {
        size = 0;
        sizeType = "US";
        type = "Straight";
        length = "standard";
    }

    Needle(float size, String sizeType, String type, String length)
    {
        this.size = size;
        this.sizeType = sizeType;
        this.type = type;
        this.length = length;
    }

    public float getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getSizeType() {
        return sizeType;
    }

    public String getlength() {
        return length;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public void setlength(String length) {
        this.length = length;
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
        dest.writeFloat(size);
        dest.writeString(sizeType);
        dest.writeString(type);
        dest.writeString(length);

    }

    /**
     * Constructor used by parcelable
     * order read in must match order written out
     * @param in Parcel to read in from
     */
    private Needle(Parcel in) {
        this.size = in.readFloat();
        this.sizeType = in.readString();
        this.type = in.readString();
        this.length = in.readString();
    }

    /**
     * used by parcelable
     */
    public static final Parcelable.Creator<Needle> CREATOR = new Parcelable.Creator<Needle>() {
        @Override
        public Needle createFromParcel(Parcel source) {
            return new Needle(source);
        }

        @Override
        public Needle[] newArray(int size) {
            return new Needle[size];
        }
    };
}
