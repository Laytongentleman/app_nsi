package org.layton.SpringBootThymeleaf.model;

public class Note {

    private int note = 4;
    private String name = "" ;

    public Note(int note, String name) {
        this.note = note;
        this.name = name;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}