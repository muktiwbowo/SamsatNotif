package com.dabudabu.samsatnotif.model;

public class ItemInformasi {
    private int id;
    private String title;
    private String content;

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
}
