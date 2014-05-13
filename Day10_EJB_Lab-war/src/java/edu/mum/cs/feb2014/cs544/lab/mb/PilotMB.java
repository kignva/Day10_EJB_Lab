package edu.mum.cs.feb2014.cs544.lab.mb;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.mum.cs.feb2014.cs544.lab.ejb.PilotService;
import edu.mum.cs.feb2014.cs544.lab.entity.Pilot;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Va Y.
 */
@Named
@RequestScoped
public class PilotMB {
    
    @EJB
    private PilotService pilotService;
    
    private List<Pilot> pilots = new ArrayList<>();
    private List<Pilot> freePilots = new ArrayList<>();
    
    public PilotMB() {
        
    }
    
    @PostConstruct
    private void init() {
        loadData();
    }
    
    private void loadData() {
        pilots = pilotService.getAll();
    }
    
    public List<Pilot> getPilots() {
        return pilots;
    }
    
    public List<Pilot> getFreePilots() {
        freePilots = pilotService.getFreePilots();
        return freePilots;
    }
    
//    public String getFreePilotAction()
//    {
//        
//        return null;
//    }

    
    public String delete(int id) {
        if (pilotService.delete(id)) {
            loadData();
        }
        return null;
    }
    
}
