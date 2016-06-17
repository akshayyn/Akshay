/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.WorkQueue;

import business.Area.Area;

/**
 *
 * @author Aks
 */
public class CurbPollutionWorkRequest extends Business.WorkQueue.WorkRequest {
    private Area area;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
}
