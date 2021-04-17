package org.layton.SpringBootThymeleaf.model;


public class Vote {


    private int voteValue;

    public Vote(int voteValue) {
        this.voteValue = voteValue;
    }

    public int getVoteValue() {
        return voteValue;
    }


    public void setVoteValue(int voteValue) {
        this.voteValue = voteValue;
    }
}