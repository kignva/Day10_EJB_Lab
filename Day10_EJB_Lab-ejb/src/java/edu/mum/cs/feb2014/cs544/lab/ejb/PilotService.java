package edu.mum.cs.feb2014.cs544.lab.ejb;

/*
 * Copyright (c)2014
 */


import edu.mum.cs.feb2014.cs544.lab.entity.Pilot;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Va Y.
 */
@Stateless
@Interceptors(myLogger.class)
public class PilotService {

    @PersistenceContext
    private EntityManager em;

    public PilotService() {

    }

    @ExcludeClassInterceptors
    public List<Pilot> getAll() {
        TypedQuery<Pilot> q = em.createNamedQuery("Pilot.list", Pilot.class);
        return q.getResultList();
    }
    
    @ExcludeClassInterceptors
    public List<Pilot> getFreePilots()
    {
       TypedQuery<Pilot> q = em.createNamedQuery("Pilot.freePilots", Pilot.class);
        return q.getResultList(); 
    }

    public boolean save(Pilot pilot) {
        boolean saved = false;

        try {
            if (pilot.getId() > 0) {
                pilot = em.merge(pilot);
            } 
            em.persist(pilot);
            saved = true;
        } catch (Exception ex) {
            saved = false;
        }

        return saved;
    }

    public boolean delete(int id) {
        boolean removed = false;

        Pilot p = em.find(Pilot.class, id);
        if (null != p) {
            em.remove(p);
            removed = true;
        }

        return removed;
    }
    
    
    public Pilot get(int id)
    {
        return em.find(Pilot.class, id);
    }

   

}
