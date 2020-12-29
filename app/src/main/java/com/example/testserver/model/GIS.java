package com.example.testserver.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GIS  implements Parcelable {
    public Boolean success;
    public City city;
    public String message;
    public int status;

    protected GIS(Parcel in) {
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        message = in.readString();
        status = in.readInt();
    }

    public static final Creator<GIS> CREATOR = new Creator<GIS>() {
        @Override
        public GIS createFromParcel(Parcel in) {
            return new GIS(in);
        }

        @Override
        public GIS[] newArray(int size) {
            return new GIS[size];
        }
    };

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public City getData() {
        return city;
    }

    public void setData(City data) {
        this.city = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
        dest.writeString(message);
        dest.writeInt(status);
    }
}
