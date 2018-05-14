package com.dabudabu.samsatnotif.model;

import java.util.List;

public class Event {
    private boolean error;
    private List<ItemEvent> event;

    public boolean getError(){
        return error;
    }
    public void setError(boolean input){
        this.error = input;
    }
    public List<ItemEvent> getEvent(){
        return event;
    }
    public void setEvent(List<ItemEvent> input){
        this.event = input;
    }
}
