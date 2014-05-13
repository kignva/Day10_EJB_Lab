package edu.mum.cs.feb2014.cs544.lab.entity;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Day07_Customer")
@NamedQueries({
    @NamedQuery(name = "Customer.list", query = "SELECT c FROM Customer c")
})
public class Customer extends Person {
    
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    private List<Plane> planes = new ArrayList<>();
    
    public Customer()
    {
        
    }
    
    public Customer(String name, String gender, String address)
    {
        super(name, gender, address);
    }
    
     public Customer(String name, Date dob, String gender, String address)
    {
        super(name,dob, gender, address);
    }
    
    public void add(Plane plane)
    {
        planes.add(plane);
    }
    
    
    
}
