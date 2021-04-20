package org.layton.SpringBootThymeleaf.form;

public class NoteForm {

    private double note ;
    private String name = "";


    public void setNote(double note) {
        this.note = note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNote() {
        return note;
    }

    public String getName() {
        return name;
    }
}
