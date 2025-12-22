package com.example.beta2.ejb;

import com.example.beta2.entity.Vehicle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VehicleFacade {

    @PersistenceContext(unitName = "beta2PU")
    private EntityManager em;

    public List<Vehicle> findAll() {
        return em.createQuery(
                "SELECT v FROM Vehicle v",
                Vehicle.class
        ).getResultList();
    }

    public Vehicle find(Long id) {
        return em.find(Vehicle.class, id);
    }
    public void create(Vehicle vehicle) {
        em.persist(vehicle);
    }

    public Vehicle update(Vehicle vehicle) {
        return em.merge(vehicle);
    }

}
