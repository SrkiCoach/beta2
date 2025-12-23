package com.example.beta2.rest;

// Import DTO, EJB, and Entity classes
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import com.example.beta2.dto.PagedResponse;
import com.example.beta2.dto.VehicleDto;
import com.example.beta2.ejb.VehicleFacade;
import com.example.beta2.entity.Vehicle;

// Import JAX-RS annotations and classes for building RESTful endpoints
import javax.inject.Inject;            // For dependency injection
import javax.ws.rs.*;                  // For REST annotations like @GET, @POST, etc.
import javax.ws.rs.core.MediaType;     // For specifying content type (JSON)
import javax.ws.rs.core.Response;      // For constructing HTTP responses
import java.util.List;
import java.util.stream.Collectors;    // To transform lists of entities into DTOs
import javax.validation.Valid;         // For validating incoming DTOs


/**
 * VehicleResource is a JAX-RS REST endpoint that exposes CRUD operations for Vehicle entities.
 *
 * The endpoint URL base path is "/vehicles".
 * The class produces and consumes JSON (MediaType.APPLICATION_JSON).
 */
@Path("/vehicles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehicleResource {

    private static final Set<String> ALLOWED_SORTS =
            new HashSet<>(Arrays.asList("id", "brand", "year", "type"));


    /**
     * Inject the VehicleFacade EJB to interact with the database.
     * The facade provides simplified CRUD operations.
     */
    @Inject
    private VehicleFacade vehicleFacade;

    /**
     * GET /vehicles
     * Retrieve all vehicles as a list of VehicleDto objects.
     *
     * - Calls the facade to get all Vehicle entities.
     * - Maps each Vehicle to a VehicleDto (to avoid exposing entity directly).
     * - Returns a JSON list of DTOs.
     */
    @GET
    @Path("/all")
    public List<VehicleDto> getAll() {
        return vehicleFacade.findAll()
                .stream() // Stream the list of entities
                .map(v -> new VehicleDto(
                        v.getId(),
                        v.getBrand(),
                        v.getYear(),
                        v.getType()
                ))
                .collect(Collectors.toList()); // Collect back into a List<VehicleDto>
    }

    /**
     * POST /vehicles
     * Create a new vehicle.
     *
     * @param dto Incoming VehicleDto (validated with @Valid)
     * @return Response containing the created vehicle DTO and HTTP 201 status
     */
    @POST
    public Response create(@Valid VehicleDto dto) {

        // Map DTO to entity
        Vehicle v = new Vehicle();
        v.setBrand(dto.getBrand());
        v.setYear(dto.getYear());
        v.setType(dto.getType());

        // Persist entity to database
        vehicleFacade.create(v);

        // Map persisted entity back to DTO for response
        VehicleDto responseDto = new VehicleDto(
                v.getId(),
                v.getBrand(),
                v.getYear(),
                v.getType()
        );

        // Return HTTP 201 Created with the created DTO
        return Response
                .status(Response.Status.CREATED)
                .entity(responseDto)
                .build();
    }

    /**
     * GET /vehicles/{id}
     * Retrieve a vehicle by its ID.
     *
     * @param id Path parameter for vehicle ID
     * @return Response containing VehicleDto if found
     * @throws NotFoundException if the vehicle does not exist
     */
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {

        Vehicle v = vehicleFacade.find(id);

        if (v == null) {
            // Throwing NotFoundException automatically returns HTTP 404
            throw new NotFoundException("Vehicle not found");
        }

        // Map entity to DTO
        VehicleDto dto = new VehicleDto(
                v.getId(),
                v.getBrand(),
                v.getYear(),
                v.getType()
        );

        // Return HTTP 200 OK with DTO
        return Response.ok(dto).build();
    }

    /**
     * PUT /vehicles/{id}
     * Update an existing vehicle.
     *
     * @param id Path parameter for vehicle ID
     * @param dto Incoming VehicleDto with updated data (validated with @Valid)
     * @return Response containing the updated VehicleDto
     * @throws NotFoundException if vehicle does not exist
     */
    @PUT
    @Path("{id}")
    public Response update(
            @PathParam("id") Long id,
            @Valid VehicleDto dto
    ) {

        // Find existing vehicle
        Vehicle existing = vehicleFacade.find(id);

        if (existing == null) {
            // Throw 404 if vehicle not found
            throw new NotFoundException("Vehicle not found");
        }

        // Update entity fields
        existing.setBrand(dto.getBrand());
        existing.setYear(dto.getYear());
        existing.setType(dto.getType());

        // No explicit persist() or merge() needed because the entity is managed
        // Changes are automatically flushed to the database at transaction commit

        // Map updated entity back to DTO for response
        VehicleDto responseDto = new VehicleDto(
                existing.getId(),
                existing.getBrand(),
                existing.getYear(),
                existing.getType()
        );

        // Return HTTP 200 OK with updated DTO
        return Response.ok(responseDto).build();
    }

    @GET
    @Path("/paged")
    public PagedResponse<VehicleDto> getPaged(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("sort") @DefaultValue("id") String sort,
            @QueryParam("dir")  @DefaultValue("asc") String dir
    ) {

        if (!ALLOWED_SORTS.contains(sort)) {
            throw new BadRequestException("Invalid sort field");
        }

        boolean asc = !"desc".equalsIgnoreCase(dir);

        List<VehicleDto> items =
                vehicleFacade.findPage(page, size, sort, asc)
                        .stream()
                        .map(v -> new VehicleDto(
                                v.getId(),
                                v.getBrand(),
                                v.getYear(),
                                v.getType()
                        ))
                        .collect(java.util.stream.Collectors.toList());

        long total = vehicleFacade.countAll();

        return new PagedResponse<>(items, page, size, total);
    }

}
