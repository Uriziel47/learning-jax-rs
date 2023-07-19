package de.drunkenmasters.learning.liberty.getstarted.api;

import de.drunkenmasters.learning.liberty.getstarted.api.resources.ApiTestResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class ApiRestApplication extends Application {

    public ApiRestApplication() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> result = new HashSet<>();
        result.add(ApiTestResource.class);

        return result;
    }
}
