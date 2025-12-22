package com.example.beta2.rest;

import com.example.beta2.dto.ErrorResponse;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException ex) {

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(
                "Validation failed",
                errors
        );

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }
}
