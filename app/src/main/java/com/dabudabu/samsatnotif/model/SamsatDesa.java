package com.dabudabu.samsatnotif.model;

import java.util.List;

public class SamsatDesa {
    private boolean error;
    private List<ItemSamsatDesa> samsatdesa;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<ItemSamsatDesa> getSamsatdesa(){
        return samsatdesa;
    }
    public void setSamsatdesa(List<ItemSamsatDesa> input){
        this.samsatdesa = input;
    }
}
