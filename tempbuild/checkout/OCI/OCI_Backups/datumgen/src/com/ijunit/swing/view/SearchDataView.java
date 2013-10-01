/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchDataView.java
 *
 * Created on Jun 30, 2009, 5:22:16 PM
 */

package com.ijunit.swing.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.ijunit.common.Logger;
import com.ijunit.swing.action.SearchDataAction;
import com.ijunit.swing.model.SearchDataTblModel;

/**
 *
 * @author vcjain
 */
public class SearchDataView extends javax.swing.JPanel {

	static Logger logger = Logger.getLogger(SearchDataView.class);
	
	private JFrame parentObject = null;
	private SearchDataAction action = null;
    /** Creates new form SearchDataView */
    public SearchDataView() {
        initComponents();
    } 

    /** Creates new form SearchDataView */
    public SearchDataView(JFrame parent) {
    	this.parentObject = parent;
    	action = new SearchDataAction(this);
        initComponents();
        prepareComponents();
    }
    
    public void prepareComponents(){
    	dataTable.setModel(new DefaultTableModel());
    	testClassTxtFld.addActionListener(action);
    	browseTestClassBtn.addActionListener(action);
    	browseDataClassBtn.addActionListener(action);
    	testDataTxtFld.addActionListener(action);
    	conditionNameCmb.addActionListener(action);
    	getExecuteBtn().addActionListener(action);
    	cancelBtn.addActionListener(action);
    	getNextBtn().addActionListener(action);
    	getPreviousBtn().addActionListener(action);
    	getRowCountTextFld().addFocusListener(action);
    	getRowCountTextFld().setText(String.valueOf(action.getRowsCount()));
    	
    	dataTable.setModel(new SearchDataTblModel());
    	TableColumn column = getDataTable().getColumnModel().getColumn(0);
		column.setMaxWidth(35);
    	
		appDataTable.setModel(new DefaultTableModel());
		appDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		conditionNameCmb.setModel(new DefaultComboBoxModel());
//    	Only for Testing purpose
//    	getTestClassTxtFld().setText("com.mbi.ejb.patient.PatientServiceBean");
//    	getTestDataTxtFld().setText("com.mbi.common.data.patient.PatientData");

    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        testClassTxtFld = new javax.swing.JTextField();
        browseTestClassBtn = new javax.swing.JButton();
        testDataTxtFld = new javax.swing.JTextField();
        browseDataClassBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        conditionNameCmb = new javax.swing.JComboBox();
        selectBtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        queryTextArea = new javax.swing.JTextArea();
        cancelBtn = new javax.swing.JButton();
        executeBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        rowCountTextFld = new javax.swing.JTextField();
        nextBtn = new javax.swing.JButton();
        previousBtn = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        appDataTable = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(1033, 667));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("Search Data");
        jLabel2.setName("jLabel2"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setName("jPanel5"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Tool Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 12))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        dataTable.setName("dataTable"); // NOI18N
        jScrollPane2.setViewportView(dataTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel4.setText("Test Class");
        jLabel4.setName("jLabel4"); // NOI18N

        testClassTxtFld.setName("testClassTxtFld"); // NOI18N
        testClassTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testClassTxtFldActionPerformed(evt);
            }
        });

        browseTestClassBtn.setFont(new java.awt.Font("Calibri", 0, 14));
        browseTestClassBtn.setText("Browse");
        browseTestClassBtn.setActionCommand("BrowseTestClass");
        browseTestClassBtn.setName("browseTestClassBtn"); // NOI18N
        browseTestClassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseTestClassBtnActionPerformed(evt);
            }
        });

        testDataTxtFld.setName("testDataTxtFld"); // NOI18N

        browseDataClassBtn.setFont(new java.awt.Font("Calibri", 0, 14));
        browseDataClassBtn.setText("Browse");
        browseDataClassBtn.setActionCommand("BrowseDataClass");
        browseDataClassBtn.setName("browseDataClassBtn"); // NOI18N
        browseDataClassBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseDataClassBtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel8.setText("Test Data Class");
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel7.setText("Condition Name");
        jLabel7.setName("jLabel7"); // NOI18N

        conditionNameCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        conditionNameCmb.setName("conditionNameCmb"); // NOI18N

        selectBtn.setText("Select");
        selectBtn.setName("selectBtn"); // NOI18N
        selectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(testClassTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(testDataTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(browseDataClassBtn)
                            .addComponent(browseTestClassBtn)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(conditionNameCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(selectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(testClassTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(testDataTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(browseTestClassBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseDataClassBtn)))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(conditionNameCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Application Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 12))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        queryTextArea.setColumns(20);
        queryTextArea.setRows(5);
        queryTextArea.setWrapStyleWord(true);
        queryTextArea.setName("queryTextArea"); // NOI18N
        jScrollPane4.setViewportView(queryTextArea);

        cancelBtn.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        cancelBtn.setText("Cancel");
        cancelBtn.setName("cancelBtn"); // NOI18N
        cancelBtn.setPreferredSize(new java.awt.Dimension(73, 25));
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        executeBtn.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        executeBtn.setText("Execute");
        executeBtn.setName("executeBtn"); // NOI18N
        executeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Maximum records per page");
        jLabel1.setName("jLabel1"); // NOI18N

        rowCountTextFld.setName("rowCountTextFld"); // NOI18N
        rowCountTextFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rowCountTextFldActionPerformed(evt);
            }
        });
        rowCountTextFld.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                rowCountTextFldFocusLost(evt);
            }
        });

        nextBtn.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        nextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijunit/resource/image/NextArrow.JPG"))); // NOI18N
        nextBtn.setToolTipText("Next Records");
        nextBtn.setName("nextBtn"); // NOI18N
        nextBtn.setPreferredSize(new java.awt.Dimension(73, 25));
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        previousBtn.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        previousBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijunit/resource/image/PreviousArrow.JPG"))); // NOI18N
        previousBtn.setToolTipText("Previous Records");
        previousBtn.setName("previousBtn"); // NOI18N
        previousBtn.setPreferredSize(new java.awt.Dimension(73, 25));
        previousBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rowCountTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(executeBtn))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(244, 244, 244))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rowCountTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(executeBtn)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(1017, 377));

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        appDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        appDataTable.setName("appDataTable"); // NOI18N
        jScrollPane3.setViewportView(appDataTable);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1009, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(462, 462, 462)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void executeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeBtnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_executeBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_cancelBtnActionPerformed

    private void testClassTxtFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testClassTxtFldActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_testClassTxtFldActionPerformed

    private void browseTestClassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseTestClassBtnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_browseTestClassBtnActionPerformed

    private void browseDataClassBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseDataClassBtnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_browseDataClassBtnActionPerformed

    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_selectBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
}
    
    private void rowCountTextFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rowCountTextFldFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void previousBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable appDataTable;
    private javax.swing.JButton browseDataClassBtn;
    private javax.swing.JButton browseTestClassBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox conditionNameCmb;
    private javax.swing.JTable dataTable;
    private javax.swing.JButton executeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea queryTextArea;
    private javax.swing.JButton selectBtn;
    private javax.swing.JButton nextBtn;
    private javax.swing.JButton previousBtn;
    private javax.swing.JTextField testClassTxtFld;
    private javax.swing.JTextField testDataTxtFld;
    private javax.swing.JTextField rowCountTextFld;
    // End of variables declaration//GEN-END:variables
	/**
	 * @return the appDataTable
	 */
	public javax.swing.JTable getAppDataTable() {
		return appDataTable;
	}

	/**
	 * @return the browseDataClassBtn
	 */
	public javax.swing.JButton getBrowseDataClassBtn() {
		return browseDataClassBtn;
	}

	/**
	 * @return the browseTestClassBtn
	 */
	public javax.swing.JButton getBrowseTestClassBtn() {
		return browseTestClassBtn;
	}

	/**
	 * @return the cancelBtn
	 */
	public javax.swing.JButton getCancelBtn() {
		return cancelBtn;
	}

	/**
	 * @return the conditionNameCmb
	 */
	public javax.swing.JComboBox getConditionNameCmb() {
		return conditionNameCmb;
	}

	/**
	 * @return the dataTable
	 */
	public javax.swing.JTable getDataTable() {
		return dataTable;
	}

	/**
	 * @return the executeBtn
	 */
	public javax.swing.JButton getExecuteBtn() {
		return executeBtn;
	}

	/**
	 * @return the parentObject
	 */
	public JFrame getParentObject() {
		return parentObject;
	}

	/**
	 * @return the queryTextArea
	 */
	public javax.swing.JTextArea getQueryTextArea() {
		return queryTextArea;
	}

	/**
	 * @return the selectBtn
	 */
	public javax.swing.JButton getSelectBtn() {
		return selectBtn;
	}

	/**
	 * @return the testClassTxtFld
	 */
	public javax.swing.JTextField getTestClassTxtFld() {
		return testClassTxtFld;
	}

	/**
	 * @return the testDataTxtFld
	 */
	public javax.swing.JTextField getTestDataTxtFld() {
		return testDataTxtFld;
	}

	/**
	 * @return the nextBtn
	 */
	public javax.swing.JButton getNextBtn() {
		return nextBtn;
	}

	/**
	 * @return the previousBtn
	 */
	public javax.swing.JButton getPreviousBtn() {
		return previousBtn;
	}

	/**
	 * @return the rowCountTextFld
	 */
	public javax.swing.JTextField getRowCountTextFld() {
		return rowCountTextFld;
	}

}