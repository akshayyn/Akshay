/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import Citizen.Person;
import controller.IdGenerator.UniqueIdGenerator;

/**
 *
 * @author Akshay
 */
public class Employee {
    
    private String name;
    private int id;
    
    private static int count = 1;

    public Employee() {
        UniqueIdGenerator ud = new UniqueIdGenerator();
        id = ud.getUniqueId();
    }

    public int getId() {
        return id;
    }
    public void setId(int i){
        id = i;
    }
    public void setName(String name) {
        this.name = name;
    }

    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
