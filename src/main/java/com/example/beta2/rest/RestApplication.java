package com.example.beta2.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * RestApplication is the JAX-RS application configuration class.
 *
 * It activates JAX-RS in the Java EE application and defines the base URI
 * for all REST endpoints in this application.
 */
@ApplicationPath("/api") // Base path for all REST endpoints
public class RestApplication extends Application {
    // No additional methods or fields are needed for basic setup.
    // JAX-RS will automatically scan this package (and subpackages) for resources
    // and providers (like your REST resources and exception mappers).
}
