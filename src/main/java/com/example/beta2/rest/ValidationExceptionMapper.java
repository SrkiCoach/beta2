package com.example.beta2.rest;

import com.example.beta2.dto.ErrorResponse;

import javax.validation.ConstraintViolationException; // Exception thrown by Bean Validation
import javax.ws.rs.core.Response;                     // For building HTTP responses
import javax.ws.rs.ext.ExceptionMapper;               // Interface to map exceptions to responses
import javax.ws.rs.ext.Provider;                      // Marks this class as a JAX-RS provider
import java.util.List;
import java.util.stream.Collectors;

/**
 * ValidationExceptionMapper is a JAX-RS provider that intercepts
 * ConstraintViolationException thrown during request processing.
 *
 * When a DTO fails validation (e.g., @NotBlank, @Min, @Size),
 * this mapper converts the exception into a structured HTTP 400 response.
 */
@Provider // Automatically discovered by JAX-RS
public class ValidationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    /**
     * Converts a ConstraintViolationException into a HTTP Response.
     *
     * @param ex The exception containing validation errors
     * @return Response object with HTTP 400 status and ErrorResponse body
     */
    @Override
    public Response toResponse(ConstraintViolationException ex) {

        // Extract validation messages from all constraint violations
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getMessage()) // Get the human-readable message from each violation
                .collect(Collectors.toList());

        // Wrap the list of error messages in an ErrorResponse DTO
        ErrorResponse response = new ErrorResponse(
                "Validation failed", // Summary of the error
                errors                // Detailed messages
        );

        // Build a Response with HTTP 400 Bad Request
        // The response body will be serialized to JSON automatically by JAX-RS
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }
}
