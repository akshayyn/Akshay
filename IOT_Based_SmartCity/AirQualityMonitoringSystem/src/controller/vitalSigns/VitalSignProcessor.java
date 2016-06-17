/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vitalSigns;

import business.MedicalHistory.VitalSign;

/**
 *
 * @author Aks
 */
public class VitalSignProcessor {

    public boolean verifyVitalSigns(VitalSign vs, float age) {
        if (age >= 1.0 && age <= 3.0) {
            return ((vs.getRespiratoryRate() >= 20 && vs.getRespiratoryRate() <= 30)
                    && (vs.getHeartRate() >= 80 && vs.getHeartRate() <= 130)
                    && (vs.getSystolicBloodPressure() >= 80 && vs.getSystolicBloodPressure() <= 110)
                    && (vs.getWeight() >= 22 && vs.getWeight() <= 31));
        }
        if (age >= 4.0 && age <= 5.0) {
            return ((vs.getRespiratoryRate() >= 20 && vs.getRespiratoryRate() <= 30)
                    && (vs.getHeartRate() >= 80 && vs.getHeartRate() <= 120)
                    && (vs.getSystolicBloodPressure() >= 80 && vs.getSystolicBloodPressure() <= 110)
                    && (vs.getWeight() >= 31 && vs.getWeight() <= 40));
        }
        if (age >= 13.0) {
            return ((vs.getRespiratoryRate() >= 12 && vs.getRespiratoryRate() <= 20)
                    && (vs.getHeartRate() >= 55 && vs.getHeartRate() <= 105)
                    && (vs.getSystolicBloodPressure() >= 110 && vs.getSystolicBloodPressure() <= 120)
                    && (vs.getWeight() >= 110));
        }
        return false;
    }
}
