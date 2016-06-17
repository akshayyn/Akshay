package userinterface.AdministrativeRole;

import Business.Organization.AdminOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.WorkRequest;
import Citizen.Citizen;
import business.Area.Area;
import business.City.City;
import business.Organization.CitizenOrganization;
import business.WorkQueue.CitizenRegistrationWorkRequest;
import bussinesslogic.email.SendMail;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aks
 */
public class ManageCitizensJPanel extends javax.swing.JPanel {

    JPanel userProcessContainer;
    Area area;
    City city;
    CitizenOrganization citizenOrg;

    /**
     * Creates new form ManageCitizensJPanel
     */
    public ManageCitizensJPanel(JPanel userProcessorContainer, Area area, City city) {
        initComponents();
        userProcessContainer = userProcessorContainer;
        this.area = area;
        this.city = city;
        for (Organization orgnization : area.getOrganizationDirectory().getOrganizationList()) {
            if (orgnization instanceof CitizenOrganization) {
                citizenOrg = (CitizenOrganization) orgnization;
            }
        }
        if (null == citizenOrg) {
            JOptionPane.showMessageDialog(null, "Citizen Organization not yet created for the area!");
            jButton1.setVisible(false);
            processButton.setEnabled(false);
        }
        changeVisiblity(false);
        populateRegisteredCitizensTable();
        populateRegistrationRequestsTable();
    }

    public void changeVisiblity(boolean flag) {
        userLabel.setVisible(flag);
        userNameField.setVisible(flag);
        passLabel.setVisible(flag);
        passwordField.setVisible(flag);
    }

    public void populateRegisteredCitizensTable() {
        if (null != area) {

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            model.setRowCount(0);

            if (null != citizenOrg) {

                for (UserAccount ua : citizenOrg.getUserAccountDirectory().getUserAccountList()) {
                    Object[] row = new Object[2];
                    row[0] = ua.getCitizen();
                    row[1] = ua.getCitizen().getCitizenId();
                    model.addRow(row);
                }
            }
        }
    }

    public void populateRegistrationRequestsTable() {
        if (null != area) {

            DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();

            model.setRowCount(0);
            for (WorkRequest request : area.getWorkQueue().getWorkRequestList()) {
                CitizenRegistrationWorkRequest workRequest = (CitizenRegistrationWorkRequest) request;
                Object[] row = new Object[2];
                row[0] = workRequest.getCitizen();
                row[1] = workRequest.getStatus();
                model.addRow(row);
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        processButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JTextField();
        userNameField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Manage Citizens");

        jScrollPane1.setBackground(new java.awt.Color(0, 51, 102));

        jTable1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 51, 153));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Citizen Name", "Citizen Id"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Registered Citizens");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Registration Requests");

        workRequestJTable.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        workRequestJTable.setForeground(new java.awt.Color(0, 51, 153));
        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Citizen Name", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(workRequestJTable);

        jButton1.setText("Assign User Account");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        processButton.setText("Process");
        processButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processButtonActionPerformed(evt);
            }
        });

        userLabel.setText("UserName");

        passLabel.setText("Password");

        userNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(processButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(passLabel)
                                .addGap(53, 53, 53)
                                .addComponent(passwordField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(userLabel)
                                .addGap(53, 53, 53)
                                .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(processButton)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Citizen citizen = null;
        String userName = userNameField.getText();
        String password = passwordField.getText();
        int selectedRow = workRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            citizen = (Citizen) workRequestJTable.getValueAt(selectedRow, 0);

            ArrayList<UserAccount> userAcoountList = new ArrayList<>();
            for (Area area : city.getAreaDirectory().getAreaList()) {
                for (Organization org : area.getOrganizationDirectory().getOrganizationList()) {
                    userAcoountList.addAll(org.getUserAccountDirectory().getUserAccountList());
                }
            }

            for (UserAccount ua : userAcoountList) {
                if (ua.getCitizen().getCitizenId() == citizen.getCitizenId()) {
                    JOptionPane.showMessageDialog(null, "User Account Already exists in th system!");
                    return;
                } else if (ua.getUsername().equals(userName)) {
                    JOptionPane.showMessageDialog(null, "UserName already taken");
                    return;
                }
            }
            UserAccount userAccnt = citizenOrg.getUserAccountDirectory().createUserAccount(userName, password, null, citizen, citizenOrg.getSupportedRole().get(0));
            DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();
            model.removeRow(selectedRow);
            populateRegisteredCitizensTable();
            JOptionPane.showMessageDialog(null, "User Account created Successfully!");
            SendMail mailer = new SendMail();
            mailer.sendRegistrationSuccessEmail(userAccnt);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void processButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processButtonActionPerformed
        Citizen citizen = null;
        int selectedRow = workRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            citizen = (Citizen) workRequestJTable.getValueAt(selectedRow, 0);
            changeVisiblity(true);
            userNameField.setText(citizen.getPerson().getFirstName() + citizen.getPerson().getLastName());

            passwordField.setText(citizen.getPhoneNumber());
        }
    }//GEN-LAST:event_processButtonActionPerformed

    private void userNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel passLabel;
    private javax.swing.JTextField passwordField;
    private javax.swing.JButton processButton;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userNameField;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}
