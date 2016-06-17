/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Area;


import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class AreaDirectory {
    
    private ArrayList<Area> areaList;

    public AreaDirectory() {
        areaList = new ArrayList<>();
    }

    public ArrayList<Area> getAreaList() {
        return areaList;
    }
    
    public Area createAndAddArea(String name, Area.AreaType type){
        Area area = null;
        if (type == Area.AreaType.Area){
            area = new CityArea(name);
            areaList.add(area);
        }
        return area;
    }
    
}
