package com.example.testserver.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Example implements Parcelable {
    private int lastUpdate;
    private String lanuage;
    private List<Datum> data = null;


    protected Example(Parcel in) {
        lastUpdate = in.readInt();
        lanuage = in.readString();
        data = in.createTypedArrayList(Datum.CREATOR);
    }

    public static final Creator<Example> CREATOR = new Creator<Example>() {
        @Override
        public Example createFromParcel(Parcel in) {
            return new Example(in);
        }

        @Override
        public Example[] newArray(int size) {
            return new Example[size];
        }
    };

    public String getLanuage() {
        return lanuage;
    }

    public void setLanuage(String lanuage) {
        this.lanuage = lanuage;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(lastUpdate);
        dest.writeString(lanuage);
        dest.writeTypedList(data);
    }

    public int getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
