package de.drunkenmasters.learning.liberty.getstarted.api.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/testing")
public class ApiTestResource {

    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String hello() {
        return "Hello World!";
    }
}
