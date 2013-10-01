/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddObjectPanel.java
 *
 * Created on May 13, 2009, 9:57:26 PM
 */

package com.ijunit.swing.demo;

import java.awt.dnd.DropTarget;

import com.ijunit.swing.action.AddObjectAction;
import com.ijunit.swing.model.IAttributeDataModel;
import javax.swing.JFrame;
import javax.swing.TransferHandler;
import javax.swing.table.TableColumn;


/**
 * 
 * @author vicky
 */
public class AddObjectPanel extends javax.swing.JPanel {

    JFrame parent = null;
    AddObjectAction action;
	private IAttributeDataModel model;

	//Constant define for list component name
	public static String METHODATTRIBUTELIST_NAME = "MethodAttributeList";
    public static String TABLEATTRIBUTELIST_NAME = "TAbleAttributeList";
    public static String COLUMNLIST_NAME = "ColumnList";
    public static String SETMETHODLIST_NAME = "SetMethodList";

    /** Creates new form AddObjectPanel */
    public AddObjectPanel(JFrame parent,IAttributeDataModel model) {
        this.parent = parent;
        this.model = model;
        action = new AddObjectAction(this, model);
        initComponents();
        classTextFld.setText("com.ijunit.swing.model.TestData");
        
        setSize(parent.getSize());
        System.out.println("@@@@@@@@@111111111");
       
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        classTextFld = new javax.swing.JTextField();
        selectBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tableTextFld = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        methodAttrList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        setMethodList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAttrList = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        columnList = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        detailCheckBox = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        mapTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        cancelBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();

        jScrollPane6.setViewportView(mapTable);
        mapTable.setModel(model);
        TableColumn column = mapTable.getColumnModel().getColumn(0);
        column.setMaxWidth(35);
        
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Add POJO Object");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));

        jLabel2.setText("Class");

        classTextFld.setColumns(100);

        selectBtn.setMnemonic('e');
        selectBtn.setText("Select");
        selectBtn.setActionCommand("select");
        selectBtn.setName("select"); // NOI18N
        selectBtn.addActionListener(action);

        jLabel5.setText("Table");

        tableTextFld.setToolTipText("Name of table");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Method"));

        methodAttrList.addListSelectionListener(action);
        TransferHandler th = new TransferHandler("selectedValue");
        methodAttrList.setTransferHandler(th);
        methodAttrList.setDragEnabled(true);
        methodAttrList.setName("MethodAttributeList");
        
        methodAttrList.setName("methodAttrList"); // NOI18N
        jScrollPane1.setViewportView(methodAttrList);

//        setMethodList.setModel(new javax.swing.AbstractListModel() {
//            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
//            public int getSize() { return strings.length; }
//            public Object getElementAt(int i) { return strings[i]; }
//        });
        DropTarget methodDropTarget = new DropTarget(setMethodList,action);
        setMethodList.setName("setMethodList"); // NOI18N
        jScrollPane2.setViewportView(setMethodList);

        jLabel3.setText("Method");

        jLabel4.setText("Attribute");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addContainerGap(117, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Table Column"));
        jPanel5.setPreferredSize(new java.awt.Dimension(292, 202));

//        tableAttrList.setModel(new javax.swing.AbstractListModel() {
//            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
//            public int getSize() { return strings.length; }
//            public Object getElementAt(int i) { return strings[i]; }
//        });
        tableAttrList.addListSelectionListener(action);
        tableAttrList.setTransferHandler(th);
        tableAttrList.setDragEnabled(true);
        tableAttrList.setName("TableAttributeList");
        jScrollPane3.setViewportView(tableAttrList);

//        columnList.setModel(new javax.swing.AbstractListModel() {
//            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
//            public int getSize() { return strings.length; }
//            public Object getElementAt(int i) { return strings[i]; }
//        });
        DropTarget columnDropTarget = new DropTarget(columnList,action);
        columnList.setName("columnList"); // NOI18N
        jScrollPane5.setViewportView(columnList);

        jLabel6.setText("Column");

        jLabel7.setText("Attribute");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(92, 92, 92))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        detailCheckBox.setText("Detail Display");
        detailCheckBox.setActionCommand("detailDisplay");
        detailCheckBox.setName("detailDisplay"); // NOI18N
        detailCheckBox.addChangeListener(action);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(classTextFld, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tableTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                .addComponent(detailCheckBox)))
                        .addGap(156, 156, 156)
                        .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(classTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tableTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(detailCheckBox)))
                    .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Mapping"));

        deleteBtn.setMnemonic('D');
        deleteBtn.setText("Delete");
        deleteBtn.setName("deleteBtn"); // NOI18N
        deleteBtn.addActionListener(action);

        mapTable.setName("MapTable"); // NOI18N
        jScrollPane6.setViewportView(mapTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cancelBtn.setMnemonic('C');
        cancelBtn.setText("Cancel");
        cancelBtn.setName("cancel"); // NOI18N
        cancelBtn.setOpaque(false);
        cancelBtn.addActionListener(action);

        saveBtn.setMnemonic('S');
        saveBtn.setText("Save");
        saveBtn.setName("Save"); // NOI18N
        saveBtn.setOpaque(false);
        saveBtn.addActionListener(action);

        resetBtn.setMnemonic('R');
        resetBtn.setText("Reset");
        resetBtn.setName("reset"); // NOI18N
        resetBtn.addActionListener(action);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(396, Short.MAX_VALUE)
                .addComponent(resetBtn)
                .addGap(18, 18, 18)
                .addComponent(saveBtn)
                .addGap(18, 18, 18)
                .addComponent(cancelBtn)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(saveBtn)
                    .addComponent(resetBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(760, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_selectBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_deleteBtnActionPerformed

    public void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	hide();
        parent.getContentPane().add(new LogoPanel());
        parent.pack();
}

    public void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
    	hide();
        parent.getContentPane().add(new LogoPanel());
        parent.pack();
}//GEN-LAST:event_saveBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_resetBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField classTextFld;
    private javax.swing.JList columnList;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JCheckBox detailCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable mapTable;
    private javax.swing.JList methodAttrList;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton selectBtn;
    private javax.swing.JList setMethodList;
    private javax.swing.JList tableAttrList;
    private javax.swing.JTextField tableTextFld;
    // End of variables declaration//GEN-END:variables

    /**
	 * @return the cancelBtn
	 */
	public javax.swing.JButton getCancelBtn() {
		return cancelBtn;
	}

	/**
	 * @param cancelBtn the cancelBtn to set
	 */
	public void setCancelBtn(javax.swing.JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	/**
	 * @return the classTextFld
	 */
	public javax.swing.JTextField getClassTextFld() {
		return classTextFld;
	}

	/**
	 * @param classTextFld the classTextFld to set
	 */
	public void setClassTextFld(javax.swing.JTextField classTextFld) {
		this.classTextFld = classTextFld;
	}

	/**
	 * @return the columnList
	 */
	public javax.swing.JList getColumnList() {
		return columnList;
	}

	/**
	 * @param columnList the columnList to set
	 */
	public void setColumnList(javax.swing.JList columnList) {
		this.columnList = columnList;
	}

	/**
	 * @return the deleteBtn
	 */
	public javax.swing.JButton getDeleteBtn() {
		return deleteBtn;
	}

	/**
	 * @param deleteBtn the deleteBtn to set
	 */
	public void setDeleteBtn(javax.swing.JButton deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	/**
	 * @return the methodAttrList
	 */
	public javax.swing.JList getMethodAttrList() {
		return methodAttrList;
	}

	/**
	 * @param methodAttrList the methodAttrList to set
	 */
	public void setMethodAttrList(javax.swing.JList methodAttrList) {
		this.methodAttrList = methodAttrList;
	}

	/**
	 * @return the resetBtn
	 */
	public javax.swing.JButton getResetBtn() {
		return resetBtn;
	}

	/**
	 * @param resetBtn the resetBtn to set
	 */
	public void setResetBtn(javax.swing.JButton resetBtn) {
		this.resetBtn = resetBtn;
	}

	/**
	 * @return the saveBtn
	 */
	public javax.swing.JButton getSaveBtn() {
		return saveBtn;
	}

	/**
	 * @param saveBtn the saveBtn to set
	 */
	public void setSaveBtn(javax.swing.JButton saveBtn) {
		this.saveBtn = saveBtn;
	}

	/**
	 * @return the selectBtn
	 */
	public javax.swing.JButton getSelectBtn() {
		return selectBtn;
	}

	/**
	 * @param selectBtn the selectBtn to set
	 */
	public void setSelectBtn(javax.swing.JButton selectBtn) {
		this.selectBtn = selectBtn;
	}

	/**
	 * @return the setMethodList
	 */
	public javax.swing.JList getSetMethodList() {
		return setMethodList;
	}

	/**
	 * @param setMethodList the setMethodList to set
	 */
	public void setSetMethodList(javax.swing.JList setMethodList) {
		this.setMethodList = setMethodList;
	}

	/**
	 * @return the table
	 */
	public javax.swing.JTable getTable() {
		return mapTable;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(javax.swing.JTable mapTable) {
		this.mapTable = mapTable;
	}

	/**
	 * @return the tableAttrList
	 */
	public javax.swing.JList getTableAttrList() {
		return tableAttrList;
	}

	/**
	 * @param tableAttrList the tableAttrList to set
	 */
	public void setTableAttrList(javax.swing.JList tableAttrList) {
		this.tableAttrList = tableAttrList;
	}

	/**
	 * @return the tableTextFld
	 */
	public javax.swing.JTextField getTableTextFld() {
		return tableTextFld;
	}

	/**
	 * @param tableTextFld the tableTextFld to set
	 */
	public void setTableTextFld(javax.swing.JTextField tableTextFld) {
		this.tableTextFld = tableTextFld;
	}
/**
	 * @return the detailCheckBox
	 */
	public javax.swing.JCheckBox getDetailCheckBox() {
		return detailCheckBox;
	}

	/**
	 * @param detailCheckBox the detailCheckBox to set
	 */
	public void setDetailCheckBox(javax.swing.JCheckBox detailCheckBox) {
		this.detailCheckBox = detailCheckBox;
	}

}
