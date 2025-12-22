package com.example.beta2.rest;

import com.example.beta2.dto.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

@Provider
public class GenericExceptionMapper
        implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {

        // Log ex here in real apps

        ErrorResponse response = new ErrorResponse(
                "Internal server error",
                Collections.singletonList("An unexpected error occurred")
        );

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(response)
                .build();
    }
}
