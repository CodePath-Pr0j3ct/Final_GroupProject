package com.example.fityet.Models;

import org.parceler.Parcel;

@Parcel
public class HorizontalModel {

    String id;
    String name;

    // Empty constructor required by Parcels library
    public HorizontalModel(){

    }
    public HorizontalModel(String name, String id) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;

    }
}
