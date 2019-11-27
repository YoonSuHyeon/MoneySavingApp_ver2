package com.example.moneysavingapp_ver2;



public class User {
    private String nickname;
    private String id;
    private String password;

    public User(String nickname, String id, String password, String email) {
        this.nickname = nickname;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    private String email;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
