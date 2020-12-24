package com.example.testserver;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemOccurrences {

    @SerializedName("firstOccurrences")
    @Expose
    private String firstOccurrences;
    @SerializedName("lastOccurrences")
    @Expose
    private String lastOccurrences;
    @SerializedName("occurrences")
    @Expose
    private Integer occurrences;
    @SerializedName("entries")
    @Expose
    private List<Entry> entries = null;

    public String getFirstOccurrences() {
        return firstOccurrences;
    }

    public void setFirstOccurrences(String firstOccurrences) {
        this.firstOccurrences = firstOccurrences;
    }

    public String getLastOccurrences() {
        return lastOccurrences;
    }

    public void setLastOccurrences(String lastOccurrences) {
        this.lastOccurrences = lastOccurrences;
    }

    public Integer getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(Integer occurrences) {
        this.occurrences = occurrences;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
