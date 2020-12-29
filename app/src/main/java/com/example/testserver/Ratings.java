package com.example.testserver;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ratings implements Parcelable {
    private float avgStars;
    private int totalPoints;
    private int numberVotes;

    protected Ratings(Parcel in) {
        avgStars = in.readFloat();
        totalPoints = in.readInt();
        numberVotes = in.readInt();
    }

    public static final Creator<Ratings> CREATOR = new Creator<Ratings>() {
        @Override
        public Ratings createFromParcel(Parcel in) {
            return new Ratings(in);
        }

        @Override
        public Ratings[] newArray(int size) {
            return new Ratings[size];
        }
    };

    public float getAvgStars() {
        return avgStars;
    }

    public void setAvgStars(float avgStars) {
        this.avgStars = avgStars;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getNumberVotes() {
        return numberVotes;
    }

    public void setNumberVotes(int numberVotes) {
        this.numberVotes = numberVotes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(avgStars);
        dest.writeInt(totalPoints);
        dest.writeInt(numberVotes);
    }
}
