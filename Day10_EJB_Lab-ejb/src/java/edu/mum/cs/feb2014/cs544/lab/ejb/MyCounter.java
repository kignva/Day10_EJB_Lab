/*
 * Copyright (c)2014
 */

package edu.mum.cs.feb2014.cs544.lab.ejb;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author Va Y.
 */
@Singleton
public class MyCounter {

    @EJB
    private PlaneService planeService;
    
    private long count;
    
    @Schedule(hour = "*" ,minute = "*/5", second = "0")
    public void count()
    {
        count = planeService.count();
        
        System.out.println("Service start at " + new Date());
        System.out.println("Plane count = " + count);
    }

    public long getCount() {
        return count;
    }
    
    
}
