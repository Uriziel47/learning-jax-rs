package de.drunkenmasters.learning.liberty.getstarted.api.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Properties;

@Path("/properties")
public class PropertiesResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Properties getSystemProperties() {
        return System.getProperties();
    }
}
