package com.example.testserver.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemSet {

    @SerializedName("setName")
    @Expose
    private String setName;
    @SerializedName("entries")
    @Expose
    private Entry entries = null;

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public Entry getEntries() {
        return entries;
    }

    public void setEntries(Entry entries) {
        this.entries = entries;
    }
}