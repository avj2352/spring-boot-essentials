package com.innovoskies.soul.lyrics.groups.model;
// spring
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
// custom
import com.innovoskies.soul.lyrics.enums.AuthProvider;
import com.innovoskies.soul.lyrics.enums.AuthRole;

@Document(collection="users")
public class UserModel {
    
    @Field(name="provider")
    private AuthProvider provider;
    
    private String id;

    @Field(name="name")
    private String fullName;

    private String email;

    private AuthRole role;

    public AuthProvider getProvider() {
        return provider;
    }

    public AuthRole getRole() {
        return role;
    }

    public void setRole(AuthRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }
    
}
