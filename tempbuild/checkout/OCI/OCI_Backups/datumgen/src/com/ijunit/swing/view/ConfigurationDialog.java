/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConfigurationDialog.java
 *
 * Created on Mar 5, 2009, 2:32:07 PM
 */

package com.ijunit.swing.view;

import com.ijunit.common.Logger;
import com.ijunit.swing.action.ConfigurationDialogAction;

/**
 *
 * @author vcjain
 */
public class ConfigurationDialog extends javax.swing.JDialog {

    static Logger logger = Logger.getLogger(ConfigurationDialog.class);
    
    ConfigurationDialogAction action;
    
    /** Creates new form ConfigurationDialog */
    public ConfigurationDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        action = new ConfigurationDialogAction(this);
        initComponents();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        browseBtn = new javax.swing.JButton();
        rootTextFld = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        classpathTextFld = new javax.swing.JTextField();
        classPathBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        databaseTextFld = new javax.swing.JTextField();
        driverTextFld = new javax.swing.JTextField();
        dbURLTextFld = new javax.swing.JTextField();
        hostTextFld = new javax.swing.JTextField();
        userNameTextFld = new javax.swing.JTextField();
        passwordTextFld = new javax.swing.JPasswordField();
        portTextFld = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        submitBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        testConfBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuration");

