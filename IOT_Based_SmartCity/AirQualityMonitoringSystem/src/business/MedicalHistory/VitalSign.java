/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.MedicalHistory;

import java.util.Date;

/**
 *
 * @author Aks
 */
public class VitalSign {
    
    private float respiratoryRate;
    
    private float heartRate;
    
    private float systolicBloodPressure;
    
    private float weight;
    
    private Date timeStamp;
    
    private boolean vitalSignsNormal;
    
    public boolean isVitalSignsNormal() {
        return vitalSignsNormal;
    }

    public void setVitalSignsNormal(boolean vitalSignsNormal) {
        this.vitalSignsNormal = vitalSignsNormal;
    }

    @Override
    public String toString() {
        return timeStamp.toString();
    }

    

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.setWeight(weight);
    }

    /**
     * @return the timeStamp
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the respiratoryRate
     */
    public float getRespiratoryRate() {
        return respiratoryRate;
    }

    /**
     * @param respiratoryRate the respiratoryRate to set
     */
    public void setRespiratoryRate(float respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    /**
     * @return the heartRate
     */
    public float getHeartRate() {
        return heartRate;
    }

    /**
     * @param heartRate the heartRate to set
     */
    public void setHeartRate(float heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * @return the systolicBloodPressure
     */
    public float getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    /**
     * @param systolicBloodPressure the systolicBloodPressure to set
     */
    public void setSystolicBloodPressure(float systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

}
