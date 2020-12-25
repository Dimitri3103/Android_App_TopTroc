package com.example.toptroc.Models;

public class VerticalSliderModel {

    public VerticalSliderModel() {
    }

    private int object_img,object_message;
    private String nomUser,contactUser,nomObjet,descriptionObjet;

    public  VerticalSliderModel(int object_img,int object_message,String nomUser,String contactUser,String nomObjet,String descriptionObjet){
        this.object_img = object_img;
        this.nomUser = nomUser;
        this.contactUser = contactUser;
        this.nomObjet = nomObjet;
        this.descriptionObjet = descriptionObjet;
        this.object_message = object_message;
    }

    public int getObject_img() {
        return object_img;
    }

    public void setObject_img(int object_img) {
        this.object_img = object_img;
    }

    public int getObject_message() {
        return object_message;
    }

    public void setObject_message(int object_message) {
        this.object_message = object_message;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getNomObjet() {
        return nomObjet;
    }

    public void setNomObjet(String nomObjet) {
        this.nomObjet = nomObjet;
    }

    public String getDescriptionObjet() {
        return descriptionObjet;
    }

    public void setDescriptionObjet(String descriptionObjet) {
        this.descriptionObjet = descriptionObjet;
    }
}
