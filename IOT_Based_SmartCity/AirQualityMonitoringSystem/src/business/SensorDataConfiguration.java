/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import Business.DB4OUtil.DB4OUtil;
import static Business.Organization.Organization.Type.Citizen;
import Citizen.Citizen;
import business.Area.Area;
import business.City.City;
import business.MedicalHistory.VitalSign;
import business.sensors.AsthamaInhalerSensor;
import business.sensors.PollutionMonitorSensor;
import business.sensors.PollutionMonitorSensorUsage;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Aks
 */
public class SensorDataConfiguration {

    private static Business.EcoSystem es;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();

    /**
     * Run the example.
     */
    public static void loadSensorData(Business.EcoSystem es) throws InterruptedException {
        log("loadSensorData started.");
        SensorDataConfiguration.es = es;
        SensorDataConfiguration sensorConfig = new SensorDataConfiguration(10, 10, 120);
        sensorConfig.activateDataLoading();
        /*
         To start the dataLoad at a specific date in the future, the initial delay
         needs to be calculated relative to the current time, as in : 
         Date futureDate = ...
         long startTime = futureDate.getTime() - System.currentTimeMillis();
         AlarmClock alarm = new AlarmClock(startTime, 1, 20);
         This works only if the system clock isn't reset.
         */
        log("loadSensorData ended.");
    }

    public SensorDataConfiguration() {

    }

    public SensorDataConfiguration(long aInitialDelay, long aDelayBetweenBeeps, long aStopAfter) {
        fInitialDelay = aInitialDelay;
        fDelayBetweenRuns = aDelayBetweenBeeps;
        fShutdownAfter = aStopAfter;
        fScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
    }

    /**
     * Sound the alarm for a few seconds, then stop.
     */
    void activateDataLoading() {

        Runnable loadDataTask = new LoadData();
        ScheduledFuture<?> loadDataFuture = fScheduler.scheduleWithFixedDelay(
                loadDataTask, fInitialDelay, fDelayBetweenRuns, TimeUnit.SECONDS
        );
        Runnable stopLoad = new StopLoading(loadDataFuture);
        fScheduler.schedule(stopLoad, fShutdownAfter, TimeUnit.SECONDS);
    }

    // PRIVATE 
    private ScheduledExecutorService fScheduler;
    private long fInitialDelay;
    private long fDelayBetweenRuns;
    private long fShutdownAfter;

    private static void log(String aMsg) {
        System.out.println(aMsg);
    }

