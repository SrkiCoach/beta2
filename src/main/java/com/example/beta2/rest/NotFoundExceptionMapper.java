package com.example.beta2.rest;

import com.example.beta2.dto.ErrorResponse;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

@Provider
public class NotFoundExceptionMapper
        implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException ex) {

        ErrorResponse response = new ErrorResponse(
                "Resource not found",
                Collections.singletonList(ex.getMessage())
        );

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(response)
                .build();
    }
}
