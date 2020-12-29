package com.example.testserver;

import android.os.Parcel;
import android.os.Parcelable;

public class Backpack implements Parcelable {


    protected Backpack(Parcel in) {
    }

    public static final Creator<Backpack> CREATOR = new Creator<Backpack>() {
        @Override
        public Backpack createFromParcel(Parcel in) {
            return new Backpack(in);
        }

        @Override
        public Backpack[] newArray(int size) {
            return new Backpack[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}