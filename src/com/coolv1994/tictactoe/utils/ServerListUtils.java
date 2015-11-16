package com.coolv1994.tictactoe.utils;

import com.coolv1994.tictactoe.Main;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author Vinnie
 */
public class ServerListUtils {

    private static Timer timer;

    public static ListModel<String> downloadServerList() {
        Main.LOGGER.info("Downloading server list...");
        DefaultListModel<String> servers = new DefaultListModel<>();
        ArrayList<String> response = HttpUtils.get(
                "http://projects.coolv1994.com/TicTacToe/serverList");
        response.stream().forEach(servers::addElement);
        return servers;
    }

    public static String[] getServerIP(String id) {
        Main.LOGGER.log(Level.INFO, "Request IP for ID: {0}", id);
        String[] ipPort = new String[2];
        ArrayList<String> response = HttpUtils.post(
                "http://projects.coolv1994.com/TicTacToe/getIP",
                "id=" + id);
        if (response.isEmpty()) {
            return null;
        }
        if (response.get(0).equals("Server")) {
            ipPort[0] = response.get(1);
            ipPort[1] = response.get(2);
            Main.LOGGER.log(Level.INFO, "Server IP: {0} Port: {1}", ipPort);
        }
        return ipPort;
    }

    public static void removeServer(int port) {
        Main.LOGGER.log(Level.INFO, "Removing server from list. Port: {0}", port);
        stopHeartbeat();
        ArrayList<String> response = HttpUtils.post(
                "http://projects.coolv1994.com/TicTacToe/removeServer",
                "port=" + port);
        if (response.isEmpty()) {
            return;
        }
        if (response.get(0).equals("ID")) {
            String id = response.get(1);
            Main.LOGGER.log(Level.INFO, "Server ID: {0}", id);
        }
    }

    public static void addServerToList() {
        String name = Config.get("serverName");
        int port = Config.getInt("serverPort");
        Main.LOGGER.log(Level.INFO, "Adding server to list. Port: {0} Name: {1}", new Object[]{port, name});
        ArrayList<String> response = HttpUtils.post(
                "http://projects.coolv1994.com/TicTacToe/addServer",
                "port=" + port + "&name=" + name);
        if (response.isEmpty()) {
            return;
        }
        if (response.get(0).equals("Closed")) {
            Main.LOGGER.warning("Server is not public.");
            Main.LOGGER.log(Level.WARNING, "Make sure port {0} is port forwarded and allowed through your firewall.", port);
            stopHeartbeat();
            JOptionPane.showMessageDialog(Main.gameFrame,
                    "Server not added to server list.\n" +
                    "Your server can not be accessed from the internet.\n" +
                    "Please make sure port: " + port + "\n" +
                    "is port forwarded and allowed through your firewall.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (response.get(0).equals("ID")) {
            String id = response.get(1);
            Main.LOGGER.log(Level.INFO, "Server ID: {0}", id);
        }
    }

    public static void startHeartbeat() {
        if (!Config.getBool("addToServerList")) {
            return;
        }
        Main.LOGGER.info("Starting server list heartbeat...");
        // Update server list every 5 min
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ServerListUtils.addServerToList();
            }
        }, 0, 60000);
    }

    public static void stopHeartbeat() {
        Main.LOGGER.info("Stopping server list heartbeat.");
        if (timer == null) {
            return;
        }
        timer.cancel();
    }
}
