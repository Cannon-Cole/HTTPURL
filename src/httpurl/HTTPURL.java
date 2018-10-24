/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpurl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Cole
 */
public class HTTPURL {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) {
        HTTPURL http = new HTTPURL();

        try {
            http.sendGet();
            http.sendPost();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void sendGet() throws Exception {

        String url = "https://www.google.com";

        URL obj = new URL(url);
        HttpURLConnection connect = (HttpURLConnection) obj.openConnection();

        connect.setRequestMethod("GET");

        //add request header
        connect.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = connect.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(connect.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String array[] = response.toString().split("<");
        for (int i = 0; i < array.length; i++) {
            StringBuilder insertChar = new StringBuilder();
            insertChar.insert(0, "<" + array[i]);
            array[i] = insertChar.toString();
            System.out.println(array[i]);
        }

    }

    private void sendPost() throws Exception {

        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection connect = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        connect.setRequestMethod("POST");
        connect.setRequestProperty("User-Agent", USER_AGENT);
        connect.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        connect.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connect.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = connect.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(connect.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String array[] = response.toString().split("<");
        for (int i = 0; i < array.length; i++) {
            StringBuilder insertChar = new StringBuilder();
            insertChar.insert(0, "<" + array[i]);
            array[i] = insertChar.toString();
            System.out.println(array[i]);
        }

    }

}
