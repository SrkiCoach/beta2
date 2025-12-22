package com.example.beta2.ejb;

// Import the Vehicle entity class
import com.example.beta2.entity.Vehicle;

// Import EJB and JPA classes
import javax.ejb.Stateless;          // Marks this class as a stateless EJB
import javax.persistence.EntityManager;  // JPA class to manage entities
import javax.persistence.PersistenceContext; // Annotation to inject EntityManager
import java.util.List;               // Used to return lists of vehicles

/**
 * VehicleFacade is a stateless EJB (Enterprise Java Bean)
 *
 * A stateless EJB does not keep any conversational state between method calls.
 * It is ideal for performing operations like CRUD on entities.
 *
 * The facade pattern is used here to provide a simplified interface
 * for performing database operations on Vehicle entities.
 */
@Stateless
public class VehicleFacade {

    /**
     * EntityManager is injected automatically by the container.
     * It is tied to the persistence unit defined in persistence.xml ("beta2PU").
     *
     * EntityManager is responsible for:
     *  - Persisting entities to the database
     *  - Finding entities
     *  - Updating entities
     *  - Removing entities
     */
    @PersistenceContext(unitName = "beta2PU")
    private EntityManager em;

    /**
     * Retrieve all Vehicle entities from the database.
     *
     * @return List of all vehicles
     *
     * Uses a JPQL (Java Persistence Query Language) query:
     * "SELECT v FROM Vehicle v"
     * This means: select all Vehicle objects (v) from the Vehicle table.
     */
    public List<Vehicle> findAll() {
        return em.createQuery(
                "SELECT v FROM Vehicle v", // JPQL query
                Vehicle.class             // Result type
        ).getResultList();             // Execute the query and return list
    }

    /**
     * Find a single Vehicle by its primary key (id).
     *
     * @param id The ID of the vehicle
     * @return The Vehicle entity if found, null otherwise
     *
     * Uses EntityManager's find method which looks up an entity by its primary key.
     */
    public Vehicle find(Long id) {
        return em.find(Vehicle.class, id);
    }

    /**
     * Persist a new Vehicle entity to the database.
     *
     * @param vehicle The Vehicle object to create
     *
     * em.persist adds the entity to the persistence context,
     * and it will be saved to the database when the transaction is committed.
     */
    public void create(Vehicle vehicle) {
        em.persist(vehicle);
    }

    /**
     * Update an existing Vehicle entity in the database.
     *
     * @param vehicle The Vehicle object to update
     * @return The managed Vehicle entity after merging
     *
     * em.merge updates the existing entity in the database.
     * It returns the managed instance of the entity.
     */
    public Vehicle update(Vehicle vehicle) {
        return em.merge(vehicle);
    }

}
