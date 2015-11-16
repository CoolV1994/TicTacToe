package com.coolv1994.tictactoe.nio;

import com.coolv1994.tictactoe.Main;
import com.coolv1994.tictactoe.utils.Config;
import com.coolv1994.tictactoe.utils.ServerListUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author Vinnie
 */
public class Server extends Thread implements RemoteInterface {

    private final int portNumber;
    private final boolean enableChat;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private boolean isConnected = false;

    public Server() {
        portNumber = Config.getInt("serverPort");
        enableChat = Config.getBool("enableChat");
    }

    @Override
    public boolean sendMove(String position) {
        if (isConnected) {
            System.out.println("Player: " + position);
            out.println("#:" + position);
            return true;
        }
        return false;
    }

    @Override
    public boolean sendChat(String message) {
        if (isConnected) {
            System.out.println("Chat: " + message);
            out.println("T:" + message);
            return true;
        }
        return false;
    }

    @Override
    public boolean endGame() {
        if (isConnected) {
            System.out.println("Game Over");
            out.println("#!");
            return true;
        }
        return false;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void disconnect() {
        if (isConnected) {
            System.out.println("Bye");
            out.println("Bye");
        }
        interrupt();
        closeSocket();
    }

    @Override
    public boolean undo(String position) {
        if (isConnected) {
            System.out.println("Undo: " + position);
            out.println("-:" + position);
            return true;
        }
        return false;
    }

    @Override
    public String getOpponentIP() {
        return clientSocket.getInetAddress().getHostAddress();
    }

    private void closeSocket() {
        Main.LOGGER.info("Closing socket...");
        isConnected = false;
        try {
            serverSocket.close();
            clientSocket.close();
        } catch (Exception ex) {
            Main.LOGGER.log(Level.SEVERE, "Error closing socket.", ex);
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Main.LOGGER.log(Level.INFO, "Starting server on Port: {0}", portNumber);

            try {
                serverSocket = new ServerSocket(portNumber);
                clientSocket = serverSocket.accept();

                Main.LOGGER.info(clientSocket.toString());

                // White-List
                for (String bannedIP : Config.get("bannedIPs").split("\\|")) {
                    if (clientSocket.getInetAddress().getHostAddress().equals(bannedIP)) {
                        Main.LOGGER.log(Level.INFO, "Kicking Banned Player: {0}", bannedIP);
                        closeSocket();
                        break;
                    }
                }

                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                isConnected = true;

                String fromClient;
                while ((fromClient = in.readLine()) != null) {
                    System.out.println("Client: " + fromClient);
                    if (fromClient.startsWith("#:")) {
                        String position = fromClient.substring(2);
                        if (!Main.gamePanel.opponentTakeTurn(position))
                            out.println("-:" + position);
                        continue;
                    }
                    if (fromClient.startsWith("-:")) {
                        String position = fromClient.substring(2);
                        Main.gamePanel.undoTurn(position);
                        continue;
                    }
                    if (fromClient.equals("#!")) {
                        Main.gamePanel.checkIfGameOver();
                    }
                    if (fromClient.startsWith("T:")) {
                        if (enableChat) {
                            String chat = fromClient.substring(2);
                            Main.chatFrame.appendChat(chat);
                            Main.chatFrame.setVisible(enableChat);
                        }
                        continue;
                    }
                    if (fromClient.equals("#$")) {
                        Main.stopWaitingPanel();
                        ServerListUtils.stopHeartbeat();
                        Main.chatFrame.setVisible(enableChat);
                        out.println("#$");
                        ServerListUtils.removeServer(portNumber);
                    }
                    if (fromClient.equals("Bye")) {
                        interrupt();
                        break;
                    }
                }
                closeSocket();
            } catch (BindException ex) {
                Main.LOGGER.log(Level.SEVERE, "Bind Error", ex);
                JOptionPane.showMessageDialog(Main.gameFrame,
                        "Bind Error: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                Main.setMainPanel();
                Main.chatFrame.appendSeparator();
                return;
            } catch (SocketException ex) {
                Main.LOGGER.log(Level.SEVERE, "Socket Error", ex);
                Main.setMainPanel();
                Main.chatFrame.appendSeparator();
                return;
            } catch (IOException ex) {
                closeSocket();
                Main.LOGGER.log(Level.SEVERE, "I/O Error", ex);
                JOptionPane.showMessageDialog(Main.gameFrame,
                        "I/O Error: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                Main.setMainPanel();
                Main.chatFrame.appendSeparator();
                return;
            }
        }

        closeSocket();
        Main.setMainPanel();
        Main.chatFrame.appendSeparator();
    }
}
