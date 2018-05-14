package com.dabudabu.samsatnotif.model;

public class ItemEvent {
    private int id;
    private String title;
    private String content;
    private String imageurl;

    public int getId(){
        return id;
    }
    public void setId(int input){
        this.id = input;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String input){
        this.title = input;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String input){
        this.content = input;
    }
    public String getImageurl(){
        return imageurl;
    }
    public void setImageurl(String input){
        this.imageurl = input;
    }
}
