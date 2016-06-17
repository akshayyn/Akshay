/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citizen;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Akshay
 */
public class Person {
    private String firstName;
    
    private String lastName;
    
    private Date dateOfBirth;
    
    private float age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        Calendar currentDateCalendar = new GregorianCalendar();
            Calendar birthDate = new GregorianCalendar();
            currentDateCalendar.setTime(dateOfBirth);
            birthDate.setTime(new Date());
            int diffYear = currentDateCalendar.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
            this.setAge(diffYear);
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }
    
      
    
}
