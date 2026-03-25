package com.example.prm392_flood_secure.domain.model;

public class UpdateRoleRequest {
    private String role;

    public UpdateRoleRequest(String role) {
        this.role = role;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
