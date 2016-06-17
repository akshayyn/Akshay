/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.WorkQueue;


import Citizen.Citizen;

/**
 *
 * @author Aks
 */
public class CitizenRegistrationWorkRequest extends Business.WorkQueue.WorkRequest {
    private Citizen citizen;

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }
    
    
}
