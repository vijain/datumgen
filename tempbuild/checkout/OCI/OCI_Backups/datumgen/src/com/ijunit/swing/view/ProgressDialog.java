/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProgressDialog.java
 *
 * Created on Dec 15, 2009, 12:34:56 PM
 */

package com.ijunit.swing.view;

import javax.swing.SwingUtilities;

/**
 *
 * @author vcjain
 */
public class ProgressDialog extends javax.swing.JDialog {

    protected int m_min = 0;
    protected int m_max = 100;
    protected int m_counter = 0;
    protected boolean m_stringPainted = false;
    private int indeterminateThread_Sleep = 5000;
    private int determinateThread_Sleep = 50;
    /** Creates new form ProgressDialog */
    public ProgressDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        prepareComponents();
    }

    public void prepareComponents() {
        m_progress.setMinimum(m_min);
        m_progress.setMaximum(m_max);
        m_progress.setStringPainted(m_stringPainted);
        //start();
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
        m_progress = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText("Configuring the application. Please wait...");
        jLabel1.setName("jLabel1"); // NOI18N

        m_progress.setName("m_progress"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_progress, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(m_progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void start() {
        m_stringPainted = !m_stringPainted;
        m_progress.setStringPainted(m_stringPainted);
        Thread runner = new Thread() {
            int m_counter;
            public void run() {
                m_progress.setIndeterminate(true);
                try {
                    System.out.println("1");
                    Thread.sleep(indeterminateThread_Sleep);
                    System.out.println("2");
                }
                catch (InterruptedException ex) {}
                m_progress.setIndeterminate(false);
                for (m_counter=m_min; m_counter<=m_max; m_counter++) {
                Runnable runme = new Runnable() {
                    public void run() {
                        m_progress.setValue(m_counter);
                    }
                };
                SwingUtilities.invokeLater(runme);
                try {
                    Thread.sleep(determinateThread_Sleep);
                }
                    catch (InterruptedException ex) {}
                }
                ProgressDialog.this.dispose();
            }
        };
        runner.start();
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProgressDialog dialog = new ProgressDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar m_progress;
    // End of variables declaration//GEN-END:variables

}