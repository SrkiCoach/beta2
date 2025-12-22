package com.example.beta2.rest;

import com.example.beta2.dto.ErrorResponse;

import javax.ws.rs.core.Response;              // For building HTTP responses
import javax.ws.rs.ext.ExceptionMapper;       // Interface to map exceptions to responses
import javax.ws.rs.ext.Provider;              // Marks this class as a JAX-RS provider
import java.util.Collections;

/**
 * GenericExceptionMapper is a JAX-RS provider that catches any unhandled exceptions
 * thrown during REST request processing.
 *
 * This ensures that the client receives a structured error response
 * instead of a raw stack trace or HTML error page.
 */
@Provider // Automatically discovered by JAX-RS
public class GenericExceptionMapper
        implements ExceptionMapper<Exception> { // Catches all Exceptions

    /**
     * Converts any unhandled Exception into a HTTP Response.
     *
     * @param ex The exception that was thrown
     * @return Response object with HTTP 500 status and ErrorResponse body
     */
    @Override
    public Response toResponse(Exception ex) {

        // In a real application, you would log the exception here for debugging
        // Example: logger.error("Unhandled exception", ex);

        // Create a standardized error response
        ErrorResponse response = new ErrorResponse(
                "Internal server error",                  // Summary of the error
                Collections.singletonList(               // Detailed message list
                        "An unexpected error occurred"   // Generic message to avoid exposing internal details
                )
        );

        // Build a Response with HTTP 500 Internal Server Error
        // The response body will be serialized to JSON automatically by JAX-RS
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(response)
                .build();
    }
}
