package com.example.prm392_flood_secure.domain.model;

public class LoginResponse {
    private String accessToken;
    private User user;

    public String getAccessToken() { return accessToken; }
    public User getUser() { return user; }
}
