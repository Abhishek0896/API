package com.example.testserver;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("rarity")
    @Expose
    private String rarity;
    @SerializedName("series")
    @Expose
    private Object series;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("upcoming")
    @Expose
    private Boolean upcoming;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("backpack")
    @Expose
    private Backpack backpack;
    @SerializedName("obtained")
    @Expose
    private String obtained;
    @SerializedName("obtained_type")
    @Expose
    private String obtainedType;
    @SerializedName("ratings")
    @Expose
    private Ratings ratings;
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;
    @SerializedName("costmeticId")
    @Expose
    private String costmeticId;
    @SerializedName("obtainedValue")
    @Expose
    private String obtainedValue;
    @SerializedName("obtainedFromBattlepass")
    @Expose
    private String obtainedFromBattlepass;

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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Boolean getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(Boolean upcoming) {
        this.upcoming = upcoming;
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

    public String getObtained() {
        return obtained;
    }

    public void setObtained(String obtained) {
        this.obtained = obtained;
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

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

    public String getCostmeticId() {
        return costmeticId;
    }

    public void setCostmeticId(String costmeticId) {
        this.costmeticId = costmeticId;
    }

    public String getObtainedValue() {
        return obtainedValue;
    }

    public void setObtainedValue(String obtainedValue) {
        this.obtainedValue = obtainedValue;
    }

    public String getObtainedFromBattlepass() {
        return obtainedFromBattlepass;
    }

    public void setObtainedFromBattlepass(String obtainedFromBattlepass) {
        this.obtainedFromBattlepass = obtainedFromBattlepass;
    }

}