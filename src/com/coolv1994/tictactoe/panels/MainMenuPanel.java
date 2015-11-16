package com.coolv1994.tictactoe.panels;

import com.coolv1994.tictactoe.GameMode;
import com.coolv1994.tictactoe.Main;
import com.coolv1994.tictactoe.dialogs.SettingsDialog;

/**
 *
 * @author vinnie
 */
public class MainMenuPanel extends javax.swing.JPanel {

    /**
     * Creates new form PlayerSelectPanel
     */
    public MainMenuPanel() {
        Main.LOGGER.info("Main menu.");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JButton p1Button = new javax.swing.JButton();
        javax.swing.JButton p2Button = new javax.swing.JButton();
        javax.swing.JButton onlineButton = new javax.swing.JButton();
        javax.swing.JButton settingsButton = new javax.swing.JButton();

        p1Button.setText("1 Player");
        p1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p1ButtonActionPerformed(evt);
            }
        });

        p2Button.setText("2 Players");
        p2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p2ButtonActionPerformed(evt);
            }
        });

        onlineButton.setText("Online");
        onlineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onlineButtonActionPerformed(evt);
            }
        });

        settingsButton.setText("Settings");
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(p1Button, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(p2Button, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(onlineButton, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(settingsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p1Button)
                .addGap(18, 18, 18)
                .addComponent(p2Button)
                .addGap(18, 18, 18)
                .addComponent(onlineButton)
                .addGap(18, 18, 18)
                .addComponent(settingsButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void p1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p1ButtonActionPerformed
        Main.setGamePanel(GameMode.AI);
    }//GEN-LAST:event_p1ButtonActionPerformed

    private void p2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p2ButtonActionPerformed
        Main.setGamePanel(GameMode.P2);
    }//GEN-LAST:event_p2ButtonActionPerformed

    private void onlineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onlineButtonActionPerformed
        Main.setOnlinePanel();
    }//GEN-LAST:event_onlineButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            SettingsDialog dialog = new SettingsDialog();
            dialog.setVisible(true);
        });
    }//GEN-LAST:event_settingsButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}