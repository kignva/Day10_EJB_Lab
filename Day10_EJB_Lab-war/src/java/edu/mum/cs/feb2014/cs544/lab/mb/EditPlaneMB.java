package edu.mum.cs.feb2014.cs544.lab.mb;


import com.sun.xml.ws.client.RequestContext;
import edu.mum.cs.feb2014.cs544.lab.ejb.PlaneService;
import edu.mum.cs.feb2014.cs544.lab.entity.Plane;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.core.Context;

/**
 *
 * @author Administrator
 */
@Named
@RequestScoped
public class EditPlaneMB {

    @EJB
    PlaneService planeService;
    
    
    @Context
    RequestContext context;

    private Plane plane = new Plane();
    
    private String title = "New Plane";
    private String actionString = "Add";

    public EditPlaneMB() {

    }

    public Plane getPlane() {
        return plane;
    }

    public String save() {
        String page = "index";
        
        planeService.save(plane);

        return page;
    }


    public String loadData(int id) {
        
        plane = planeService.get(id);
        
        if (plane.getId()>0){
            title = "Edit Plane";
            actionString = "Save";
        }
        else
        {
            title = "New Plane";
            actionString = "Add";
        }
        
        return "editPlane";
    }


    public String getTitle() {
        return title;
    }
    

    public String getActionString() {
        return actionString;
    }
    
        
    public String addMileageAction()
    {
        int mileage = 100;
        System.out.println("addMileageAction " + plane);
        plane.setMileage(plane.getMileage()+mileage);
        System.out.println("addMileageAction " + plane);
        try {
        if (planeService.save(plane))
                loadData(plane.getId());
        }
        catch (Exception ex)
        {
            //Throwable t = ex.getCause();
            FacesMessage message = new FacesMessage(ex.getCause().getMessage());
            FacesContext.getCurrentInstance().addMessage("form1:mileage", message);
            
            plane.setMileage(plane.getMileage()-mileage);
            
        }
        
        return null;
    }
    

}
