package com.example.testserver;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item implements Parcelable {
    private String name;
    private String description;
    private String type;
    private String rarity;
    private Object series;
    private Images images;
    private Backpack backpack;
    private String obtainedType;
    private Ratings ratings;
    private String costmeticId;

    protected Item(Parcel in) {
        name = in.readString();
        description = in.readString();
        type = in.readString();
        rarity = in.readString();
        images = in.readParcelable(Images.class.getClassLoader());
        backpack = in.readParcelable(Backpack.class.getClassLoader());
        obtainedType = in.readString();
        costmeticId = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Object getSeries() {
        return series;
    }

    public void setSeries(Object series) {
        this.series = series;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public String getObtainedType() {
        return obtainedType;
    }

    public void setObtainedType(String obtainedType) {
        this.obtainedType = obtainedType;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public String getCostmeticId() {
        return costmeticId;
    }

    public void setCostmeticId(String costmeticId) {
        this.costmeticId = costmeticId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(type);
        dest.writeString(rarity);
        dest.writeParcelable(images, flags);
        dest.writeParcelable(backpack, flags);
        dest.writeString(obtainedType);
        dest.writeString(costmeticId);
    }
}