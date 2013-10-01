/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StartDemo.java
 *
 * Created on May 13, 2009, 4:58:51 PM
 */

package com.ijunit.swing.demo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.ijunit.swing.view.AddObjectPanel;
import com.ijunit.swing.view.AddObjectView;

import com.ijunit.swing.model.ObjectDataTableModel;

/**
 *
 * @author vcjain
 */
public class StartDemo extends javax.swing.JFrame {

    /** Creates new form StartDemo */
    public StartDemo() {
        //initComponents();
        initDo();
    }
    public void initDo(){
        topPanel = new javax.swing.JPanel();
        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();
        executeMenu = new javax.swing.JMenu();
        mainMenu.setName("mainMenu"); // NOI18N

        searchMenu = new javax.swing.JMenu();
        searchMenuItem = new javax.swing.JMenuItem();

        searchMenu.setText("Search");
        
        executeMenu.setText("Execute");
        searchMenuItem.setText("Test Condition");
        searchMenu.add(searchMenuItem);
        
        fileMenu.setText("File");
        fileMenu.setName("fileMenu"); // NOI18N
        mainMenu.add(fileMenu);
        
        
        editMenu.setText("Edit");
        editMenu.setName("editMenu"); // NOI18N
        mainMenu.add(editMenu);

        mainMenu.add(searchMenu);
        setJMenuBar(mainMenu);
        
        mainMenu.add(executeMenu);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        topPanel.setName("topPanel"); // NOI18N
        topPanel.add(new LogoPanel());
        addObjectItem = new javax.swing.JMenuItem();
        addTestDataItem = new javax.swing.JMenuItem();
        exitItem = new javax.swing.JMenuItem();
        //addObjectItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijunit/resource/image/plus.PNG"))); // NOI18N
        addObjectItem.setMnemonic('o');
        addObjectItem.setText("New POJO");
        addObjectItem.setToolTipText("Add a new Data Object");
        addObjectItem.setName("addObjectItem"); // NOI18N
        addObjectItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addObjectItemActionPerformed(evt);
            }
        });
        fileMenu.add(addObjectItem);

        //addTestDataItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ijunit/resource/image/plus.PNG"))); // NOI18N
        addTestDataItem.setMnemonic('T');
        addTestDataItem.setText("New Test Data");
        addTestDataItem.setName("addTestDataItem"); // NOI18N
        addTestDataItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTestDataItemActionPerformed(evt);
            }
        });
        fileMenu.add(addTestDataItem);

        exitItem.setMnemonic('x');
        exitItem.setText("Exit");
        exitItem.setToolTipText("Exit from application");
        exitItem.setName("exitItem"); // NOI18N
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitItemActionPerformed(evt);  
            }
        });
        
        searchMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
               JPanel p = new SearchPanel(StartDemo.this);
                getContentPane().removeAll();
                getContentPane().add(p);
                pack();
            }
        });
        
        
        fileMenu.add(exitItem);
        getContentPane().add(topPanel);
        pack();
        //setPreferredSize(new java.awt.Dimension(820, 500));
        setSize(getMaximumSize());
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mainMenu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        topPanel.setName("topPanel"); // NOI18N
        //topPanel.setPreferredSize(new java.awt.Dimension(820, 447));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel1.setText("iGTest");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("(interactive Generator for Test Data)");
        jLabel2.setName("jLabel2"); // NOI18N

        topPanel.add(new LogoPanel());
        mainMenu.setName("mainMenu"); // NOI18N

        fileMenu.setText("File");
        fileMenu.setName("fileMenu"); // NOI18N
        mainMenu.add(fileMenu);

        editMenu.setText("Edit");
        editMenu.setName("editMenu"); // NOI18N
        mainMenu.add(editMenu);

        setJMenuBar(mainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        //setPreferredSize(new java.awt.Dimension(820, 447));
        
        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
            	try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                new StartDemo().setVisible(true);
    }
    private void addObjectItemActionPerformed(java.awt.event.ActionEvent evt) {
    	 JPanel p = new AddObjectView(this,new ObjectDataTableModel());
         getContentPane().removeAll();
         p.setSize(topPanel.getSize());
         getContentPane().add(p);
         pack();
    }

    private void addTestDataItemActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Adding a Test Data");
        JPanel p = new AddTestData1(this);
        getContentPane().removeAll();
        getContentPane().add(p);
        pack();
    }

    private void exitItemActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    private javax.swing.JMenuItem addObjectItem;
    private javax.swing.JMenuItem addTestDataItem;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JMenu searchMenu;
    private javax.swing.JMenuItem searchMenuItem;
    private javax.swing.JMenu executeMenu;
}
