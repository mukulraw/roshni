package com.app.roshni.workerJobListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("brand_id")
    @Expose
    private String brandId;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("brand_street")
    @Expose
    private String brandStreet;
    @SerializedName("brand_area")
    @Expose
    private String brandArea;
    @SerializedName("job_type")
    @Expose
    private String jobType;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("preferred")
    @Expose
    private String preferred;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("salary")
    @Expose
    private String salary;
    @SerializedName("stype")
    @Expose
    private String stype;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("logo")
    @Expose
    private String logo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandStreet() {
        return brandStreet;
    }

    public void setBrandStreet(String brandStreet) {
        this.brandStreet = brandStreet;
    }

    public String getBrandArea() {
        return brandArea;
    }

    public void setBrandArea(String brandArea) {
        this.brandArea = brandArea;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPreferred() {
        return preferred;
    }

    public void setPreferred(String preferred) {
        this.preferred = preferred;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
