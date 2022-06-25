/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swlc.bolton.notifier.views;

/**
 *
 * @author athukorala
 */
public class ActiveUserDTO {
    private String name;
    private String email;
    private boolean isSubscribe;

    public ActiveUserDTO() {
    }

    public ActiveUserDTO(String name, String email, boolean isSubscribe) {
        this.name = name;
        this.email = email;
        this.isSubscribe = isSubscribe;
    }

     public ActiveUserDTO(String name,  boolean isSubscribe) {
        this.name = name;
        this.isSubscribe = isSubscribe;
    }

     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }
    
    
}
