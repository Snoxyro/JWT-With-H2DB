package com.anyGroup.JWT_With_H2DB.dto;

public class RegisterUserDto {

    private String username;

    private String email;

    private String password;

    //Getters & Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password;}
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    //Getters & Setters
}
