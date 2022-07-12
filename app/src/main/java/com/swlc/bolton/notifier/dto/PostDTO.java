/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swlc.bolton.notifier.dto;

/**
 *
 * @author athukorala
 */
public class PostDTO extends SuperDTO{
    private UserDTO sharedUser;
    private String timestamp;
    private String post;

    public PostDTO() {
    }

    public PostDTO(UserDTO sharedUser, String timestamp, String post) {
        this.sharedUser = sharedUser;
        this.timestamp = timestamp;
        this.post = post;
    }

    public UserDTO getSharedUser() {
        return sharedUser;
    }

    public void setSharedUser(UserDTO sharedUser) {
        this.sharedUser = sharedUser;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
    
    
}
