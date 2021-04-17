package org.layton.SpringBootThymeleaf.form;

public class NoteForm {

    private int note = 4;
    private String name = "";


    public void setNote(int note) {
        this.note = note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNote() {
        return note;
    }

    public String getName() {
        return name;
    }
}
