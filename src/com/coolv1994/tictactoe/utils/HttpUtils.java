package com.coolv1994.tictactoe.utils;

import com.coolv1994.tictactoe.Main;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author Vinnie
 */
public class HttpUtils {
    public static ArrayList<String> post(String request, String parameters) {
        Main.LOGGER.log(Level.INFO, "Post URL: {0} Params: {1}", new Object[]{request, parameters});
        ArrayList<String> response = new ArrayList<>();
        try {
            byte[] postData = parameters.getBytes(StandardCharsets.UTF_8);
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();           
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
            conn.setUseCaches(false);
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            } catch (IOException ex) {
                Main.LOGGER.log(Level.SEVERE, "Error posting data.", ex);
            }
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.add(line);
                }
            } catch (IOException ex) {
                Main.LOGGER.log(Level.SEVERE, "Error getting response.", ex);
            }
        } catch (MalformedURLException ex) {
            Main.LOGGER.log(Level.SEVERE, "Malformed URL: {0}", ex.getMessage());
        } catch (ProtocolException ex) {
            Main.LOGGER.log(Level.SEVERE, "Invalid Protocol", ex);
        } catch (IOException ex) {
            Main.LOGGER.log(Level.SEVERE, "I/O Error", ex);
        }
        return response;
    }

    public static ArrayList<String> get(String request) {
        Main.LOGGER.log(Level.INFO, "Get URL: {0}", request);
        ArrayList<String> response = new ArrayList<>();
        try {
            URL url = new URL(request);
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.add(line);
                }
            } catch (IOException ex) {
                Main.LOGGER.log(Level.SEVERE, "Error getting response.", ex);
            }
        } catch (MalformedURLException ex) {
            Main.LOGGER.log(Level.SEVERE, "Malformed URL: {0}", ex.getMessage());
        }
        return response;
    }
}
