package com.coolv1994.tictactoe.nio;

/**
 *
 * @author Vinnie
 */
public interface RemoteInterface {

    boolean sendMove(String position);

    boolean sendChat(String message);

    boolean endGame();

    boolean isConnected();

    void disconnect();

    boolean undo(String position);

    String getOpponentIP();

}