    /**
     * If invocations might overlap, you can specify more than a single thread.
     */
    private static final int NUM_THREADS = 1;
    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    private final class LoadData implements Runnable {

        @Override
        public void run() {
            ++fCount;
            log("inside" + fCount);

            for (City city : SensorDataConfiguration.es.getCityList()) {
                for (Area area : city.getAreaDirectory().getAreaList()) {
                    log("inside" + fCount);
                    long offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
                    long end = Timestamp.valueOf("2015-12-01 00:00:00").getTime();
                    long diff = end - offset + 1;

                    PollutionMonitorSensorUsage usage1 = area.getSensor().addUsageInstance(new Timestamp(offset + (long) (Math.random() * diff)));
                    usage1.setLeadLevel(getRandomDouble(usage1.getPbThreshold() - 0.75, usage1.getPbThreshold() + 0.375));
                    usage1.setNitrogenDioxideLevel(getRandomDouble(usage1.getNo2Threshold() - 27.0, usage1.getNo2Threshold() + 13.0));
                    usage1.setCarbonMonoxideLevel(getRandomDouble(usage1.getCoThreshold() - 5.0, usage1.getCoThreshold() + 2.5));
                    usage1.setSulfurDioxideLevel(getRandomDouble(usage1.getSo2Threshold() - 50.0, usage1.getSo2Threshold() + 26.0));
                    usage1.setOzoneLevel(getRandomDouble(usage1.getO3Threshold() - 65.0, usage1.getO3Threshold() + 33.0));
                    for (PollutionMonitorSensor sensor : area.getSensorNetwork().getHouseHoldSensorList()) {

                        PollutionMonitorSensorUsage usage = sensor.addUsageInstance(new Timestamp(offset + (long) (Math.random() * diff)));
                        usage.setLeadLevel(getRandomDouble(usage1.getPbThreshold() - 0.75, usage1.getPbThreshold() + 0.375));
                        usage.setNitrogenDioxideLevel(getRandomDouble(usage1.getNo2Threshold() - 27.0, usage1.getNo2Threshold() + 13.0));
                        usage.setCarbonMonoxideLevel(getRandomDouble(usage1.getCoThreshold() - 5.0, usage1.getCoThreshold() + 2.50));
                        usage.setSulfurDioxideLevel(getRandomDouble(usage1.getSo2Threshold() - 50.0, usage1.getSo2Threshold() + 25.0));
                        usage.setOzoneLevel(getRandomDouble(usage1.getO3Threshold() - 65.0, usage1.getO3Threshold() + 32.5));

                    }

                    Timestamp randomTime = new Timestamp(offset + (long) (Math.random() * diff));
                    for (int i = 0; i < 3; i++) {
                        Random r = new Random();
                        if (null != city.getAreaDirectory().getAreaList() && city.getAreaDirectory().getAreaList().size() >= 1
                                && null != area.getSensorNetwork().getAthamaInhalerSensors() && area.getSensorNetwork().getAthamaInhalerSensors().size() >= 1) {
                            int y = r.nextInt(city.getAreaDirectory().getAreaList().size());
                            int x = r.nextInt(area.getSensorNetwork().getAthamaInhalerSensors().size());
                            AsthamaInhalerSensor aS = area.getSensorNetwork().getAthamaInhalerSensors().get(x);
                            if (null != aS) {
                                Area a = city.getAreaDirectory().getAreaList().get(y);
                                if (null != a) {
                                    aS.addUsage(randomTime, a);
                                }
                            }
                        }
                    }
                    for (Citizen citizen : area.getCitizenDirectory().getCitizenList()) {
                        VitalSign vs = citizen.getHealthReport().addVitalSigns();
                        if (citizen.getPerson().getAge() >= 1 || citizen.getPerson().getAge() <= 3) {
                            vs.setHeartRate((float) getRandomDouble(60, 150));
                            vs.setRespiratoryRate((float) getRandomDouble(10, 50));
                            vs.setSystolicBloodPressure((float) getRandomDouble(60, 130));
                            vs.setTimeStamp(new Timestamp(offset + (long) (Math.random() * diff)));
                            vs.setWeight((float) getRandomDouble(18, 36));
                        } else if (citizen.getPerson().getAge() >= 4 || citizen.getPerson().getAge() < 5) {
                            vs.setWeight((float) getRandomDouble(25, 45));
                            vs.setHeartRate((float) getRandomDouble(60, 140));
                            vs.setRespiratoryRate((float) getRandomDouble(10, 40));
                            vs.setSystolicBloodPressure((float) getRandomDouble(60, 130));
                            vs.setTimeStamp(new Timestamp(offset + (long) (Math.random() * diff)));
                        } else if (citizen.getPerson().getAge() >= 6 || citizen.getPerson().getAge() < 12) {
                            vs.setWeight((float) getRandomDouble(35, 120    ));
                            vs.setHeartRate((float) getRandomDouble(50, 150));
                            vs.setRespiratoryRate((float) getRandomDouble(10, 50));
                            vs.setSystolicBloodPressure((float) getRandomDouble(60, 130));
                            vs.setTimeStamp(new Timestamp(offset + (long) (Math.random() * diff)));
                        } else if (citizen.getPerson().getAge() >= 13) {
                            vs.setWeight((float) getRandomDouble(100, 250));
                            vs.setHeartRate((float) getRandomDouble(45, 110));
                            vs.setRespiratoryRate((float) getRandomDouble(8,25));
                            vs.setSystolicBloodPressure((float) getRandomDouble(90, 130));
                            vs.setTimeStamp(new Timestamp(offset + (long) (Math.random() * diff)));
                        }
                    }
                }
            }
        }
        private int fCount;
    }

    private final class StopLoading implements Runnable {

        StopLoading(ScheduledFuture<?> aSchedFuture) {
            fSchedFuture = aSchedFuture;
        }

        @Override
        public void run() {
            log("Stopping load.");
            fSchedFuture.cancel(DONT_INTERRUPT_IF_RUNNING);
            /* 
             Note that this Task also performs cleanup, by asking the 
             scheduler to shutdown gracefully. 
             */
            fScheduler.shutdown();
        }
        private ScheduledFuture<?> fSchedFuture;
    }

    public void loadSesnors() {

    }

    private double getRandomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
