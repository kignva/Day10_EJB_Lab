package edu.mum.cs.feb2014.cs544.lab.entity;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Va Y.
 */
@Entity
@Table(name = "Day07_Person")
public class Person {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    protected String name;
    @Temporal(TemporalType.DATE)
    protected Date dob;
    protected String gender;
    protected String address;
    
    public Person()
    {
        
    }
    
    public Person(String name, String gender, String address)
    {
        this.name = name;
        this.gender = gender;
        this.address = address;
    }
    
    public Person(String name, Date dob, String gender, String address)
    {
        this(name, gender, address);
        this.dob=dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.id==((Person)obj).getId());
    }
    
    
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + 
                "{Id: " + id + ", name: " + name + ", gender: " + gender + ", address: " + address + '}';
    }
    
}
