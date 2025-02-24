package com.csn;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
@Path("/hello")
public class HelloWorld {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getMessage() {
        return "<h1>Hello World</h1>";
    }
}