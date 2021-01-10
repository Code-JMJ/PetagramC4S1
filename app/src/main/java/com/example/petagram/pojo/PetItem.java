package com.example.petagram.pojo;

public class PetItem {
    private int petPic;
    private String petName;
    private String key_id;
    private String favStatus;

    public PetItem() {
    }

    public PetItem(int petPic, String petName, String key_id, String favStatus) {
        this.petPic = petPic;
        this.petName = petName;
        this.key_id = key_id;
        this.favStatus = favStatus;
    }

    public int getPetPic() {
        return petPic;
    }

    public void setPetPic(int petPic) {
        this.petPic = petPic;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }
}
