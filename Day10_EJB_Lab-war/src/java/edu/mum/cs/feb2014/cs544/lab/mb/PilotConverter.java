package edu.mum.cs.feb2014.cs544.lab.mb;

/*
 * Copyright (c)2014
 */



import edu.mum.cs.feb2014.cs544.lab.ejb.PilotService;
import edu.mum.cs.feb2014.cs544.lab.entity.Pilot;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Va Y.
 */
@Named
@RequestScoped
@FacesConverter(value = "PilotConverter") //forClass = Pilot.class)
public class PilotConverter implements Converter {
    
    @EJB
    PilotService pilotService;
    
    public PilotConverter()
    {
//        try {
//            pilotService = (PilotService) new InitialContext().lookup(
//                    "java:global/Day08_EJB_Lab-ejb/PilotService");
//        } catch (NamingException ex) {
//            Logger.getLogger(PersonConverter.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        return pilotService.get(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        return String.valueOf(((Pilot)value).getId());
    }
    
}
