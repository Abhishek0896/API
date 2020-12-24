package com.example.testserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("lastUpdate")
    @Expose
    private Integer lastUpdate;
    @SerializedName("lanuage")
    @Expose
    private String lanuage;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Integer lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLanuage() {
        return lanuage;
    }

    public void setLanuage(String lanuage) {
        this.lanuage = lanuage;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
