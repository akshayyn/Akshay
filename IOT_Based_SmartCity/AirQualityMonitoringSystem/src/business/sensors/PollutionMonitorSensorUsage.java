/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.sensors;

import java.util.Date;

/**
 *
 * @author Aks
 */
public class PollutionMonitorSensorUsage {

    private double coThreshold = 10.0;

    private double coVariance = 2.0;

    private double no2Threshold = 57.0;

    private double no2Variance = 7.0;

    private double o3Threshold = 137.0;

    private double o3Variance = 10.0;

    private double so2Threshold = 105.0;

    private double so2Variance = 10.0;

    private double pbThreshold = 1.5;

    private double pbVariance = 0.3;

    private double carbonMonoxideLevel;

    private double leadLevel;

    private double nitrogenDioxideLevel;

    private double ozoneLevel;

    private Date date;

    private double sulfurDioxideLevel;

    public boolean isPolluted() {
        if (carbonMonoxideLevel > coThreshold
                || leadLevel > pbThreshold
                || nitrogenDioxideLevel > no2Threshold
                || sulfurDioxideLevel > so2Threshold
                || ozoneLevel > o3Threshold) {
            return true;
        }
        return false;
    }

    public double getCarbonMonoxideLevel() {
        return carbonMonoxideLevel;
    }

    public void setCarbonMonoxideLevel(double carbonMonoxideLevel) {
        this.carbonMonoxideLevel = carbonMonoxideLevel;
    }

    public double getLeadLevel() {
        return leadLevel;
    }

    public void setLeadLevel(double leadLevel) {
        this.leadLevel = leadLevel;
    }

    public double getNitrogenDioxideLevel() {
        return nitrogenDioxideLevel;
    }

    public void setNitrogenDioxideLevel(double nitrogenDioxideLevel) {
        this.nitrogenDioxideLevel = nitrogenDioxideLevel;
    }

    public double getOzoneLevel() {
        return ozoneLevel;
    }

    public void setOzoneLevel(double ozoneLevel) {
        this.ozoneLevel = ozoneLevel;
    }

    public double getSulfurDioxideLevel() {
        return sulfurDioxideLevel;
    }

    public void setSulfurDioxideLevel(double sulfurDioxideLevel) {
        this.sulfurDioxideLevel = sulfurDioxideLevel;
    }

    public double getCoThreshold() {
        return coThreshold;
    }

    public double getCoVariance() {
        return coVariance;
    }

    public double getNo2Threshold() {
        return no2Threshold;
    }

    public double getNo2Variance() {
        return no2Variance;
    }

    public double getO3Threshold() {
        return o3Threshold;
    }

    public double getO3Variance() {
        return o3Variance;
    }

    public double getSo2Threshold() {
        return so2Threshold;
    }

    public double getSo2Variance() {
        return so2Variance;
    }

    public double getPbThreshold() {
        return pbThreshold;
    }

    public double getPbVariance() {
        return pbVariance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date.toString();
    }
    

}
