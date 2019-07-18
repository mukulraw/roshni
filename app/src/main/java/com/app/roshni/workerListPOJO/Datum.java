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
    @SerializedName("dob")
    @Expose
    private String dob;
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
    @SerializedName("ppin")
    @Expose
    private String ppin;
    @SerializedName("pstate")
    @Expose
    private String pstate;
    @SerializedName("pdistrict")
    @Expose
    private String pdistrict;
    @SerializedName("parea")
    @Expose
    private String parea;
    @SerializedName("pstreet")
    @Expose
    private String pstreet;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("employment")
    @Expose
    private String employment;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("religion")
    @Expose
    private String religion;
    @SerializedName("educational")
    @Expose
    private String educational;
    @SerializedName("marital")
    @Expose
    private String marital;
    @SerializedName("employer")
    @Expose
    private String employer;
    @SerializedName("home")
    @Expose
    private String home;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("sector")
    @Expose
    private String sector;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getPpin() {
        return ppin;
    }

    public void setPpin(String ppin) {
        this.ppin = ppin;
    }

    public String getPstate() {
        return pstate;
    }

    public void setPstate(String pstate) {
        this.pstate = pstate;
    }

    public String getPdistrict() {
        return pdistrict;
    }

    public void setPdistrict(String pdistrict) {
        this.pdistrict = pdistrict;
    }

    public String getParea() {
        return parea;
    }

    public void setParea(String parea) {
        this.parea = parea;
    }

    public String getPstreet() {
        return pstreet;
    }

    public void setPstreet(String pstreet) {
        this.pstreet = pstreet;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
