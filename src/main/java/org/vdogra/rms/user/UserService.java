package org.vdogra.rms.user;

import net.vz.mongodb.jackson.JacksonDBCollection;

import org.vdogra.rms.user.configuration.UserConfiguration;
import org.vdogra.rms.user.resources.IndexResource;
import org.vdogra.rms.user.resources.UserResource;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
 
public class UserService extends Service<UserConfiguration> {
 
    public static void main(String[] args) throws Exception {
        new UserService().run(new String[] { "server" });
    }
 
    @Override
    public void initialize(Bootstrap<UserConfiguration> bootstrap) {
        //test comment
    	bootstrap.setName("user");
    }
 
    @Override
    public void run(UserConfiguration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.manage(mongoManaged);
 
        environment.addHealthCheck(new MongoHealthCheck(mongo));
 
        DB db = mongo.getDB(configuration.mongodb);
        JacksonDBCollection<User, String> users = JacksonDBCollection.wrap(db.getCollection("Users"), User.class, String.class);
 
        environment.addResource(new IndexResource(users));
 
        environment.addResource(new UserResource(users));
    }
 
}