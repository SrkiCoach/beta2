package com.example.beta2.rest;

import com.example.beta2.dto.ErrorResponse;

import javax.ws.rs.NotFoundException;         // JAX-RS exception for 404 errors
import javax.ws.rs.core.Response;             // For building HTTP responses
import javax.ws.rs.ext.ExceptionMapper;       // Interface to map exceptions to responses
import javax.ws.rs.ext.Provider;              // Marks this class as a JAX-RS provider
import java.util.Collections;

/**
 * NotFoundExceptionMapper is a JAX-RS provider that handles NotFoundException.
 *
 * This exception is typically thrown in your REST resources when a requested entity
 * is not found (e.g., GET /vehicles/{id} with a non-existent ID).
 *
 * It converts the exception into a structured HTTP 404 response with a JSON body.
 */
@Provider // Automatically discovered by JAX-RS
public class NotFoundExceptionMapper
        implements ExceptionMapper<NotFoundException> {

    /**
     * Converts a NotFoundException into an HTTP 404 Response.
     *
     * @param ex The NotFoundException that was thrown
     * @return Response object with HTTP 404 status and an ErrorResponse body
     */
    @Override
    public Response toResponse(NotFoundException ex) {

        // Wrap the exception message in an ErrorResponse DTO
        // The details list contains the specific message from the exception
        ErrorResponse response = new ErrorResponse(
                "Resource not found",               // Summary of the error
                Collections.singletonList(ex.getMessage()) // Detailed message list
        );

        // Build a Response with HTTP 404 Not Found
        // Response body will be serialized to JSON automatically by JAX-RS
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(response)
                .build();
    }
}
