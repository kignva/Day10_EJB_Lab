package edu.mum.cs.feb2014.cs544.lab.ejb;

/*
 * Copyright (c)2014
 */
import edu.mum.cs.feb2014.cs544.lab.entity.Customer;
import edu.mum.cs.feb2014.cs544.lab.entity.Pilot;
import edu.mum.cs.feb2014.cs544.lab.entity.Plane;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Va Y.
 */
@Stateless
@Interceptors(myLogger.class)
public class PlaneService {

    @PersistenceContext
    private EntityManager em;

    public PlaneService() {

    }

    @ExcludeClassInterceptors
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Plane> getAll() {
        TypedQuery<Plane> q = em.createNamedQuery("Plane.list", Plane.class);
        return q.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Plane get(int id) {
        return em.find(Plane.class, id);
    }

    public boolean save(Plane plane) {
        boolean saved = false;
        
        System.out.println("PlaneService save" + plane);

        //try {
            if (plane.getId() > 0) {
                plane = em.merge(plane);
            }
                em.persist(plane);
            saved = true;
        //} catch (Exception ex) {
            //saved = false;
        //}

        return saved;
    }

    public boolean delete(int id) {
        boolean removed = false;

        Plane p = em.find(Plane.class, id);
        if (null != p) {
            em.remove(p);
            removed = true;
        }

        return removed;
    }

    @ExcludeClassInterceptors
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public long count() {
        long result = -1;
        try {
            Query q = em.createNamedQuery("Plane.count");
            result = (long) q.getSingleResult();
        } catch (Exception ex) {

        }
        return result;
    }

    @ExcludeClassInterceptors
    public void fillData() {
        Calendar cal = Calendar.getInstance();
        cal.set(1980, Calendar.FEBRUARY, 12);
        Customer cust1 = new Customer("John", cal.getTime(), "Male", "CA");
        Customer cust2 = new Customer("Dan", "Male", "IA");

        cal.set(1990, Calendar.DECEMBER, 12);
        Customer cust3 = new Customer("Joe", cal.getTime(), "Female", "CA");

        Pilot pilot1 = new Pilot("Jack", "Male", "CA");
        Plane p1 = new Plane("Boeing", "737", 8, 120, pilot1);

        p1.add(cust1);
        p1.add(cust2);
        p1.add(cust3);

        Pilot pilot2 = new Pilot("Fred", "Male", "WA");
        Plane p2 = new Plane("Boeing", "777", 8, 152, pilot2);
        p2.add(cust3);
        p2.add(cust1);

        Pilot pilot3 = new Pilot("Kan", "Male", "IA");
        Plane p3 = new Plane("Airbus", "A200", 3, 160, pilot3);
        p3.add(cust2);
        p3.add(cust1);

        em.persist(cust1);
        em.persist(cust2);
        em.persist(cust3);

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
    }

    @ExcludeClassInterceptors
    public void clearData() {
        TypedQuery<Plane> q = em.createNamedQuery("Plane.list", Plane.class);
        List<Plane> results = q.getResultList();
        for (Plane p : results) {
            em.remove(p);
        }

        TypedQuery<Customer> q2 = em.createNamedQuery("Customer.list", Customer.class);
        List<Customer> results2 = q2.getResultList();
        for (Customer c : results2) {
            em.remove(c);
        }
    }

}
