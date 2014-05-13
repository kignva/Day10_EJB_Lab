package edu.mum.cs.feb2014.cs544.lab.ejb;

/*
 * Copyright (c)2014
 */


import edu.mum.cs.feb2014.cs544.lab.entity.Customer;
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
public class CustomerService {

    @PersistenceContext
    private EntityManager em;

    public CustomerService() {

    }

    @ExcludeClassInterceptors
    public List<Customer> getAll() {
        TypedQuery<Customer> q = em.createNamedQuery("Customer.list", Customer.class);
        return q.getResultList();
    }

    public boolean save(Customer customer) {
        boolean saved = false;

        try {
            if (customer.getId() > 0) {
                customer = em.merge(customer);
            }
            em.persist(customer);
            saved = true;
        } catch (Exception ex) {
            saved = false;
        }

        return saved;
    }

    public boolean delete(int id) {
        boolean removed = false;

        Customer p = em.find(Customer.class, id);
        if (null != p) {
            em.remove(p);
            removed = true;
        }

        return removed;
    }
    
    
        public Customer get(int id)
    {
        return em.find(Customer.class, id);
    }

   

}
