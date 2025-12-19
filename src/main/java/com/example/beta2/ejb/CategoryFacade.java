package com.example.beta2.ejb;

import com.example.beta2.entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryFacade {

    @PersistenceContext(unitName = "beta2PU")
    private EntityManager em;

    public List<Category> findAll() {
        return em.createQuery(
                "SELECT c FROM Category c WHERE c.isDeleted IS NULL",
                Category.class
        ).getResultList();
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }
}


