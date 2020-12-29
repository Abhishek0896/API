package com.example.testserver.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SurveyCity implements Parcelable {
    private int id;
    private String name;

    protected SurveyCity(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<SurveyCity> CREATOR = new Creator<SurveyCity>() {
        @Override
        public SurveyCity createFromParcel(Parcel in) {
            return new SurveyCity(in);
        }

        @Override
        public SurveyCity[] newArray(int size) {
            return new SurveyCity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}

