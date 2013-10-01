/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJPanel.java
 *
 * Created on May 30, 2009, 12:18:02 AM
 */

package com.ijunit.swing.view;

import java.awt.dnd.DropTarget;

import javax.swing.JFrame;
import javax.swing.TransferHandler;
import javax.swing.table.TableColumn;

import com.ijunit.common.utility.CommonUtility;
import com.ijunit.swing.action.AddDummyObjectAction;
import com.ijunit.swing.model.IAttributeDataModel;

/**
 * 
 * @author vicky
 */
public class AddDummyObjectView extends javax.swing.JPanel {

	AddDummyObjectAction action;

	private IAttributeDataModel model;

	private JFrame parentObject = null;

	// Constant define for list component name
	public static String METHODATTRIBUTELIST_NAME = "MethodAttributeList";

	public static String ABLEATTRIBUTELIST_NAME = "TAbleAttributeList";

	public static String COLUMNLIST_NAME = "ColumnList";

	public static String SETMETHODLIST_NAME = "SetMethodList";

	/** Creates new form AddObject */
	public AddDummyObjectView(IAttributeDataModel model) {
		this.model = model;
		action = new AddDummyObjectAction(this, model);
		initComponents();
		prepareComponents();

	}

	public AddDummyObjectView(JFrame parent, IAttributeDataModel model) {
		this(model);
		this.parentObject = parent;
	}

