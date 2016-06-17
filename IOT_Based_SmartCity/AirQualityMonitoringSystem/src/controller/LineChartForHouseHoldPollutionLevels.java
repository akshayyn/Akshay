/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ /**
 *
 * The MIT License
 *
 * Copyright (c) 2008 the original author or authors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package controller;


import static Business.Organization.Organization.Type.Citizen;
import Citizen.Citizen;
import business.sensors.PollutionMonitorSensorUsage;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Color;
import static com.googlecode.charts4j.Color.*;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;
import static com.googlecode.charts4j.UrlUtil.normalize;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import static org.junit.Assert.assertEquals;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Aks
 */


public class LineChartForHouseHoldPollutionLevels {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Logger.global.setLevel(Level.ALL);
    }

    @Test
    public void generateLineChart(Citizen c) {

        // EXAMPLE CODE START
        ArrayList<Date> keyList = new ArrayList<>(c.getResidence().getPollutionMonitorSensor().getUsageHistory().keySet());
        Collections.sort(keyList);
        // Defining lines

        final double[] ozone = new double[keyList.size()];
        final double[] no2 = new double[keyList.size()];
        final double[] co = new double[keyList.size()];
        final double[] so2 = new double[keyList.size()];
        final double[] pb = new double[keyList.size()];
        for (Date d : keyList) {
            PollutionMonitorSensorUsage usage = c.getResidence().getPollutionMonitorSensor().getUsageHistory().get(d);
            ozone[keyList.indexOf(d)] = usage.getOzoneLevel();
            no2[keyList.indexOf(d)] = usage.getNitrogenDioxideLevel();
            co[keyList.indexOf(d)] = usage.getCarbonMonoxideLevel();
            so2[keyList.indexOf(d)] = usage.getSulfurDioxideLevel();
            pb[keyList.indexOf(d)] = usage.getLeadLevel();

        }
        
        Line line1 = Plots.newLine(Data.newData(ozone), Color.newColor("3377ff"), "Ozone Level");
        line1.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line1.addShapeMarkers(Shape.CIRCLE, Color.newColor("ccddff"), 12);
        
        Line line2 = Plots.newLine(Data.newData(no2), Color.newColor("8600b3"), "NO2 Level");
        line2.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line2.addShapeMarkers(Shape.CIRCLE, Color.newColor("f2ccff"), 12);
        
        Line line3 = Plots.newLine(Data.newData(co), Color.newColor("ff3300"), "CO Level");
        line3.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line3.addShapeMarkers(Shape.CIRCLE, Color.newColor("ffd6cc"), 12);
        
        Line line4 = Plots.newLine(Data.newData(so2), Color.newColor("00e64d"), "So2 Level");
        line4.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line4.addShapeMarkers(Shape.CIRCLE, Color.newColor("ccffdd"), 12);
        
        Line line5 = Plots.newLine(Data.newData(pb), Color.newColor("ffff00"), "Lead Level");
        line5.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line5.addShapeMarkers(Shape.CIRCLE, Color.newColor("ffffe5"), 12);
        // Defining chart.
        LineChart chart = GCharts.newLineChart(line1, line2,line3,line4,line5);
        chart.setSize(750,400);
        chart.setTitle("Air Pollution Levels Over Period)", WHITE, 14);
        //chart.addHorizontalRangeMarker(40, 60, Color.newColor(RED, 30));
        //chart.addVerticalRangeMarker(70, 90, Color.newColor(GREEN, 30));
        chart.setGrid(25, 25, 3, 2);

        // Defining axis info and styles
        AxisStyle axisStyle = AxisStyle.newAxisStyle(WHITE, 12, AxisTextAlignment.CENTER);
        String[] dateArray = new String[keyList.size()];
        for(Date d: keyList){
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date today = null;
            try {
               today = dateFormat.parse(dateFormat.format(d));
            } catch (ParseException ex) {
                Logger.getLogger(LineChartForHouseHoldPollutionLevels.class.getName()).log(Level.SEVERE, null, ex);
            }if(null !=  today){
            dateArray[keyList.indexOf(d)] = d.toString();
        }
        }
        AxisLabels xAxis = AxisLabelsFactory.newAxisLabels(dateArray);
        xAxis.setAxisStyle(axisStyle);
        
        AxisLabels yAxis = AxisLabelsFactory.newAxisLabels("", "5", "10", "15", "20","25", "30", "35", "40","45", "50", "55", "60","65", "70", "75", "80","85", "90", "95", "100","105", "110", "115", "120","125","130","135","140","145","150","155","160");
        AxisLabels xAxis3 = AxisLabelsFactory.newAxisLabels("Date", 50.0);
        xAxis3.setAxisStyle(AxisStyle.newAxisStyle(WHITE, 14, AxisTextAlignment.CENTER));
        yAxis.setAxisStyle(axisStyle);
        AxisLabels yAxis2 = AxisLabelsFactory.newAxisLabels("Levels in microgram/m^3", 50.0);
        yAxis2.setAxisStyle(AxisStyle.newAxisStyle(WHITE, 14, AxisTextAlignment.CENTER));
        yAxis2.setAxisStyle(axisStyle);

        // Adding axis info to chart.
        chart.addXAxisLabels(xAxis);
        
        chart.addXAxisLabels(xAxis3);
        chart.addYAxisLabels(yAxis);
        chart.addYAxisLabels(yAxis2);

        // Defining background and chart fills.
        chart.setBackgroundFill(Fills.newSolidFill(Color.newColor("003366")));
        LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.newColor("363433"), 100);
        fill.addColorAndOffset(Color.newColor("2E2B2A"), 0);
        chart.setAreaFill(fill);
        String url = chart.toURLString();
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        try {
            label = new JLabel(new ImageIcon(ImageIO.read(new URL(url))));
        } catch (MalformedURLException ex) {
            Logger.getLogger(LineChartForHouseHoldPollutionLevels.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LineChartForHouseHoldPollutionLevels.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        // EXAMPLE CODE END. Use this url string in your web or
        // Internet application.
        /**Logger.global.info(url);
        String expectedString = "http://chart.apis.google.com/chart?chco=CA3D05,87CEEB&chd=e:AAB4DhEzFxGnHrJRLhOZRmUpXCYZYpYAXCWfXCZIczhmmtrKuE,..-H8e7M6O5Y4U2u0exmuZrWo9nmnWn.o9pgo9m3jMeZZSU1R7&chdl=My+Website.com|Competition.com&chf=bg,s,1F1D1D|c,lg,0,363433,1.0,2E2B2A,0.0&chg=25.0,25.0,3,2&chls=3,1,0|3,1,0&chm=r,FF00004C,0,0.40,0.60|R,0080004C,0,0.70,0.90|d,CA3D05,0,-1,12,0|d,FFFFFF,0,-1,8,0|d,87CEEB,1,-1,12,0|d,FFFFFF,1,-1,8,0&chs=600x450&cht=lc&chts=FFFFFF,14&chtt=Web+Traffic%7C%28in+billions+of+hits%29&chxl=0:||25|50|75|100|1:|Hits|2:|Nov|Dec|Jan|Feb|Mar|3:|2007|2007|2008|2008|2008|4:|Month&chxp=1,50.0|4,50.0&chxr=1,0.0,100.0|4,0.0,100.0&chxs=0,FFFFFF,12,0|1,FFFFFF,12,0|2,FFFFFF,12,0|3,FFFFFF,12,0|4,FFFFFF,14,0&chxt=y,y,x,x,x";
        assertEquals("Junit error", normalize(expectedString), normalize(url));*/
    }
}
