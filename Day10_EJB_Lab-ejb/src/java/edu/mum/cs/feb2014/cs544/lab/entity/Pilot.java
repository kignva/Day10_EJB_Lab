package edu.mum.cs.feb2014.cs544.lab.entity;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Va Y.
 */
@Entity
@Table(name = "Day07_Pilot")
@NamedQueries({
    @NamedQuery(name = "Pilot.list", query = "SELECT p FROM Pilot p"),
    @NamedQuery(name = "Pilot.freePilots", query = "SELECT p FROM Pilot p WHERE p.plane IS NULL")
})
public class Pilot extends Person implements Serializable {
    
    @OneToOne(mappedBy = "pilot", fetch = FetchType.EAGER)
    private Plane plane;

    public Pilot() {

    }

    public Pilot(String name, String gender, String address) {
        super(name, gender, address);
    }

    public Pilot(String name, Date dob, String gender, String address) {
        super(name, dob, gender, address);
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
    
    

}
