package com.example.beta2.rest;

import com.example.beta2.dto.CategoryDto;
import com.example.beta2.ejb.CategoryFacade;
import com.example.beta2.entity.Category;

import javax.inject.Inject;            // For dependency injection
import javax.ws.rs.*;                  // JAX-RS annotations (@GET, @Path, etc.)
import javax.ws.rs.core.MediaType;     // For specifying content type (JSON)
import java.util.List;
import java.util.stream.Collectors;    // To map lists of entities to DTOs

/**
 * REST resource for Category entities.
 * Exposes HTTP endpoints for retrieving category data.
 * Base path: /categories
 * Produces JSON responses.
 */
@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    /**
     * Inject the CategoryFacade EJB to interact with the database.
     */
    @Inject
    private CategoryFacade categoryFacade;

    /**
     * GET /categories
     * Retrieve all categories.
     *
     * - Calls the facade to get all non-deleted Category entities.
     * - Maps each entity to a CategoryDto (to avoid exposing the entity directly).
     * - Returns a JSON list of DTOs.
     */
    @GET
    public List<CategoryDto> getAll() {

        List<Category> entities = categoryFacade.findAll();

        return entities.stream()
                .map(c -> new CategoryDto(
                        c.getId(),
                        c.getUsrCode(),
                        c.getDescription(),
                        c.getActive()
                ))
                .collect(Collectors.toList());
    }

    /**
     * GET /categories/{id}
     * Retrieve a single category by its ID.
     *
     * @param id Path parameter for category ID
     * @return CategoryDto for the requested category
     * @throws NotFoundException if the category does not exist
     */
    @GET
    @Path("/{id}")
    public CategoryDto getById(@PathParam("id") Long id) {

        // Retrieve entity from database
        Category c = categoryFacade.findById(id);

        if (c == null) {
            // Throw 404 Not Found if the category does not exist
            throw new NotFoundException("Category not found");
        }

        // Map entity to DTO and return as JSON
        return new CategoryDto(
                c.getId(),
                c.getUsrCode(),
                c.getDescription(),
                c.getActive()
        );
    }
}
