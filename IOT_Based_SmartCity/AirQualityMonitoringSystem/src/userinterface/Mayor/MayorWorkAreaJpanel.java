/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.Mayor;

import Business.EcoSystem;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import business.Area.Area;
import business.City.City;
import business.sensors.PollutionMonitorSensorUsage;
import controller.BarChartForAveragePollution;
import controller.DataProcessor.AirQualityProcessor;
import controller.DataProcessor.InhalerDataProcessor;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aks
 */
public class MayorWorkAreaJpanel extends javax.swing.JPanel {

    boolean addVitalSigns;
    private JPanel userProcessContainer;
    private UserAccount account;
    private Organization organization;
    //private Area area;
    private City city;
    private EcoSystem business;

    /**
     * Creates new form CitizenWorkAreaJPanel
     */
    public MayorWorkAreaJpanel(JPanel userProcessContainer, UserAccount account, Organization organization, Area area, City city, EcoSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        //this.area = area;
        this.city = city;
        this.business = business;
        loadModeCombo();
        loadAreaCombo();
        populateInhalerData();
    }

    public void loadModeCombo() {
        modeCombo.removeAllItems();
        modeCombo.addItem("Indoor");
        modeCombo.addItem("Outdoor");
        modeCombo.setSelectedItem("Outdoor");

    }

    public void loadAreaCombo() {
        areaCombo.removeAllItems();
        for (Area area : city.getAreaDirectory().getAreaList()) {
            areaCombo.addItem(area);
        }
    }

    public void resetFields() {
        o3.setText("");
        co.setText("");
        so2.setText("");
        no2.setText("");
        pb.setText("");
    }

    public void populateAreaWisePollutionTable(Area area) {
        DefaultTableModel dtm = (DefaultTableModel) areaWisePollutionLevelTable.getModel();
        dtm.setRowCount(0);
        ArrayList<Date> keyList = new ArrayList<>(area.getSensor().getUsageHistory().keySet());
        Collections.sort(keyList);
        for (Date d : keyList) {
            PollutionMonitorSensorUsage usage = area.getSensor().getUsageHistory().get(d);
            if (null != usage && null != usage.getDate()) {
                Object row[] = new Object[2];
                row[0] = usage;
                if (usage.isPolluted()) {
                    row[1] = "Polluted";
                } else {
                    row[1] = "Clean";
                }
                dtm.addRow(row);
            }
        }
    }

    public void populatePollutionLevelTable(boolean indoorDoor) {
        DefaultTableModel dtm = (DefaultTableModel) areaAirQualityTable.getModel();
        dtm.setRowCount(0);
        for (Area area : city.getAreaDirectory().getAreaList()) {
            AirQualityProcessor processor = new AirQualityProcessor();
            PollutionMonitorSensorUsage usage;
            if (indoorDoor) {
                usage = processor.determineIndoorAirQuality(area);
            } else {
                usage = processor.DetermineAreaAirQuality(area);
            }
            if (null != usage) {
                Object row[] = new Object[3];
                row[0] = area;
                if (usage.isPolluted()) {
                    row[1] = "Polluted";
                } else {
                    row[1] = "Clean";
                }
                row[2] = usage;
                dtm.addRow(row);
            }
        }

        /**
         * ArrayList<Date> keyList = new
         * ArrayList<>(area.getSensor().getUsageHistory().keySet());
         * Collections.sort(keyList); for (Date d : keyList) {
         * PollutionMonitorSensorUsage usage =
         * area.getSensor().getUsageHistory().get(d); if (null != usage && null
         * != usage.getDate()) { Object row[] = new Object[2]; row[0] = usage;
         * if (usage.isPolluted()) { row[1] = "Polluted"; } else { row[1] =
         * "Clean"; } dtm.addRow(row); }
        }
         */
    }

