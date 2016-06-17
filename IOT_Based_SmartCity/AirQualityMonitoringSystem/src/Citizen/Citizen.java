/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citizen;

import business.MedicalHistory.HealthReport;
import business.house.House;


import business.sensors.AsthamaInhalerSensor;
import controller.IdGenerator.UniqueIdGenerator;

/**
 *
 * @author Akshay
 */
public class Citizen {
    
    private Person person;
    
    private int citizenId;
    
    private static int count = 0;
    
    private House residence;
    
    private String emailId;
    
    private String phoneNumber;
    
    private HealthReport healthReport;
    
   
    
    

    public HealthReport getResVitalSignHistory() {
        return healthReport;
    }

    public void setResVitalSignHistory(HealthReport healthReport) {
        this.healthReport = healthReport;
    }

    public Citizen() {
        UniqueIdGenerator ui = new UniqueIdGenerator();
        citizenId = ui.getUniqueId();
         residence = new House();
         person = new Person();
         healthReport = new HealthReport();
    }
    
    

    public Person getPerson() {
        return person;
       
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    public House getResidence() {
        return residence;
    }

    public void setResidence(House residence) {
        this.residence = residence;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HealthReport getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(HealthReport healthReport) {
        this.healthReport = healthReport;
    }

    @Override
    public String toString() {
        return person.getFirstName()+", " + person.getLastName();
    }

  
    
}
