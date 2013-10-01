/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WorkspaceDialog.java
 *
 * Created on Sep 1, 2010, 2:46:19 PM
 */

package com.ijunit.swing.view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.ijunit.resource.ResourceManagerBase;

/**
 *
 * @author vcjain
 */
public class WorkspaceDialog extends javax.swing.JDialog {

	JFrame parent = null;
    /** Creates new form WorkspaceDialog */
    public WorkspaceDialog(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        prepareComponents();
    }

    public void prepareComponents() {

    	addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
    	
    	setLocation(250,150);
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
        confFolderTxt = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        newBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Choose WorkSpace");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 102));
        setModal(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setForeground(new java.awt.Color(0, 102, 102));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Choose reource file");
        jLabel1.setName("jLabel1"); // NOI18N

        confFolderTxt.setName("confFolderTxt"); // NOI18N
        confFolderTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confFolderTxtActionPerformed(evt);
            }
        });

        browseBtn.setMnemonic('B');
        browseBtn.setActionCommand("browse");
        browseBtn.setLabel("Browse");
        browseBtn.setName("browseBtn"); // NOI18N
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        newBtn.setMnemonic('N');
        newBtn.setActionCommand("new");
        newBtn.setLabel("Create New");
        newBtn.setName("newBtn"); // NOI18N
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });

        cancelBtn.setMnemonic('C');
        cancelBtn.setText("Cancel");
        cancelBtn.setActionCommand("cancel");
        cancelBtn.setName("cancelBtn"); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        try{
        	jLabel3.setIcon(new javax.swing.ImageIcon(ResourceManagerBase.DatumgenImagePath+ResourceManagerBase.FILE_SEPRATOR+"DGFlashLogo.PNG")); // NOI18N
        }catch(Exception e){
        	e.printStackTrace();
        }
        jLabel3.setText("jLabel3");
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(newBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(cancelBtn))
                    .addComponent(confFolderTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addComponent(confFolderTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseBtn)
                    .addComponent(newBtn)
                    .addComponent(cancelBtn))
                .addGap(22, 22, 22))
        );

        jPanel2.setBackground(new java.awt.Color(149, 113, 149));
        jPanel2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel2.setBackground(new java.awt.Color(0, 0, 102));
        jLabel2.setFont(new java.awt.Font("Cooper Black", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DatumGen - Test Data Generator");
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    private void confFolderTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confFolderTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confFolderTxtActionPerformed

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
    	try{
	    	JFileChooser chooser = new JFileChooser();
	       	chooser.setCurrentDirectory(
	       			new File(System.getProperty("user.home")
	       					+System.getProperty("file.separator")+"iJunit"));
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				ResourceManagerBase.resourceFileName = chooser.getSelectedFile().getAbsolutePath();
				getConfFolderTxt().setText(ResourceManagerBase.resourceFileName);
				dispose();
		    }else{
		    	return;
		    }
    	}catch(Exception e){
    		
    	}
    }
    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
//    	ConfigurationWizard wizard = new ConfigurationWizard(null,true);
//		wizard.setVisible(true);
		dispose();
    }//GEN-LAST:event_newBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	System.exit(0);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WorkspaceDialog dialog = new WorkspaceDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

 // Variables declaration - do not modify
    private javax.swing.JButton browseBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField confFolderTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton newBtn;
    // End of variables declaration
	/**
	 * @return the confFolderTxt
	 */
	public javax.swing.JTextField getConfFolderTxt() {
		return confFolderTxt;
	}

	/**
	 * @param confFolderTxt the confFolderTxt to set
	 */
	public void setConfFolderTxt(javax.swing.JTextField confFolderTxt) {
		this.confFolderTxt = confFolderTxt;
	}

}