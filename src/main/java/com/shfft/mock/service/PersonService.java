package com.shfft.mock.service;

import javax.ws.rs.*;

@Path("/webapi/rest/person")
@Consumes("application/json;charset=UTF-8")
@Produces("application/json;charset=UTF-8")
public interface PersonService {

    @GET
    @Path("/sayName/{string}")
    String sayName(@PathParam("string") String string);

}
