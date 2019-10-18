package com.app.roshni.contWorkerPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("block")
    @Expose
    private String block;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("totalchildren")
    @Expose
    private String totalchildren;
    @SerializedName("childrensixtofourteen")
    @Expose
    private String childrensixtofourteen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTotalchildren() {
        return totalchildren;
    }

    public void setTotalchildren(String totalchildren) {
        this.totalchildren = totalchildren;
    }

    public String getChildrensixtofourteen() {
        return childrensixtofourteen;
    }

    public void setChildrensixtofourteen(String childrensixtofourteen) {
        this.childrensixtofourteen = childrensixtofourteen;
    }
}
