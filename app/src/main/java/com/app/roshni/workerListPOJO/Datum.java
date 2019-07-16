package com.app.roshni.workerListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("cpin")
    @Expose
    private String cpin;
    @SerializedName("cstate")
    @Expose
    private String cstate;
    @SerializedName("cdistrict")
    @Expose
    private String cdistrict;
    @SerializedName("carea")
    @Expose
    private String carea;
    @SerializedName("cstreet")
    @Expose
    private String cstreet;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("employment")
    @Expose
    private String employment;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("created")
    @Expose
    private String created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCpin() {
        return cpin;
    }

    public void setCpin(String cpin) {
        this.cpin = cpin;
    }

    public String getCstate() {
        return cstate;
    }

    public void setCstate(String cstate) {
        this.cstate = cstate;
    }

    public String getCdistrict() {
        return cdistrict;
    }

    public void setCdistrict(String cdistrict) {
        this.cdistrict = cdistrict;
    }

    public String getCarea() {
        return carea;
    }

    public void setCarea(String carea) {
        this.carea = carea;
    }

    public String getCstreet() {
        return cstreet;
    }

    public void setCstreet(String cstreet) {
        this.cstreet = cstreet;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
