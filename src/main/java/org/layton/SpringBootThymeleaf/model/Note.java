package org.layton.SpringBootThymeleaf.model;

public class Note {

    private double note;
    private String name = "" ;

    public Note(double note, String name) {
        this.note = note;
        this.name = name;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}