/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConfDBWizardView.java
 *
 * Created on Jul 28, 2009, 7:32:44 PM
 */

package com.ijunit.swing.view;

import javax.swing.BorderFactory;

/**
 *
 * @author vcjain
 */
public class ConfDBWizardView extends javax.swing.JPanel {

	public final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public final String DATABASEURL = "jdbrc:oracle:thin";
	public final String PORT = "1521";
    /** Creates new form ConfDBWizardView */
    public ConfDBWizardView() {
        initComponents();
        prepareComponents();
    }

    public void prepareComponents() {
    	getDriverTextFld().setText(DRIVER);
    	getDbURLTextFld().setText(DATABASEURL);
    	getPortTextFld().setText(PORT);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        databaseTextFld = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        driverTextFld = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dbURLTextFld = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        hostTextFld = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        portTextFld = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        userNameTextFld = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        passwordTextFld = new javax.swing.JPasswordField();
        testConfBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(479, 305));

        //jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(479, 305));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabel1.setText("Configuration Wizard");
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(" Datum-Gen DB Properties"));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(484, 310));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel3.setText("Database:");
        jLabel3.setName("jLabel3"); // NOI18N

        databaseTextFld.setColumns(25);
        databaseTextFld.setToolTipText("Database name");
        databaseTextFld.setName("databaseTextFld"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel4.setText("Driver:");
        jLabel4.setName("jLabel4"); // NOI18N

        driverTextFld.setColumns(50);
        driverTextFld.setToolTipText("like oracle.jdbc.OracleDriver");
        driverTextFld.setName("driverTextFld"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel5.setText("Database Url:");
        jLabel5.setName("jLabel5"); // NOI18N

        dbURLTextFld.setColumns(50);
        dbURLTextFld.setToolTipText("like jdbc:oracle:thin");
        dbURLTextFld.setName("dbURLTextFld"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel6.setText("Host:");
        jLabel6.setName("jLabel6"); // NOI18N

        hostTextFld.setColumns(20);
        hostTextFld.setToolTipText("like 192.168.100.65");
        hostTextFld.setName("hostTextFld"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel7.setText("Port:");
        jLabel7.setName("jLabel7"); // NOI18N

        portTextFld.setColumns(6);
        portTextFld.setToolTipText("like..1521");
        portTextFld.setName("portTextFld"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel8.setText("Username:");
        jLabel8.setName("jLabel8"); // NOI18N

        userNameTextFld.setColumns(20);
        userNameTextFld.setToolTipText("like.. scott");
        userNameTextFld.setName("userNameTextFld"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel9.setText("Password:");
        jLabel9.setName("jLabel9"); // NOI18N

        passwordTextFld.setColumns(20);
        passwordTextFld.setToolTipText("");
        passwordTextFld.setName("passwordTextFld"); // NOI18N

        testConfBtn.setFont(new java.awt.Font("Calibri", 0, 12));
        testConfBtn.setText("Test");
        testConfBtn.setName("testConfBtn"); // NOI18N
        testConfBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testConfBtnAction(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(databaseTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hostTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordTextFld, 0, 0, Short.MAX_VALUE)
                            .addComponent(userNameTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addComponent(testConfBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(portTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbURLTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driverTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databaseTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(driverTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dbURLTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hostTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(portTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userNameTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(testConfBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void testConfBtnAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testConfBtnAction
        // TODO add your handling code here:
    }//GEN-LAST:event_testConfBtnAction


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField databaseTextFld;
    private javax.swing.JTextField dbURLTextFld;
    private javax.swing.JTextField driverTextFld;
    private javax.swing.JTextField hostTextFld;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField passwordTextFld;
    private javax.swing.JFormattedTextField portTextFld;
    private javax.swing.JButton testConfBtn;
    private javax.swing.JTextField userNameTextFld;
    // End of variables declaration//GEN-END:variables
	/**
	 * @return the databaseTextFld
	 */
	public javax.swing.JTextField getDatabaseTextFld() {
		return databaseTextFld;
	}

	/**
	 * @return the dbURLTextFld
	 */
	public javax.swing.JTextField getDbURLTextFld() {
		return dbURLTextFld;
	}

	/**
	 * @return the driverTextFld
	 */
	public javax.swing.JTextField getDriverTextFld() {
		return driverTextFld;
	}

	/**
	 * @return the hostTextFld
	 */
	public javax.swing.JTextField getHostTextFld() {
		return hostTextFld;
	}

	/**
	 * @return the passwordTextFld
	 */
	public javax.swing.JPasswordField getPasswordTextFld() {
		return passwordTextFld;
	}

	/**
	 * @return the portTextFld
	 */
	public javax.swing.JFormattedTextField getPortTextFld() {
		return portTextFld;
	}

	/**
	 * @return the testConfBtn
	 */
	public javax.swing.JButton getTestConfBtn() {
		return testConfBtn;
	}

	/**
	 * @return the userNameTextFld
	 */
	public javax.swing.JTextField getUserNameTextFld() {
		return userNameTextFld;
	}

}
