package com.example.petagram.pojo;

public class FavItem {

    private int favPic;
    private String favName;
    private String key_id;

    public FavItem() {
    }

    public FavItem(int favPic, String favName, String key_id) {
        this.favPic = favPic;
        this.favName = favName;
        this.key_id = key_id;
    }

    public int getFavPic() {
        return favPic;
    }

    public void setFavPic(int favPic) {
        this.favPic = favPic;
    }

    public String getFavName() {
        return favName;
    }

    public void setFavName(String favName) {
        this.favName = favName;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }
}
