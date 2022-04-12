package com.ecommerce.sample.model;

import java.util.Set;

public class JwtResponse {

    private String username;
    private String jwtToken;
    private Set<Role> role;

    public JwtResponse(Set<Role> role, String username, String jwtToken ) {
        this.username = username;
        this.jwtToken = jwtToken;
        this.role = role;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}

