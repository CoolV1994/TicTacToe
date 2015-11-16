package com.coolv1994.tictactoe.utils;

import com.coolv1994.tictactoe.Main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

/**
 *
 * @author Vinnie
 */
public class Config {
    private static final String FILE = "ttt.config";
    private static final Properties CONFIG = new Properties();

    public static void set(String key, String value) {
        CONFIG.setProperty(key, value);
    }

    public static void setBool(String key, boolean value) {
        set(key, String.valueOf(value));
    }

    public static void setInt(String key, int value) {
        set(key, String.valueOf(value));
    }

    public static String get(String key) {
        return CONFIG.getProperty(key);
    }

    public static boolean getBool(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static String get(String key, String defaultValue) {
        return CONFIG.getProperty(key, defaultValue);
    }

    public static boolean getBool(String key, String defaultValue) {
        return Boolean.parseBoolean(get(key, defaultValue));
    }

    public static int getInt(String key, String defaultValue) {
        return Integer.parseInt(get(key, defaultValue));
    }

    public static void load() {
        Main.LOGGER.info("Loading config...");
        try (FileInputStream in = new FileInputStream(FILE)) {
            CONFIG.load(in);
        } catch (FileNotFoundException ex) {
            Main.LOGGER.log(Level.SEVERE, "Error: No Config File");
        } catch (IOException ex) {
            Main.LOGGER.log(Level.SEVERE, "I/O Error", ex);
        }
    }

    public static void save() {
        Main.LOGGER.info("Saving config...");
        try (FileOutputStream out = new FileOutputStream(FILE)) {
            CONFIG.store(out, "TicTacToe Config");
        } catch (IOException ex) {
            Main.LOGGER.log(Level.SEVERE, "I/O Error", ex);
        }
    }

    public static void setDefaults() {
        set("addToServerList", "true");
        set("enableChat", "true");
        set("serverName", "Server");
        set("serverPort", "25010");
        set("bannedIPs", "");
        Main.LOGGER.info("Default config set.");
    }
}
