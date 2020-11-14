package com.example.it17068406;

public class Joke {
    int ID;
    private String Type;
    private String Setup;
    private String punchLine;

    public Joke(int ID, String type, String setup, String punchLine) {
        this.ID = ID;
        Type = type;
        Setup = setup;
        this.punchLine = punchLine;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSetup() {
        return Setup;
    }

    public void setSetup(String setup) {
        Setup = setup;
    }

    public String getPunchLine() {
        return punchLine;
    }

    public void setPunchLine(String punchLine) {
        this.punchLine = punchLine;
    }
}
