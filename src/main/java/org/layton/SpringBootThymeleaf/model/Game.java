package org.layton.SpringBootThymeleaf.model;

import java.util.ArrayList;

public class Game {

    private String name;
    private int note;
    private String description;
    private ArrayList<String> tags = new ArrayList<String>();


    public Game(String name , int Note, String description, ArrayList<String> tags) {
        this.name = name;
        this.description= description;
        this.note= note;
        this.tags= tags;

    }

    public String getName() {
        return name;
    }

    public int getNote() {
        return note;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
