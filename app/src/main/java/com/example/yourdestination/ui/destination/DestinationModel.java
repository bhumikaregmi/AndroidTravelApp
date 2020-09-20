package com.example.yourdestination.ui.destination;

public class DestinationModel{

    private String name;
    private int image;
    private String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String name) {
        this.detail = name;
    }



    public DestinationModel(String name, int image, String detail) {
        this.name = name;
        this.image = image;
        this.detail = detail;
    }
}
