package com.innovoskies.soul.lyrics.enums;

public enum AuthRole {
    ADMIN("admin"),
    FREE("free");

    private String name;

    private AuthRole(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
}