    public void populateInhalerData() {
        DefaultTableModel dtm = (DefaultTableModel) inhalerUsageTable.getModel();
        dtm.setRowCount(0);
        for (Area area : city.getAreaDirectory().getAreaList()) {
            InhalerDataProcessor processor = new InhalerDataProcessor();
            int usage = processor.getAreaWiseInhalerUsage(area, city);
            Object row[] = new Object[2];
            row[0] = area;
            row[1] = usage;
            dtm.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        areaCombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaWisePollutionLevelTable = new javax.swing.JTable();
        showLevels1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        o4 = new javax.swing.JTextField();
        co1 = new javax.swing.JTextField();
        pb1 = new javax.swing.JTextField();
        so3 = new javax.swing.JTextField();
        no3 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        inhalerUsageTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaAirQualityTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        showLevels = new javax.swing.JButton();
        o3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        co = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        pb = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        so2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        no2 = new javax.swing.JTextField();
        modeCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        barChartButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Mayor Work Area JPanel");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        areaCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        areaCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaComboActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 204));
        jLabel3.setText("Select Area");

        areaWisePollutionLevelTable.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        areaWisePollutionLevelTable.setForeground(new java.awt.Color(0, 102, 204));
        areaWisePollutionLevelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Time Stamp", "Air Quality"
            }
        ));
        jScrollPane2.setViewportView(areaWisePollutionLevelTable);

        showLevels1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        showLevels1.setForeground(new java.awt.Color(0, 102, 204));
        showLevels1.setText("Show Levels");
        showLevels1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLevels1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 204));
        jLabel14.setText("Ozone Level");

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 204));
        jLabel15.setText("Carbon Monoxide Level");

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 204));
        jLabel16.setText("Lead Level");

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 204));
        jLabel17.setText("Sulphur Dioxide Level");

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 204));
        jLabel18.setText("Nitrogen Dioxide Level");

        o4.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        o4.setForeground(new java.awt.Color(0, 102, 204));
        o4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o4ActionPerformed(evt);
            }
        });

        co1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        co1.setForeground(new java.awt.Color(0, 102, 204));

        pb1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        pb1.setForeground(new java.awt.Color(0, 102, 204));

        so3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        so3.setForeground(new java.awt.Color(0, 102, 204));

        no3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        no3.setForeground(new java.awt.Color(0, 102, 204));
        no3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(areaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel16))
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(so3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pb1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(no3)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel14))
                                        .addGap(43, 43, 43)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(co1)
                                            .addComponent(o4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(showLevels1))
                                .addGap(157, 157, 157)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(areaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(showLevels1)
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(o4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(co1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(pb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(so3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(no3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );

        jTabbedPane2.addTab("Area wise Air Pollution", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        inhalerUsageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Area", "Usgae Number"
            }
        ));
        jScrollPane3.setViewportView(inhalerUsageTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304))
        );

        jTabbedPane2.addTab("Area Wise Asthama Inhaler Usage", jPanel5);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(0, 51, 204));

        areaAirQualityTable.setForeground(new java.awt.Color(0, 51, 204));
        areaAirQualityTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Area", "Air Quality", "TimeStamp"
            }
        ));
        jScrollPane1.setViewportView(areaAirQualityTable);

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 204));
        jLabel9.setText("Average Ozone Level");

        showLevels.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        showLevels.setForeground(new java.awt.Color(0, 51, 204));
        showLevels.setText("Show Levels");
        showLevels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLevelsActionPerformed(evt);
            }
        });

        o3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        o3.setForeground(new java.awt.Color(0, 51, 204));
        o3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 204));
        jLabel10.setText("Average Carbon Monoxide Level");

        co.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        co.setForeground(new java.awt.Color(0, 51, 204));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 204));
        jLabel11.setText("Average Lead Level");

        pb.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        pb.setForeground(new java.awt.Color(0, 51, 204));
        pb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pbActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 204));
        jLabel12.setText("Average Sulphur Dioxide Level");

        so2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        so2.setForeground(new java.awt.Color(0, 51, 204));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 204));
        jLabel13.setText("Average Nitrogen Dioxide Level");

        no2.setForeground(new java.awt.Color(0, 51, 204));
        no2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no2ActionPerformed(evt);
            }
        });

        modeCombo.setForeground(new java.awt.Color(0, 51, 204));
        modeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        modeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeComboActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Select Report Mode");

        jButton1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 255));
        jButton1.setText("Curb Pollution in Area");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        barChartButton.setBackground(new java.awt.Color(0, 51, 204));
        barChartButton.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        barChartButton.setForeground(new java.awt.Color(255, 255, 255));
        barChartButton.setText("Generate Bar Chart");
        barChartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barChartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(co, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11))
                            .addGap(48, 48, 48)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(so2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pb, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(no2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(showLevels)
                                    .addGap(79, 79, 79))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(o3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(barChartButton)
                        .addGap(6, 6, 6)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(modeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(barChartButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showLevels)
                    .addComponent(jButton1))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(o3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(co, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(so2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(no2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(54, 54, 54))
        );

        jTabbedPane2.addTab("Average Pollution Level Reports", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Area Wise Pollution Report", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showLevelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showLevelsActionPerformed
        resetFields();
        int row = areaAirQualityTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            PollutionMonitorSensorUsage usage = (PollutionMonitorSensorUsage) areaAirQualityTable.getValueAt(row, 2);
            if (null != usage) {
                o3.setText(String.valueOf(usage.getOzoneLevel()));
                if (usage.getOzoneLevel() > usage.getO3Threshold()) {
                    o3.setBackground(Color.pink);
                } else {
                    o3.setBackground(Color.green);
                }

                co.setText(String.valueOf(usage.getCarbonMonoxideLevel()));
                if (usage.getCarbonMonoxideLevel() > usage.getCoThreshold()) {
                    co.setBackground(Color.pink);
                } else {
                    co.setBackground(Color.green);
                }

                no2.setText(String.valueOf(usage.getNitrogenDioxideLevel()));
                if (usage.getNitrogenDioxideLevel() > usage.getNo2Threshold()) {
                    no2.setBackground(Color.pink);
                } else {
                    no2.setBackground(Color.green);
                }

                so2.setText(String.valueOf(usage.getSulfurDioxideLevel()));
                if (usage.getSulfurDioxideLevel() > usage.getSo2Threshold()) {
                    so2.setBackground(Color.pink);
                } else {
                    so2.setBackground(Color.green);
                }

                pb.setText(String.valueOf(usage.getLeadLevel()));
                if (usage.getLeadLevel() > usage.getPbThreshold()) {
                    pb.setBackground(Color.pink);
                } else {
                    pb.setBackground(Color.green);
                }
            }
        }
    }//GEN-LAST:event_showLevelsActionPerformed

    private void o3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_o3ActionPerformed

    private void no2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no2ActionPerformed

    private void modeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeComboActionPerformed
        // TODO add your handling code here:
        String mode = (String) modeCombo.getSelectedItem();
        if (null != mode) {
            if (mode.equals("Outdoor")) {
                populatePollutionLevelTable(false);
            } else {
                populatePollutionLevelTable(true);
            }
            resetFields();
        }
    }//GEN-LAST:event_modeComboActionPerformed

    private void showLevels1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showLevels1ActionPerformed
        int row = areaWisePollutionLevelTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            PollutionMonitorSensorUsage usage = (PollutionMonitorSensorUsage) areaWisePollutionLevelTable.getValueAt(row, 0);
            if (null != usage) {
                o4.setText(String.valueOf(usage.getOzoneLevel()));
                if (usage.getOzoneLevel() > usage.getO3Threshold()) {
                    o4.setBackground(Color.pink);
                } else {
                    o4.setBackground(Color.green);
                }

                co1.setText(String.valueOf(usage.getCarbonMonoxideLevel()));
                if (usage.getCarbonMonoxideLevel() > usage.getCoThreshold()) {
                    co1.setBackground(Color.pink);
                } else {
                    co1.setBackground(Color.green);
                }

                no3.setText(String.valueOf(usage.getNitrogenDioxideLevel()));
                if (usage.getNitrogenDioxideLevel() > usage.getNo2Threshold()) {
                    no3.setBackground(Color.pink);
                } else {
                    no3.setBackground(Color.green);
                }

                so3.setText(String.valueOf(usage.getSulfurDioxideLevel()));
                if (usage.getSulfurDioxideLevel() > usage.getSo2Threshold()) {
                    so3.setBackground(Color.pink);
                } else {
                    so3.setBackground(Color.green);
                }

                pb1.setText(String.valueOf(usage.getLeadLevel()));
                if (usage.getLeadLevel() > usage.getPbThreshold()) {
                    pb1.setBackground(Color.pink);
                } else {
                    pb1.setBackground(Color.green);
                }
            }
        }
    }//GEN-LAST:event_showLevels1ActionPerformed

    private void o4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_o4ActionPerformed

    private void no3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no3ActionPerformed

    private void areaComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaComboActionPerformed
        Area area = (Area) areaCombo.getSelectedItem();
        if (null != area) {
            this.populateAreaWisePollutionTable(area);
        }
    }//GEN-LAST:event_areaComboActionPerformed

    private void pbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pbActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedRow = 0;
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            Area area = (Area) areaAirQualityTable.getValueAt(selectedRow, 0);
            SendCurbPollutionRequestJPanel manageAreaJPanel = new SendCurbPollutionRequestJPanel(userProcessContainer, account, organization, area);
            userProcessContainer.add("manageAreaJPanel", manageAreaJPanel);

            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void barChartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barChartButtonActionPerformed
        boolean flag = false;
        if(null != modeCombo.getSelectedItem()){
            if(((String)modeCombo.getSelectedItem()).equals("Indoor")){
                flag = true;
            }
        }
        BarChartForAveragePollution barCharter = new BarChartForAveragePollution();
       barCharter.plotChart(city, flag);
    }//GEN-LAST:event_barChartButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable areaAirQualityTable;
    private javax.swing.JComboBox areaCombo;
    private javax.swing.JTable areaWisePollutionLevelTable;
    private javax.swing.JButton barChartButton;
    private javax.swing.JTextField co;
    private javax.swing.JTextField co1;
    private javax.swing.JTable inhalerUsageTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JComboBox modeCombo;
    private javax.swing.JTextField no2;
    private javax.swing.JTextField no3;
    private javax.swing.JTextField o3;
    private javax.swing.JTextField o4;
    private javax.swing.JTextField pb;
    private javax.swing.JTextField pb1;
    private javax.swing.JButton showLevels;
    private javax.swing.JButton showLevels1;
    private javax.swing.JTextField so2;
    private javax.swing.JTextField so3;
    // End of variables declaration//GEN-END:variables
}
