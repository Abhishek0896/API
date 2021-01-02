package com.example.testserver.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Datum implements Parcelable {
    private String itemId;
    private int lastUpdate;
    private Store store;
    private Item item;


    protected Datum(Parcel in) {
        itemId = in.readString();
        lastUpdate = in.readInt();
        store = in.readParcelable(Store.class.getClassLoader());
        item = in.readParcelable(Item.class.getClassLoader());
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }


    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(itemId);
        dest.writeInt(lastUpdate);
        dest.writeParcelable(store, flags);
        dest.writeParcelable(item, flags);
    }

    public int getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
