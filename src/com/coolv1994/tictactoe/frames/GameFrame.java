package com.coolv1994.tictactoe.frames;

import com.coolv1994.tictactoe.Main;
import com.coolv1994.tictactoe.panels.LoadingPanel;

/**
 *
 * @author vinnie
 */
public final class GameFrame extends javax.swing.JFrame {

    /**
     * Creates new form GameFrame
     */
    public GameFrame() {
        Main.LOGGER.info("Initializing game frame...");
        initComponents();
        changePanel(new LoadingPanel());
    }

    public void changePanel(javax.swing.JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setPreferredSize(new java.awt.Dimension(234, 290));
        getContentPane().setLayout(new java.awt.CardLayout());

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
