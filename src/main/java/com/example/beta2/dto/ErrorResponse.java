package com.example.beta2.dto;

import java.util.List;

/**
 * ErrorResponse is a Data Transfer Object (DTO) used to send error information
 * from your backend to clients (e.g., REST API responses).
 *
 * This class is typically used when validation fails or an exception occurs,
 * allowing the API to return structured JSON errors instead of plain text.
 */
public class ErrorResponse {

    /**
     * A short, human-readable error message summarizing the problem
     * Example: "Validation Failed" or "Resource Not Found"
     */
    private String error;

    /**
     * Optional detailed messages providing more information about the error
     * Example: ["Brand cannot be blank", "Year must be >= 1886"]
     */
    private List<String> details;

    /**
     * Constructor to initialize the ErrorResponse
     *
     * @param error   Short summary of the error
     * @param details List of detailed messages explaining the error
     */
    // REQUIRED by JSON-B
    public ErrorResponse() {
    }

    public ErrorResponse(String error, List<String> details) {
        this.error = error;
        this.details = details;
    }

    // ===== GETTERS =====
    // These are used by JSON serializers (like Jackson) to convert this object to JSON

    public String getError() {
        return error;
    }

    public List<String> getDetails() {
        return details;
    }

    // Note: No setters are provided, making this DTO effectively immutable after creation.
    // This is fine for error responses since they are usually created once and returned to the client.
}
