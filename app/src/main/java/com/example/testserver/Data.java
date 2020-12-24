package com.example.testserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("itemId")
    @Expose
    private String itemId;
    @SerializedName("lastUpdate")
    @Expose
    private Integer lastUpdate;
    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("itemSet")
    @Expose
    private ItemSet itemSet;
    @SerializedName("itemOccurrences")
    @Expose
    private ItemOccurrences itemOccurrences;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Integer lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemSet getItemSet() {
        return itemSet;
    }

    public void setItemSet(ItemSet itemSet) {
        this.itemSet = itemSet;
    }

    public ItemOccurrences getItemOccurrences() {
        return itemOccurrences;
    }

    public void setItemOccurrences(ItemOccurrences itemOccurrences) {
        this.itemOccurrences = itemOccurrences;
    }

}
