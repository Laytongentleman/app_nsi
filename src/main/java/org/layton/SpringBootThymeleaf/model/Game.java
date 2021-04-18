package org.layton.SpringBootThymeleaf.model;

import java.util.ArrayList;

public class Game {

    private String name;
    private int note;
    private String description;
    private String id;
    private ArrayList<String> tags = new ArrayList<String>();


    public Game(String id, String name , int note, String description, ArrayList<String> tags) {
        this.name = name;
        this.description= description;
        this.note= note;
        this.tags= tags;
        this.id = id;

    }

    public String getId() {
        return id;
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
