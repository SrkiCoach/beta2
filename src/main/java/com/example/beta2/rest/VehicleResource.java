package com.example.beta2.rest;

import com.example.beta2.dto.VehicleDto;
import com.example.beta2.ejb.VehicleFacade;
import com.example.beta2.entity.Vehicle;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

@Path("/vehicles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehicleResource {

    @Inject
    private VehicleFacade vehicleFacade;

    @GET
    public List<VehicleDto> getAll() {
        return vehicleFacade.findAll()
                .stream()
                .map(v -> new VehicleDto(
                        v.getId(),
                        v.getBrand(),
                        v.getYear(),
                        v.getType()
                ))
                .collect(Collectors.toList());
    }

    @POST
    public Response create(@Valid VehicleDto dto) {

        Vehicle v = new Vehicle();
        v.setBrand(dto.getBrand());
        v.setYear(dto.getYear());
        v.setType(dto.getType());

        vehicleFacade.create(v);

        VehicleDto responseDto = new VehicleDto(
                v.getId(),
                v.getBrand(),
                v.getYear(),
                v.getType()
        );

        return Response
                .status(Response.Status.CREATED)
                .entity(responseDto)
                .build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {

        Vehicle v = vehicleFacade.find(id);

        if (v == null) {
            throw new NotFoundException("Vehicle not found");
   //         return Response.status(Response.Status.NOT_FOUND).build();
        }

        VehicleDto dto = new VehicleDto(
                v.getId(),
                v.getBrand(),
                v.getYear(),
                v.getType()
        );

        return Response.ok(dto).build();
    }

    @PUT
    @Path("{id}")
    public Response update(
            @PathParam("id") Long id,
            @Valid VehicleDto dto
    ) {

        Vehicle existing = vehicleFacade.find(id);

        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Update managed entity
        existing.setBrand(dto.getBrand());
        existing.setYear(dto.getYear());
        existing.setType(dto.getType());

        // No persist(), no merge(), no save()
        // JPA will flush automatically

        VehicleDto responseDto = new VehicleDto(
                existing.getId(),
                existing.getBrand(),
                existing.getYear(),
                existing.getType()
        );

        return Response.ok(responseDto).build();
    }


}
