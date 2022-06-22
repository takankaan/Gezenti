package com.sarigulsoftware.gezenti;

public class Trip {
    private String id,location, date, point, declaration, summary;

    public Trip(){}

    public Trip(String id, String location, String date, String point, String declaration, String summary, String headPhoto) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.point = point;
        this.declaration = declaration;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
