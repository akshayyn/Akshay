/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.MedicalHistory;

import business.sensors.AsthamaInhalerSensor;
import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class HealthReport {
    private boolean isAsthamaPatient;
    
    private AsthamaInhalerSensor asthamaInhalerSensor;
    
    private boolean isLungCancerPatient;
    
    private boolean isCOPDPatient;
    
    private ArrayList<VitalSign> vitalSigns;

    public ArrayList<VitalSign> getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(ArrayList<VitalSign> vitalSigns) {
        this.vitalSigns = vitalSigns;
    }
    
    public VitalSign addVitalSigns(){
        VitalSign rvs = new VitalSign();
        this.vitalSigns.add(rvs);
        return rvs;      
    }

    public boolean isIsAsthamaPatient() {
        return isAsthamaPatient;
    }

    public void setIsAsthamaPatient(boolean isAsthamaPatient) {
        this.isAsthamaPatient = isAsthamaPatient;
    }

    public AsthamaInhalerSensor getAsthamaInhalerSensor() {
        return asthamaInhalerSensor;
    }

    public void setAsthamaInhalerSensor(AsthamaInhalerSensor asthamaInhalerSensor) {
        this.asthamaInhalerSensor = asthamaInhalerSensor;
    }

    public boolean isIsLungCancerPatient() {
        return isLungCancerPatient;
    }

    public void setIsLungCancerPatient(boolean isLungCancerPatient) {
        this.isLungCancerPatient = isLungCancerPatient;
    }

    public boolean isIsCOPDPatient() {
        return isCOPDPatient;
    }

    public void setIsCOPDPatient(boolean isCOPDPatient) {
        this.isCOPDPatient = isCOPDPatient;
    }

    public HealthReport() {
        vitalSigns = new ArrayList<>();
    }
    public void deleteVitalSign(VitalSign v){
        this.vitalSigns.remove(v);
    }
}
