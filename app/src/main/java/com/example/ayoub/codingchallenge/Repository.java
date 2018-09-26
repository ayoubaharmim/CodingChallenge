package com.example.ayoub.codingchallenge;

public class Repository {

    private String name;
    private String description;
    private String star;
    private String username;
    private String avatar;
    private String date_creation;

    public Repository(String name, String description, String star, String username, String avatar, String date_creation) {
        this.name = name;
        this.description = description;
        this.star = star;
        this.username = username;
        this.avatar = avatar;
        this.date_creation = date_creation;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStar() {
        return star;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDate_creation() {
        return date_creation;
    }
}
