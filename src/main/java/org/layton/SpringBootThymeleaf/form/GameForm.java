package org.layton.SpringBootThymeleaf.form;


import java.util.ArrayList;

public class GameForm {

    private String id;


    private String name;
    private Float note;
    private Float sumVote;
    private Integer nbVote;
    private String description ="";
    private String tags ;
    private String videoPath = "";
    private ArrayList<String> imgPath;
    private ArrayList<String> platform;
    private String reviewTitle;
    private String reviewContent;
    private String reviewConclusion;

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

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public Float getSumVote() {
        return sumVote;
    }

    public void setSumVote(Float sumVote) {
        this.sumVote = sumVote;
    }

    public Integer getNbVote() {
        return nbVote;
    }

    public void setNbVote(Integer nbVote) {
        this.nbVote = nbVote;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public ArrayList<String> getImgPath() {
        return imgPath;
    }


    public void setImgPath(ArrayList<String> imgPath) {
        this.imgPath = imgPath;
    }

    public ArrayList<String> getPlatform() {
        return platform;
    }

    public void setPlatform(ArrayList<String> platform) {
        this.platform = platform;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewConclusion() {
        return reviewConclusion;
    }

    public void setReviewConclusion(String reviewConclusion) {
        this.reviewConclusion = reviewConclusion;
    }


}
