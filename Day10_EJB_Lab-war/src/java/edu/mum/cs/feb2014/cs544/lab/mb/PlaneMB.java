package edu.mum.cs.feb2014.cs544.lab.mb;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.mum.cs.feb2014.cs544.lab.ejb.PlaneService;
import edu.mum.cs.feb2014.cs544.lab.entity.Plane;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Va Y.
 */
@Named()
@RequestScoped
public class PlaneMB {
    
    @EJB
    PlaneService planeService;
    
    private List<Plane> planes;
    
    public PlaneMB() {
        
    }
    
    @PostConstruct
    private void init() {
        loadData();
    }
    
    public List<Plane> getPlanes() {
        return planes;
    }
    
    public String fillData() {
        planeService.fillData();
        loadData();
        
        return null;
    }

    private String loadData() {
        planes = planeService.getAll();
        
        return null;
    }
    
    public String clearData() {
        planeService.clearData();
        loadData();
        
        return null;
    }
    
    public String delete(int id) {
        if (planeService.delete(id)) {
            loadData();
        }
        
        return null;
    }
    
    
}
