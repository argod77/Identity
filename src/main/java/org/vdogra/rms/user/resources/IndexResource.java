package org.vdogra.rms.user.resources;

import java.util.ArrayList;
import java.util.List;
 

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 

import org.vdogra.rms.user.User;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
 

import com.yammer.metrics.annotation.Timed;
 
@Path("/")
public class IndexResource {
 
    private JacksonDBCollection<User, String> collection;
 
    public IndexResource(JacksonDBCollection<User, String> users) {
        this.collection = users;
    }
 
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<User> index() {
        DBCursor<User> dbCursor = collection.find();
        List<User> users = new ArrayList<>();
        while (dbCursor.hasNext()) {
            User user = dbCursor.next();
            users.add(user);
        }
        return users;
    }
 
}