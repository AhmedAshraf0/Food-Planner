package com.example.foodplanner.dashboard.view;

public class SliderItem {
    //url to load image
    private int image;
    private String title;

    public SliderItem(int image , String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
