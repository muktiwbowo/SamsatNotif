package com.dabudabu.samsatnotif.model;

public class ItemEposti {
    private int id;
    private String place;
    private String alamat;

    public int getId(){
        return id;
    }
    public void setId(int input){
        this.id = input;
    }
    public String getPlace(){
        return place;
    }
    public void setPlace(String input){
        this.place = input;
    }
    public String getAlamat(){
        return alamat;
    }
    public void setAlamat(String input){
        this.alamat = input;
    }
}
