package org.vdogra.rms.user;

import java.util.Date;
import java.util.UUID;
 
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
 
public class User {

	
    private String id = UUID.randomUUID().toString();
    
    @net.vz.mongodb.jackson.Id
    private String _id;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String tenant;
    
    @NotBlank
    private String role;
 
    
    private final Date createdOn = new Date();
 
    public User() {
    }
 
    public User(String name, String tenant, String role) {
        super();
        this.name = name;
        this.tenant = tenant;
    }
 
    public String getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }
    public String getTenant() {
        return tenant;
    }
    public String getRole() {
        return role;
    }
 
  
    public Date getCreatedOn() {
        return createdOn;
    }
}