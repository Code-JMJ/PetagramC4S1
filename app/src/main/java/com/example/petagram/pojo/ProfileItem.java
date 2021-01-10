package com.example.petagram.pojo;

public class ProfileItem {

    private String id;
    private String petName;
    private String urlPetPic;
    private int likes = 0;

    public ProfileItem() {
    }

    public ProfileItem(String petName, String urlPetPic, int likes) {
        this.petName = petName;
        this.urlPetPic = urlPetPic;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getUrlPetPic() {
        return urlPetPic;
    }

    public void setUrlPetPic(String urlPetPic) {
        this.urlPetPic = urlPetPic;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}


