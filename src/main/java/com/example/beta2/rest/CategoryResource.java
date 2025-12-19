package com.example.beta2.rest;

import com.example.beta2.dto.CategoryDto;
import com.example.beta2.ejb.CategoryFacade;
import com.example.beta2.entity.Category;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    private CategoryFacade categoryFacade;

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

    @GET
    @Path("/{id}")
    public CategoryDto getById(@PathParam("id") Long id) {

        Category c = categoryFacade.findById(id);

        if (c == null) {
            throw new NotFoundException("Category not found");
        }

        return new CategoryDto(
                c.getId(),
                c.getUsrCode(),
                c.getDescription(),
                c.getActive()
        );
    }
}
