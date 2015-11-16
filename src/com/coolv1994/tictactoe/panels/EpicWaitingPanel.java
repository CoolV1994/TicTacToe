package com.coolv1994.tictactoe.panels;

import com.coolv1994.tictactoe.Main;
import com.coolv1994.tictactoe.utils.Config;
import com.coolv1994.tictactoe.utils.ServerListUtils;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author vinnie
 */
public class EpicWaitingPanel extends javax.swing.JPanel {

    private static final String[] POSITIONS = {
        "NW", "W", "SW", "S", "SE", "E", "NE", "N"};
    private static Timer timer;
    private int index = 0;
    private int index2 = 5;
    private boolean x;

    /**
     * Creates new form TicTacPanel
     */
    public EpicWaitingPanel() {
        initComponents();
        setTimer();
        Main.LOGGER.info("Waiting for match...");
    }

    private javax.swing.JButton getButton(String position) {
        switch (position) {
            case "NW":
                return nw;
            case "N":
                return n;
            case "NE":
                return ne;
            case "W":
                return w;
            case "E":
                return e;
            case "SW":
                return sw;
            case "S":
                return s;
            case "SE":
                return se;

        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nw = new javax.swing.JButton();
        n = new javax.swing.JButton();
        ne = new javax.swing.JButton();
        w = new javax.swing.JButton();
        e = new javax.swing.JButton();
        sw = new javax.swing.JButton();
        s = new javax.swing.JButton();
        se = new javax.swing.JButton();
        javax.swing.JButton quitButton = new javax.swing.JButton();
        javax.swing.JLabel waitingLabel = new javax.swing.JLabel();

        nw.setEnabled(false);
        nw.setName("NW"); // NOI18N

        n.setEnabled(false);
        n.setName("N"); // NOI18N

        ne.setEnabled(false);
        ne.setName("NE"); // NOI18N

        w.setEnabled(false);
        w.setName("W"); // NOI18N

        e.setEnabled(false);
        e.setName("E"); // NOI18N

        sw.setEnabled(false);
        sw.setName("SW"); // NOI18N

        s.setEnabled(false);
        s.setName("S"); // NOI18N

        se.setEnabled(false);
        se.setName("SE"); // NOI18N

        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        waitingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        waitingLabel.setText("<html><center>Waiting<br>for<br>match");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quitButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(nw, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(ne, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(w, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(waitingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(sw, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(se, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ne, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nw, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(w, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waitingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sw, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(se, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(quitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Main.LOGGER.fine("Waiting...");
                getButton(POSITIONS[index]).setText(x?"X":"O");
                getButton(POSITIONS[index2]).setText("");
                x = !x;
                if (index == 7) {
                    index = 0;
                } else {
                    index++;
                }
                if (index2 == 7) {
                    index2 = 0;
                } else {
                    index2++;
                }
            }
        }, 0, 1000);
    }

    public void stopTimer() {
        if (timer == null) {
            return;
        }
        timer.cancel();
    }

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        if (
                JOptionPane.showConfirmDialog(this,
                        "Are your sure you want to quit?",
                        "Quit Game",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE)
                != 0
            )
            return;
        Main.remoteConnection.disconnect();
        ServerListUtils.removeServer(Config.getInt("serverPort"));
        stopTimer();
        Main.setMainPanel();
    }//GEN-LAST:event_quitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton e;
    private javax.swing.JButton n;
    private javax.swing.JButton ne;
    private javax.swing.JButton nw;
    private javax.swing.JButton s;
    private javax.swing.JButton se;
    private javax.swing.JButton sw;
    private javax.swing.JButton w;
    // End of variables declaration//GEN-END:variables
}
