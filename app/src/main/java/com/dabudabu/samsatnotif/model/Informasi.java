package com.dabudabu.samsatnotif.model;

import java.util.List;

public class Informasi {
    private boolean error;
    private List<ItemInformasi> informasi;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<ItemInformasi> getInformasi(){
        return informasi;
    }
    public void setInformasi(List<ItemInformasi> input){
        this.informasi = input;
    }
}
