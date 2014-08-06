package org.vdogra.rms.user.resources;

import java.util.List;
 

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 

import org.vdogra.rms.user.User;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
 

import com.yammer.metrics.annotation.Timed;
 
@Path("Users")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class UserResource {
 
    private JacksonDBCollection<User, String> collection;
 
    public UserResource(JacksonDBCollection<User, String> Users) {
        this.collection = Users;
    }
 
    @POST
    @Timed
    public Response publishNewUser(@Valid User User) {
        collection.insert(User);
        return Response.noContent().build();
    }
}