        jPanel1.setName("jPanel1"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Path"));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setText("Specify Root folder where iJunit will keep generated files.");
        jLabel1.setName("jLabel1"); // NOI18N

        browseBtn.setText("Browse");
        browseBtn.setActionCommand("rootFolderBrowse");
        browseBtn.setName("browseButton"); // NOI18N
        browseBtn.addActionListener(action);

        rootTextFld.setName("rootTextField"); // NOI18N
        rootTextFld.addActionListener(action);

        jLabel2.setText("Root Path");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel11.setText("Specify path to application classes.");
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText("Class Path");
        jLabel12.setName("jLabel12"); // NOI18N

        classpathTextFld.setToolTipText("like..c:\\eclispe..\\build\\classes");
        classpathTextFld.setName("classpathTextFld"); // NOI18N

        classPathBtn.setText("Browse");
        classPathBtn.setActionCommand("classPathBrowse");
        classPathBtn.setName("classPathBtn"); // NOI18N
        classPathBtn.addActionListener(action);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rootTextFld, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(81, 81, 81)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classpathTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(classPathBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rootTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseBtn)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(classpathTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classPathBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Database properties"));
        jPanel3.setToolTipText("Database name");
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel3.setText("Database:");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Driver:");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Database Url:");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("Host:");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("Port:");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText("Username:");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText("Password:");
        jLabel9.setName("jLabel9"); // NOI18N

        databaseTextFld.setColumns(25);
        databaseTextFld.setToolTipText("Database name");
        databaseTextFld.setName("databaseTF"); // NOI18N

        driverTextFld.setColumns(50);
        driverTextFld.setToolTipText("like oracle.jdbc.OracleDriver");
        driverTextFld.setName("driverTextField"); // NOI18N

        dbURLTextFld.setColumns(50);
        dbURLTextFld.setToolTipText("like jdbc:oracle:thin");
        dbURLTextFld.setName("dbURLTextFiels"); // NOI18N

        hostTextFld.setColumns(20);
        hostTextFld.setToolTipText("like 192.168.100.65");
        hostTextFld.setName("hostTextField"); // NOI18N

        userNameTextFld.setColumns(20);
        userNameTextFld.setToolTipText("like.. scott");
        userNameTextFld.setName("userNameTextField"); // NOI18N

        passwordTextFld.setColumns(20);
        passwordTextFld.setToolTipText("");
        passwordTextFld.setName("passwordField"); // NOI18N

        portTextFld.setColumns(6);
        portTextFld.setToolTipText("like..1521");
        portTextFld.setName("portTextFld"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
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
                    .addComponent(databaseTextFld, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(driverTextFld, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(dbURLTextFld, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(hostTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(passwordTextFld, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(userNameTextFld, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                    .addComponent(portTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databaseTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driverTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dbURLTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setName("jPanel4"); // NOI18N

        submitBtn.setText("Submit");
        submitBtn.setName("submitBtn"); // NOI18N
        submitBtn.addActionListener(action);

        cancelBtn.setText("Cancel");
        cancelBtn.setName("cancelBtn"); // NOI18N
        cancelBtn.addActionListener(action);

        testConfBtn.setText("Test");
        testConfBtn.setName("testConfBtn"); // NOI18N
        testConfBtn.addActionListener(action);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(testConfBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(submitBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(testConfBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel10.setBackground(new java.awt.Color(153, 153, 153));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel10.setText("iJunit Configuration ");
        jLabel10.setName("jLabel10"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(152, 152, 152))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rootTextFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rootTextFldActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_rootTextFldActionPerformed


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConfigurationDialog dialog = new ConfigurationDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton classPathBtn;
    private javax.swing.JTextField classpathTextFld;
    private javax.swing.JTextField databaseTextFld;
    private javax.swing.JTextField dbURLTextFld;
    private javax.swing.JTextField driverTextFld;
    private javax.swing.JTextField hostTextFld;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField passwordTextFld;
    private javax.swing.JFormattedTextField portTextFld;
    private javax.swing.JTextField rootTextFld;
    private javax.swing.JButton submitBtn;
    private javax.swing.JButton testConfBtn;
    private javax.swing.JTextField userNameTextFld;
    // End of variables declaration//GEN-END:variables


	public javax.swing.JButton getBrowseBtn() {
		return browseBtn;
	}

	public void setBrowseBtn(javax.swing.JButton browseBtn) {
		this.browseBtn = browseBtn;
	}

	public javax.swing.JButton getCancelBtn() {
		return cancelBtn;
	}

	public void setCancelBtn(javax.swing.JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public javax.swing.JButton getClassPathBtn() {
		return classPathBtn;
	}

	public void setClassPathBtn(javax.swing.JButton classPathBtn) {
		this.classPathBtn = classPathBtn;
	}

	public javax.swing.JTextField getClasspathTextFld() {
		return classpathTextFld;
	}

	public void setClasspathTextFld(javax.swing.JTextField classpathTextFld) {
		this.classpathTextFld = classpathTextFld;
	}

	public javax.swing.JTextField getDatabaseTextFld() {
		return databaseTextFld;
	}

	public void setDatabaseTextFld(javax.swing.JTextField databaseTextFld) {
		this.databaseTextFld = databaseTextFld;
	}

	public javax.swing.JTextField getDbURLTextFld() {
		return dbURLTextFld;
	}

	public void setDbURLTextFld(javax.swing.JTextField dbURLTextFld) {
		this.dbURLTextFld = dbURLTextFld;
	}

	public javax.swing.JTextField getDriverTextFld() {
		return driverTextFld;
	}

	public void setDriverTextFld(javax.swing.JTextField driverTextFld) {
		this.driverTextFld = driverTextFld;
	}

	public javax.swing.JTextField getHostTextFld() {
		return hostTextFld;
	}

	public void setHostTextFld(javax.swing.JTextField hostTextFld) {
		this.hostTextFld = hostTextFld;
	}

	public javax.swing.JPasswordField getPasswordTextFld() {
		return passwordTextFld;
	}

	public void setPasswordTextFld(javax.swing.JPasswordField passwordTextFld) {
		this.passwordTextFld = passwordTextFld;
	}

	public javax.swing.JFormattedTextField getPortTextFld() {
		return portTextFld;
	}

	public void setPortTextFld(javax.swing.JFormattedTextField portTextFld) {
		this.portTextFld = portTextFld;
	}

	public javax.swing.JTextField getRootTextFld() {
		return rootTextFld;
	}

	public void setRootTextFld(javax.swing.JTextField rootTextFld) {
		this.rootTextFld = rootTextFld;
	}

	public javax.swing.JButton getSubmitBtn() {
		return submitBtn;
	}

	public void setSubmitBtn(javax.swing.JButton submitBtn) {
		this.submitBtn = submitBtn;
	}

	public javax.swing.JButton getTestConfBtn() {
		return testConfBtn;
	}

	public void setTestConfBtn(javax.swing.JButton testConfBtn) {
		this.testConfBtn = testConfBtn;
	}

	public javax.swing.JTextField getUserNameTextFld() {
		return userNameTextFld;
	}

	public void setUserNameTextFld(javax.swing.JTextField userNameTextFld) {
		this.userNameTextFld = userNameTextFld;
	}

}