package com.example.petagram.pojo;

public class BioItem {

    private String biography;
    private int followers_count;
    private int follows_count;
    private int media_count;
    private String name;
    private String profile_picture_url;
    private String username;

    public BioItem() {
    }

    public BioItem(String biography, int followers_count, int follows_count, int media_count, String name, String profile_picture_url, String username) {
        this.biography = biography;
        this.followers_count = followers_count;
        this.follows_count = follows_count;
        this.media_count = media_count;
        this.name = name;
        this.profile_picture_url = profile_picture_url;
        this.username = username;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFollows_count() {
        return follows_count;
    }

    public void setFollows_count(int follows_count) {
        this.follows_count = follows_count;
    }

    public int getMedia_count() {
        return media_count;
    }

    public void setMedia_count(int media_count) {
        this.media_count = media_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_picture_url() {
        return profile_picture_url;
    }

    public void setProfile_picture_url(String profile_picture_url) {
        this.profile_picture_url = profile_picture_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
