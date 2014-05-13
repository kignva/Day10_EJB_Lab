package edu.mum.cs.feb2014.cs544.lab.mb;


import edu.mum.cs.feb2014.cs544.lab.ejb.CustomerService;
import edu.mum.cs.feb2014.cs544.lab.entity.Customer;
import java.io.Serializable;
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
public class CustomerMB implements Serializable {
    
    @EJB
    CustomerService customerService;
    
    private List<Customer> customers;
    private Customer customer = new Customer();
    
    private String title = "New Customer";
    private String actionString = "Add";
    
    public CustomerMB() {
        
    }
    
    @PostConstruct
    private void init() {
        loadData();
    }
    
    private void loadData() {
        customers = customerService.getAll();
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }
    
    public String delete(int id) {
        if (customerService.delete(id)) {
            loadData();
        }
        
        return null;
    }
    
    public String edit(int id)
    {
        String page = "editCustomer";
        customer = customerService.get(id);
        
        if (customer.getId()==0)
        {
            title= "New Customer";
            actionString = "Add";
        }
        else
        {
            
            title= "Edit Customer";
            actionString = "Save";
        }
        
        
        return page;
    }
    
}
