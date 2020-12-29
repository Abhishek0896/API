package com.example.testserver;

import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {

    private Boolean isFeatured;
    private Boolean isRefundable;
    private int cost;
    private int occurrences;
    private Boolean isNew;


    protected Store(Parcel in) {
        byte tmpIsFeatured = in.readByte();
        isFeatured = tmpIsFeatured == 0 ? null : tmpIsFeatured == 1;
        byte tmpIsRefundable = in.readByte();
        isRefundable = tmpIsRefundable == 0 ? null : tmpIsRefundable == 1;
        cost = in.readInt();
        occurrences = in.readInt();
        byte tmpIsNew = in.readByte();
        isNew = tmpIsNew == 0 ? null : tmpIsNew == 1;
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isFeatured == null ? 0 : isFeatured ? 1 : 2));
        dest.writeByte((byte) (isRefundable == null ? 0 : isRefundable ? 1 : 2));
        dest.writeInt(cost);
        dest.writeInt(occurrences);
        dest.writeByte((byte) (isNew == null ? 0 : isNew ? 1 : 2));
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public void setFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public Boolean getRefundable() {
        return isRefundable;
    }

    public void setRefundable(Boolean refundable) {
        isRefundable = refundable;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public static Creator<Store> getCREATOR() {
        return CREATOR;
    }
}
