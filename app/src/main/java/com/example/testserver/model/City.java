package com.example.testserver.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class City implements Parcelable {
    private List<SurveyCity> surveyCities = null;

    protected City(Parcel in) {
        surveyCities = in.createTypedArrayList(SurveyCity.CREATOR);
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public List<SurveyCity> getSurveyCities() {
        return surveyCities;
    }

    public void setSurveyCities(List<SurveyCity> surveyCities) {
        this.surveyCities = surveyCities;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(surveyCities);
    }
}
