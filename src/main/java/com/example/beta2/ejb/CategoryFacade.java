package com.example.beta2.ejb;

import com.example.beta2.entity.Category;

import javax.ejb.Stateless;          // Marks this class as a stateless EJB
import javax.persistence.EntityManager;  // Used to manage JPA entities
import javax.persistence.PersistenceContext; // For injecting the EntityManager
import java.util.List;

/**
 * CategoryFacade is a stateless EJB that provides a simplified interface
 * for performing database operations on Category entities.
 *
 * It uses the facade pattern to abstract JPA operations,
 * so other layers (like REST resources) don't have to write queries directly.
 */
@Stateless
public class CategoryFacade {

    /**
     * Inject the EntityManager tied to the persistence unit "beta2PU"
     * EntityManager is responsible for interacting with the database
     * (persisting, finding, updating, deleting entities).
     */
    @PersistenceContext(unitName = "beta2PU")
    private EntityManager em;

    /**
     * Retrieve all non-deleted categories from the database.
     *
     * @return List of Category entities where isDeleted is NULL
     *
     * JPQL query: "SELECT c FROM Category c WHERE c.isDeleted IS NULL"
     * - This assumes the Category entity has an 'isDeleted' field (soft delete)
     */
    public List<Category> findAll() {
        return em.createQuery(
                "SELECT c FROM Category c WHERE c.isDeleted IS NULL",
                Category.class
        ).getResultList(); // Executes the query and returns a List<Category>
    }

    /**
     * Find a Category entity by its primary key (ID)
     *
     * @param id The ID of the category
     * @return The Category entity if found, null otherwise
     *
     * Uses EntityManager's find() method, which looks up an entity by primary key.
     */
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }
}
