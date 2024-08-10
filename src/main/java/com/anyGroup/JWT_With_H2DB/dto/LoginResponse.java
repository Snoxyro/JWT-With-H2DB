package com.anyGroup.JWT_With_H2DB.dto;

public class LoginResponse {

    private String token;

    private long expiresIn;

    //Getters & Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public long getExpiresIn() { return expiresIn; }
    public void setExpiresIn(long expiresIn) { this.expiresIn = expiresIn; }
    //Getters & Setters
}