	public void prepareComponents() {
		// preparing table
		mapTable.setModel(model);
		TableColumn column = mapTable.getColumnModel().getColumn(0);
		column.setMaxWidth(35);
//		column  = mapTable.getColumnModel().getColumn(3);
//		column.setCellEditor(new TableEditor(TableEditor.DATAOBJECT));
//		column.setCellRenderer(new TableRenderer(false, TableRenderer.DATAOBJECT));
		
		// preparing Lists.
		methodAttrList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		methodAttrList.addListSelectionListener(action);
		TransferHandler th = new TransferHandler("selectedValue");
		methodAttrList.setTransferHandler(th);
		methodAttrList.setDragEnabled(true);
		methodAttrList.setName("MethodAttributeList");
		DropTarget methodDropTarget = new DropTarget(setMethodList, action);

		setMethodList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});

		// setting action listener
		saveBtn.addActionListener(action);
		resetBtn.addActionListener(action);
		cancelBtn.addActionListener(action);
		deleteBtn.addActionListener(action);
		selectBtn.addActionListener(action);
		autoMapBtn.addActionListener(action);
		browseBtn.addActionListener(action);
		//prefixTextFld.addActionListener(action);
		
		autoMapBtn.setEnabled(false);
		getSaveBtn().setEnabled(false);
		getDeleteBtn().setEnabled(false);
		getResetBtn().setEnabled(false);
		//getDetailCheckBox().setEnabled(false);
		
		// For Testing purpose must be commented after testing
		classTextFld.setText("com.mbi.common.data.user.UserInfo");
		
		prefixTextFld.setText(CommonUtility.getPrefixString());
		packageTextFld.setText(CommonUtility.getPackagesToOmitString());

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	 // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        methodAttrList = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        setMethodList = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        mapTable = new javax.swing.JTable();
        deleteBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        resetBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        classTextFld = new javax.swing.JTextField();
        selectBtn = new javax.swing.JButton();
        autoMapBtn = new javax.swing.JButton();
        browseBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        prefixTextFld = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        packageTextFld = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14));
        jLabel5.setText("Attribute");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14));
        jLabel3.setText("Method");

        methodAttrList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        methodAttrList.setName("methodAttrList"); // NOI18N
        jScrollPane1.setViewportView(methodAttrList);

        setMethodList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        setMethodList.setName("setMethodList"); // NOI18N
        jScrollPane4.setViewportView(setMethodList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18));
        jLabel4.setText("Add Dummy Object");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        mapTable.setName("MapTable"); // NOI18N
        jScrollPane6.setViewportView(mapTable);

        deleteBtn.setMnemonic('D');
        deleteBtn.setText("Delete");
        deleteBtn.setName("deleteBtn"); // NOI18N
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        cancelBtn.setMnemonic('C');
        cancelBtn.setText("Cancel");
        cancelBtn.setName("cancel"); // NOI18N
        cancelBtn.setOpaque(false);
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        saveBtn.setMnemonic('S');
        saveBtn.setText("Save");
        saveBtn.setName("Save"); // NOI18N
        saveBtn.setOpaque(false);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(382, 255));

        resetBtn.setMnemonic('R');
        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 13));
        jLabel2.setText("Class");

        classTextFld.setColumns(100);

        selectBtn.setMnemonic('e');
        selectBtn.setText("Select");
        selectBtn.setActionCommand("select");
        selectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBtnActionPerformed(evt);
            }
        });

        autoMapBtn.setText("Auto Map");
        autoMapBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoMapBtnActionPerformed(evt);
            }
        });

        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Prefix");

        prefixTextFld.setToolTipText("Name of table");
        prefixTextFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefixTextFldActionPerformed(evt);
            }
        });

        jLabel8.setText("Specify custom packages to omit ");

        packageTextFld.setToolTipText("Name of table");
        packageTextFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packageTextFldActionPerformed(evt);
            }
        });

        jLabel10.setText("like com.mbi, org.apache");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(classTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(browseBtn)
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(prefixTextFld, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(autoMapBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(packageTextFld, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(1, 1, 1))
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(classTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseBtn)
                    .addComponent(prefixTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addComponent(packageTextFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoMapBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(414, 414, 414))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

	private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteBtnActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_deleteBtnActionPerformed

	private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveBtnActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_saveBtnActionPerformed

	private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelBtnActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cancelBtnActionPerformed

	private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_resetBtnActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_resetBtnActionPerformed

	private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_selectBtnActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_selectBtnActionPerformed

	private void autoMapBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_autoMapBtnActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_autoMapBtnActionPerformed

	private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {
		
	}

    private void prefixTextFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void packageTextFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton autoMapBtn;

	private javax.swing.JButton cancelBtn;

	private javax.swing.JButton browseBtn;

	private javax.swing.JTextField classTextFld;

	private javax.swing.JButton deleteBtn;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;

	private javax.swing.JLabel jLabel5;

	private javax.swing.JLabel jLabel8;

	private javax.swing.JLabel jLabel10;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JPanel jPanel2;

	private javax.swing.JPanel jPanel3;

	private javax.swing.JPanel jPanel4;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JScrollPane jScrollPane4;

	private javax.swing.JScrollPane jScrollPane6;

	private javax.swing.JTable mapTable;

	private javax.swing.JList methodAttrList;

	private javax.swing.JButton resetBtn;

	private javax.swing.JButton saveBtn;

	private javax.swing.JButton selectBtn;

	private javax.swing.JList setMethodList;

	private javax.swing.JTextField packageTextFld;

	private javax.swing.JTextField prefixTextFld;

	// End of variables declaration//GEN-END:variables

	/**
	 * @return the cancelBtn
	 */
	public javax.swing.JButton getCancelBtn() {
		return cancelBtn;
	}

	/**
	 * @return the classTextFld
	 */
	public javax.swing.JTextField getClassTextFld() {
		return classTextFld;
	}
	

	/**
	 * @return the deleteBtn
	 */
	public javax.swing.JButton getDeleteBtn() {
		return deleteBtn;
	}

	/**
	 * @return the methodAttrList
	 */
	public javax.swing.JList getMethodAttrList() {
		return methodAttrList;
	}

	/**
	 * @return the resetBtn
	 */
	public javax.swing.JButton getResetBtn() {
		return resetBtn;
	}

	/**
	 * @return the saveBtn
	 */
	public javax.swing.JButton getSaveBtn() {
		return saveBtn;
	}

	/**
	 * @return the selectBtn
	 */
	public javax.swing.JButton getSelectBtn() {
		return selectBtn;
	}

	/**
	 * @return the setMethodList
	 */
	public javax.swing.JList getSetMethodList() {
		return setMethodList;
	}

	/**
	 * @return the table
	 */
	public javax.swing.JTable getTable() {
		return mapTable;
	}

	/**
	 * @return the tableAttrList
	 */
	/**
	 * @return the autoMapBtn
	 */
	public javax.swing.JButton getAutoMapBtn() {
		return autoMapBtn;
	}

	/**
	 * @return the parentObject
	 */
	public JFrame getParentObject() {
		return parentObject;
	}

	/**
	 * @param parentObject the parentObject to set
	 */
	public void setParentObject(JFrame parentObject) {
		this.parentObject = parentObject;
	}

	/**
	 * @return the browseBtn
	 */
	public javax.swing.JButton getBrowseBtn() {
		return browseBtn;
	}

	public javax.swing.JTextField getPrefixTextFld() {
		return prefixTextFld;
	}

	public javax.swing.JTextField getPackageTextFld() {
		return packageTextFld;
	}

}
