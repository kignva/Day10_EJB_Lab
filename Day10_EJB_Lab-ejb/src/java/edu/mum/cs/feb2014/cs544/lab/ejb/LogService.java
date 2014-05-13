/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.cs.feb2014.cs544.lab.ejb;

import edu.mum.cs.feb2014.cs544.lab.entity.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author vyim
 */
@Stateless
public class LogService {

    @PersistenceContext
    private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Log> getLogs(int startPosition, int maxPerPage)
    {
        TypedQuery<Log> q = em.createNamedQuery("Log.all", Log.class);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxPerPage);
        return q.getResultList();
    }
    
    public List<Log> getLogs()
    {
        return getLogs(0, 25);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public long count()
    {
        long result = -1;
        try {
            Query q = em.createNamedQuery("Log.count");
            result = (long) q.getSingleResult();
        } catch (Exception ex) {

        }
        return result;
    }
    
    public boolean addLog(Log log)
    {
        boolean success = false;
        try {
            em.persist(log);
            success = true;
        }
        catch (Exception ex)
        {
            
        }
        return success;
    }
}
