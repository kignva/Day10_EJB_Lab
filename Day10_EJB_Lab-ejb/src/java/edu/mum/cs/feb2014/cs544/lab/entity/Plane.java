package edu.mum.cs.feb2014.cs544.lab.entity;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Va Y.
 */

@Entity
@Table(name = "Day07_Plane")
@NamedQueries({
    @NamedQuery(name = "Plane.list", query = "SELECT p FROM Plane p"),
    @NamedQuery(name = "Plane.count", query = "SELECT COUNT(p) FROM Plane p")
})
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Version
    private int lock_version;
    
    private String make;
    private String model;
    private int numberOfEngines;
    private int capacity;
    
    private int mileage;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Plane_has_customers")
    private List<Customer> customers = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pilot pilot;
    
    
    private Person medicalTrainee;

    public Plane() {

    }

    public Plane(String make, String model,
            int numberOfEnigines, int capacity, Pilot pilot) {
        this.make = make;
        this.model = model;
        this.numberOfEngines = numberOfEnigines;
        this.capacity = capacity;
        pilot.setPlane(this);
        this.pilot = pilot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add(Customer cust) {
        cust.add(this);
        customers.add(cust);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getNumberOfEngines() {
        return numberOfEngines;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * @return the pilot
     */
    public Pilot getPilot() {
        if (null == pilot)
        {
            pilot = new Pilot();
        }
        
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        pilot.setPlane(this);
        this.pilot = pilot;
    }
    
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumberOfEngines(int numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getLock_version() {
        return lock_version;
    }

    public void setLock_version(int lock_version) {
        this.lock_version = lock_version;
    }
    
    

    /**
     * @return the medical_trainee
     */
    public Person getMedicalTrainee() {
        return medicalTrainee;
    }

    /**
     * @param medicalTrainee the medical_trainee to set
     */
    public void setMedicalTrainee(Person medicalTrainee) {
        this.medicalTrainee = medicalTrainee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Plane{id=");
        sb.append(id);
        sb.append(", make=");
        sb.append(make);
        sb.append(", model=");
        sb.append(model);
        sb.append(", mileage=");
        sb.append(mileage);
        sb.append(",version=");
        sb.append(lock_version);
        sb.append("}");
        
        return sb.toString();
    }
    
    

}
