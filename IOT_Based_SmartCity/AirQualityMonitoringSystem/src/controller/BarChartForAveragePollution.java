/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Area.Area;
import business.City.City;
import business.sensors.PollutionMonitorSensorUsage;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import static com.googlecode.charts4j.Color.GOLD;
import static com.googlecode.charts4j.Color.LIGHTGREY;
import static com.googlecode.charts4j.Color.SILVER;
import static com.googlecode.charts4j.Color.BLACK;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plots;
import static com.googlecode.charts4j.UrlUtil.normalize;
import controller.DataProcessor.AirQualityProcessor;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Aks
 */
public class BarChartForAveragePollution {
    public void plotChart(City city,boolean inDoor) {
        ArrayList<Double> o3List = new ArrayList<>();
        ArrayList<Double> coList = new ArrayList<>();
        ArrayList<Double> no2List = new ArrayList<>();
        ArrayList<Double> so2List = new ArrayList<>();
        ArrayList<Double> pbList = new ArrayList<>();
        String[] areaArray = new String[city.getAreaDirectory().getAreaList().size()];
        for (Area area : city.getAreaDirectory().getAreaList()) {
            areaArray[city.getAreaDirectory().getAreaList().indexOf(area)] = area.getName();
            AirQualityProcessor processor = new AirQualityProcessor();
            PollutionMonitorSensorUsage usage;
            if (inDoor) {
                usage = processor.determineIndoorAirQuality(area);
            } else {
                usage = processor.DetermineAreaAirQuality(area);
            }
            if (null != usage) {
                o3List.add(usage.getOzoneLevel());
                coList.add(usage.getCarbonMonoxideLevel());
                no2List.add(usage.getNitrogenDioxideLevel());
                so2List.add(usage.getSulfurDioxideLevel());
                pbList.add(usage.getLeadLevel());
            }
            }
        final double MAX_POLLUTION = 200.0;
        Data o3Data= DataUtil.scaleWithinRange(0, MAX_POLLUTION, o3List);
        Data coData= DataUtil.scaleWithinRange(0, MAX_POLLUTION, coList);
        Data no2Data= DataUtil.scaleWithinRange(0, MAX_POLLUTION, no2List);
        Data so2Data= DataUtil.scaleWithinRange(0, MAX_POLLUTION, so2List);
        Data pbData= DataUtil.scaleWithinRange(0, MAX_POLLUTION, pbList);
        BarChartPlot o3 = Plots.newBarChartPlot(o3Data, GOLD, "Ozone");
        BarChartPlot co = Plots.newBarChartPlot(coData, SILVER, "CarbonMonoxide");
        BarChartPlot no2 = Plots.newBarChartPlot(no2Data, Color.BROWN, "Nitrogen Dioxide");
        BarChartPlot so2 = Plots.newBarChartPlot(so2Data, Color.BLUE, "Sulphur Dioxide");
        BarChartPlot pb = Plots.newBarChartPlot(pbData, Color.GREENYELLOW, "Leade ");
        BarChart chart = GCharts.newBarChart(o3,co,no2,so2,pb);

        // Defining axis info and styles
        AxisStyle axisStyle = AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER);
        AxisLabels area = AxisLabelsFactory.newAxisLabels("Area", 50.0);
        area.setAxisStyle(axisStyle);
        AxisLabels areas = AxisLabelsFactory.newAxisLabels(areaArray);
        areas.setAxisStyle(axisStyle);
        AxisLabels pollutionLevels = AxisLabelsFactory.newAxisLabels("Pollution Level in PPM/m^3", 50.0);
        pollutionLevels.setAxisStyle(axisStyle);
        AxisLabels pollutionCount = AxisLabelsFactory.newNumericRangeAxisLabels(0, MAX_POLLUTION);
        pollutionCount.setAxisStyle(axisStyle);


        // Adding axis info to chart.
        chart.addXAxisLabels(pollutionCount);
        chart.addXAxisLabels(pollutionLevels);
        chart.addYAxisLabels(areas);
        chart.addYAxisLabels(area);
        chart.addTopAxisLabels(pollutionCount);
        chart.setHorizontal(true);
        chart.setSize(450, 650);
        chart.setSpaceBetweenGroupsOfBars(30);
        if(inDoor){
        chart.setTitle("Area wise average Indoor Pollution", BLACK, 16);
        }else{
            chart.setTitle("Area wise average OutDoor Pollution", BLACK, 16);
        }
        ///51 is the max number of medals.
        chart.setGrid((50.0/MAX_POLLUTION)*20, 600, 3, 2);
        chart.setBackgroundFill(Fills.newSolidFill(LIGHTGREY));
        LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.newColor("E37600"), 100);
        fill.addColorAndOffset(Color.newColor("DC4800"), 0);
        chart.setAreaFill(fill);
        String url = chart.toURLString();
        // EXAMPLE CODE END. Use this url string in your web or
        // Internet application.
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        try {
            label = new JLabel(new ImageIcon(ImageIO.read(new URL(url))));
        } catch (MalformedURLException ex) {
            Logger.getLogger(BarChartForAveragePollution.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BarChartForAveragePollution.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        /**Logger.global.info(url);
        String expectedString = "http://chart.apis.google.com/chart?chf=bg,s,D3D3D3|c,lg,0,E37600,1.0,DC4800,0.0&chs=450x650&chd=e:..tLc3X2UF,aWvraWQUMj,jItLjIS0S0&chtt=2008+Beijing+Olympics+Medal+Count&chts=000000,16&chg=19.6078431372549,600.0,3,2&chxt=y,y,t,x,x&chxl=0:|Germany|United+Kingdom|Russia|USA|China|1:|Country|4:|Medals&chxs=0,000000,13,0|1,000000,13,0|2,000000,13,0|3,000000,13,0|4,000000,13,0&chxp=1,50.0|4,50.0&chxr=1,0.0,100.0|2,0.0,51.0|3,0.0,51.0|4,0.0,100.0&chdl=Gold|Silver|Bronze&chco=FFD700,C0C0C0,A52A2A&chbh=23,4,30&cht=bhg";
        assertEquals("Junit error", normalize(expectedString), normalize(url));*/
    }
}
