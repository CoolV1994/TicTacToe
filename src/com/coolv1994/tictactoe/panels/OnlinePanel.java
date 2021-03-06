package com.coolv1994.tictactoe.panels;

import com.coolv1994.tictactoe.GameMode;
import com.coolv1994.tictactoe.Main;
import com.coolv1994.tictactoe.dialogs.ServerListDialog;
import com.coolv1994.tictactoe.utils.ServerListUtils;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author vinnie
 */
public class OnlinePanel extends javax.swing.JPanel {

    /**
     * Creates new form OnlinePanel
     */
    public OnlinePanel() {
        Main.LOGGER.info("Online menu.");
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

        javax.swing.JButton findButton = new javax.swing.JButton();
        javax.swing.JButton hostButton = new javax.swing.JButton();
        javax.swing.JButton ipButton = new javax.swing.JButton();
        javax.swing.JButton backButton = new javax.swing.JButton();

        findButton.setText("Find Match");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        hostButton.setText("Host Match");
        hostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostButtonActionPerformed(evt);
            }
        });

        ipButton.setText("Direct Connect");
        ipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(findButton, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(hostButton, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(ipButton, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(findButton)
                .addGap(18, 18, 18)
                .addComponent(hostButton)
                .addGap(18, 18, 18)
                .addComponent(ipButton)
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        ServerListDialog dialog = new ServerListDialog();
        java.awt.EventQueue.invokeLater(() -> {
            dialog.setVisible(true);
        });
        java.awt.EventQueue.invokeLater(dialog::reloadServerList);
    }//GEN-LAST:event_findButtonActionPerformed

    private void hostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostButtonActionPerformed
        Main.setHostPanel();
        ServerListUtils.startHeartbeat();
    }//GEN-LAST:event_hostButtonActionPerformed

    private void ipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipButtonActionPerformed
        String ip = JOptionPane.showInputDialog(Main.gameFrame, "Enter IP address:", "Direct Connect", JOptionPane.QUESTION_MESSAGE);
        if (ip == null || ip.isEmpty()) {
            return;
        }
        Main.setGamePanel(GameMode.CLIENT);
        if (ip.contains(":")) {
            int split = ip.indexOf(":");
            int port = Integer.parseInt(ip.substring(split + 1));
            ip = ip.substring(0, split);
            Main.LOGGER.log(Level.INFO, "Connecting to IP: {0} Port: {1}", new Object[]{ip, port});
            Main.setClientPanel(ip, port);
        } else {
            Main.LOGGER.log(Level.INFO, "Connecting to IP: {0} Port: 25010", ip);
            Main.setClientPanel(ip, 25010);
        }
    }//GEN-LAST:event_ipButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        Main.setMainPanel();
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
