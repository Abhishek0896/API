package com.example.testserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medium {

    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("dimensions")
    @Expose
    private String dimensions;
    @SerializedName("framerate")
    @Expose
    private Integer framerate;
    @SerializedName("duration")
    @Expose
    private Integer duration;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Integer getFramerate() {
        return framerate;
    }

    public void setFramerate(Integer framerate) {
        this.framerate = framerate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
