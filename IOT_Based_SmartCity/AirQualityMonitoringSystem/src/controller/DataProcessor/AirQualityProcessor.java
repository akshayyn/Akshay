/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DataProcessor;

import business.Area.Area;
import business.City.City;
import business.SensorDataConfiguration;
import business.sensors.PollutionMonitorSensor;
import business.sensors.PollutionMonitorSensorUsage;
import static java.lang.Math.log;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Aks
 */
public class AirQualityProcessor {

    private static double coThreshold = 10.0;

    private static double no2Threshold = 57.0;

    private static double o3Threshold = 137.0;

    private static double so2Threshold = 105.0;

    private static double pbThreshold = 1.5;

    public PollutionMonitorSensorUsage DetermineAreaAirQuality(Area a) {
        PollutionMonitorSensorUsage avgUsage = new PollutionMonitorSensorUsage();
        avgUsage.setDate(new Date());
        double aggOzoneLevel = 0.0;
        double aggCoLevel = 0.0;
        double aggNo2Level = 0.0;
        double aggSo2Level = 0.0;
        double aggPbLevel = 0.0;

        ArrayList<Date> keyList = new ArrayList<>(a.getSensor().getUsageHistory().keySet());
        Collections.sort(keyList);
        for (Date d : keyList) {
            PollutionMonitorSensorUsage usage = a.getSensor().getUsageHistory().get(d);
            aggOzoneLevel = aggOzoneLevel + usage.getOzoneLevel();
            aggCoLevel = aggCoLevel + usage.getCarbonMonoxideLevel();
            aggNo2Level = aggNo2Level + usage.getNitrogenDioxideLevel();
            aggSo2Level = aggNo2Level + usage.getSulfurDioxideLevel();
            aggPbLevel = aggPbLevel + usage.getLeadLevel();

        }
        avgUsage.setCarbonMonoxideLevel(aggCoLevel / keyList.size());
        avgUsage.setOzoneLevel(aggOzoneLevel / keyList.size());
        avgUsage.setSulfurDioxideLevel(aggSo2Level / keyList.size());
        avgUsage.setNitrogenDioxideLevel(aggNo2Level / keyList.size());
        avgUsage.setLeadLevel(aggPbLevel / keyList.size());
        return avgUsage;

    }

    public PollutionMonitorSensorUsage determineIndoorAirQuality(Area a) {
        PollutionMonitorSensorUsage avgUsage = new PollutionMonitorSensorUsage();
        avgUsage.setDate(new Date());
        double aggOzoneLevel = 0.0;
        double aggCoLevel = 0.0;
        double aggNo2Level = 0.0;
        double aggSo2Level = 0.0;
        double aggPbLevel = 0.0;

        int count = 0;

        for (PollutionMonitorSensor sensor : a.getSensorNetwork().getHouseHoldSensorList()) {
            ArrayList<Date> keyList = new ArrayList<>(sensor.getUsageHistory().keySet());
            Collections.sort(keyList);
            for (Date d : keyList) {
                count++;
                PollutionMonitorSensorUsage usage = sensor.getUsageHistory().get(d);
                if (null != usage) {
                    aggOzoneLevel = aggOzoneLevel + usage.getOzoneLevel();
                    aggCoLevel = aggCoLevel + usage.getCarbonMonoxideLevel();
                    aggNo2Level = aggNo2Level + usage.getNitrogenDioxideLevel();
                    aggSo2Level = aggNo2Level + usage.getSulfurDioxideLevel();
                    aggPbLevel = aggPbLevel + usage.getLeadLevel();
                }
            }
        }
        avgUsage.setCarbonMonoxideLevel(aggCoLevel / count);
        avgUsage.setOzoneLevel(aggOzoneLevel / count);
        avgUsage.setSulfurDioxideLevel(aggSo2Level / count);
        avgUsage.setNitrogenDioxideLevel(aggNo2Level / count);
        avgUsage.setLeadLevel(aggPbLevel / count);
        return avgUsage;
    }

    public void curbAirPollution(Area area) {

        long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2015-12-01 00:00:00").getTime();
        long diff = end - offset + 1;
        for (int i = 10; i < 10; i++) {
            PollutionMonitorSensorUsage usage1 = area.getSensor().addUsageInstance(new Timestamp(offset + (long) (Math.random() * diff)));
            usage1.setLeadLevel(getRandomDouble(usage1.getPbThreshold() - 0.75, usage1.getPbThreshold() + 0.10));
            usage1.setNitrogenDioxideLevel(getRandomDouble(usage1.getNo2Threshold() - 27.0, usage1.getNo2Threshold() + 2.0));
            usage1.setCarbonMonoxideLevel(getRandomDouble(usage1.getCoThreshold() - 5.0, usage1.getCoThreshold() + 0.5));
            usage1.setSulfurDioxideLevel(getRandomDouble(usage1.getSo2Threshold() - 50.0, usage1.getSo2Threshold() + 5.0));
            usage1.setOzoneLevel(getRandomDouble(usage1.getO3Threshold() - 65.0, usage1.getO3Threshold() + 12.0));
            for (PollutionMonitorSensor sensor : area.getSensorNetwork().getHouseHoldSensorList()) {

                PollutionMonitorSensorUsage usage = sensor.addUsageInstance(new Timestamp(offset + (long) (Math.random() * diff)));
                usage.setLeadLevel(getRandomDouble(usage1.getPbThreshold() - 0.75, usage1.getPbThreshold() + 0.10));
                usage.setNitrogenDioxideLevel(getRandomDouble(usage1.getNo2Threshold() - 27.0, usage1.getNo2Threshold() + 2.0));
                usage.setCarbonMonoxideLevel(getRandomDouble(usage1.getCoThreshold() - 5.0, usage1.getCoThreshold() + 0.5));
                usage.setSulfurDioxideLevel(getRandomDouble(usage1.getSo2Threshold() - 50.0, usage1.getSo2Threshold() + 5.0));
                usage.setOzoneLevel(getRandomDouble(usage1.getO3Threshold() - 65.0, usage1.getO3Threshold() + 12.0));

            }
        }

    }

    private double getRandomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
