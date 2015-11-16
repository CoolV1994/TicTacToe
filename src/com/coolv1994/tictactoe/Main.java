package com.coolv1994.tictactoe;

import com.coolv1994.tictactoe.frames.*;
import com.coolv1994.tictactoe.nio.*;
import com.coolv1994.tictactoe.panels.*;
import com.coolv1994.tictactoe.utils.Config;
import com.coolv1994.tictactoe.utils.ServerListUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinnie
 */
public class Main {

    public static final Logger LOGGER = Logger.getLogger("TicTacToe");
    public static GameFrame gameFrame;
    public static TicTacPanel gamePanel;
    public static EpicWaitingPanel waitingPanel;
    public static ChatFrame chatFrame;
    public static RemoteInterface remoteConnection;

    public static void setMainPanel() {
        gamePanel = null;
        gameFrame.changePanel(new MainMenuPanel());
    }

    public static void setOnlinePanel() {
        gamePanel = null;
        gameFrame.changePanel(new OnlinePanel());
    }

    public static void setGamePanel(GameMode mode) {
        gamePanel = new TicTacPanel(mode);
        gameFrame.changePanel(gamePanel);
    }

    public static void setHostPanel() {
        gamePanel = new TicTacPanel(GameMode.SERVER);
        setWaitingPanel();
        Server server = new Server();
        server.start();
        remoteConnection = server;
    }

    public static void setClientPanel(String ip, int port) {
        gamePanel = new TicTacPanel(GameMode.CLIENT);
        gamePanel.setP2Turn();
        setWaitingPanel();
        Client client = new Client(ip, port);
        client.start();
        remoteConnection = client;
    }

    public static void setWaitingPanel() {
        waitingPanel = new EpicWaitingPanel();
        gameFrame.changePanel(waitingPanel);
    }

    public static void stopWaitingPanel() {
        waitingPanel.stopTimer();
        waitingPanel = null;
        gameFrame.changePanel(gamePanel);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            LOGGER.log(Level.SEVERE, "Error setting look and feel", ex);
        }        
        //</editor-fold>        
        //</editor-fold>

        // Load Config
        Config.setDefaults();
        Config.load();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            gameFrame = new GameFrame();
            chatFrame = new ChatFrame();
            gameFrame.setVisible(true);
            gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    if (remoteConnection != null) {
                        ServerListUtils.removeServer(Config.getInt("serverPort"));
                    }
                    System.exit(0);
                }
            });
            setMainPanel();
        });
    }

}
