package com.example.exceptions.mappers;

import com.example.exceptions.NotFound;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

//Överallt där NotFound kastas körs metoden nedan som byter statuskod.
@Provider
public class NotFoundMapper implements ExceptionMapper<NotFound> {

    @Override
    public Response toResponse(NotFound notFound) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(notFound.getMessage())
                .build();
    }
}
