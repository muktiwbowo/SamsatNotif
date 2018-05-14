package com.dabudabu.samsatnotif.model;

import java.util.List;

public class Eposti {
    private boolean error;
    private List<ItemEposti> eposti;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<ItemEposti> getEposti(){
        return eposti;
    }
    public void setEposti(List<ItemEposti> input){
        this.eposti = input;
    }
}
