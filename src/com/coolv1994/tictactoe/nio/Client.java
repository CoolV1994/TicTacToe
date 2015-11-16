package com.coolv1994.tictactoe.nio;

import com.coolv1994.tictactoe.Main;
import com.coolv1994.tictactoe.utils.Config;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author Vinnie
 */
public class Client extends Thread implements RemoteInterface {

    private final String hostName;
    private final int portNumber;
    private final boolean enableChat;
    private Socket socket;
    private PrintWriter out;
    private boolean isConnected = false;

    public Client(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
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
        return hostName;
    }

    private void closeSocket() {
        Main.LOGGER.info("Closing socket...");
        isConnected = false;
        try {
            socket.close();
        } catch (Exception ex) {
            Main.LOGGER.log(Level.SEVERE, "Error closing socket.", ex);
        }
    }

    @Override
    public void run() {
        try {
            socket = new Socket(hostName, portNumber);

            Main.LOGGER.info(socket.toString());

            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            isConnected = true;
            out.println("#$");

            String fromServer;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.startsWith("#:")) {
                    String position = fromServer.substring(2);
                    if (!Main.gamePanel.opponentTakeTurn(position))
                        out.println("Taken");
                    continue;
                }
                if (fromServer.startsWith("-:")) {
                    String position = fromServer.substring(2);
                    Main.gamePanel.undoTurn(position);
                    continue;
                }
                if (fromServer.equals("#!")) {
                    Main.gamePanel.checkIfGameOver();
                }
                if (fromServer.startsWith("T:")) {
                    if (enableChat) {
                        String chat = fromServer.substring(2);
                        Main.chatFrame.appendChat(chat);
                        Main.chatFrame.setVisible(enableChat);
                    }
                    continue;
                }
                if (fromServer.equals("#$")) {
                    Main.stopWaitingPanel();
                    Main.chatFrame.setVisible(enableChat);
                }
                if (fromServer.equals("Bye")) {
                    break;
                }
            }

            closeSocket();
            Main.setMainPanel();
            Main.chatFrame.appendSeparator();
        } catch (UnknownHostException ex) {
            isConnected = false;
            Main.LOGGER.log(Level.SEVERE, "Unknown Host", ex.getMessage());
            JOptionPane.showMessageDialog(Main.gameFrame,
                    "Unknown Host: " + hostName,
                    "Error", JOptionPane.ERROR_MESSAGE);
            Main.setMainPanel();
            Main.chatFrame.appendSeparator();
        } catch (ConnectException ex) {
            isConnected = false;
            Main.LOGGER.log(Level.SEVERE, "Connection Error", ex.getMessage());
            JOptionPane.showMessageDialog(Main.gameFrame,
                    "Connection Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            Main.setMainPanel();
            Main.chatFrame.appendSeparator();
        } catch (SocketException ex) {
            Main.LOGGER.log(Level.SEVERE, "Socket Error", ex);
            Main.setMainPanel();
            Main.chatFrame.appendSeparator();
        } catch (IOException ex) {
            isConnected = false;
            Main.LOGGER.log(Level.SEVERE, "I/O Error", ex);
            JOptionPane.showMessageDialog(Main.gameFrame,
                    "I/O Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            Main.setMainPanel();
            Main.chatFrame.appendSeparator();
        }
    }
}
