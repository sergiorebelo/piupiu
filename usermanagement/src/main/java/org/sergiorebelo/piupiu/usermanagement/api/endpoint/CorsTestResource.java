package org.sergiorebelo.piupiu.usermanagement.api.endpoint;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/cors-test")
public class CorsTestResource {

//    @GET
//    public Response getCorsTest() {
//        return Response.ok("CORS test endpoint")
//                .header("Access-Control-Allow-Origin", "*")
//                .build();
//    }


    @POST
    @Consumes("application/json")
    public Response postCorsTest(String data) {
        return Response.ok("CORS test POST endpoint")
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}