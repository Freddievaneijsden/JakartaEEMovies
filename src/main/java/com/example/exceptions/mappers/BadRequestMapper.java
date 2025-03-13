package com.example.exceptions.mappers;

import com.example.exceptions.BadRequest;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestMapper implements ExceptionMapper<BadRequest> {

    @Override
    public Response toResponse(BadRequest badRequest) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(badRequest.getMessage())
                .build();
    }
}
