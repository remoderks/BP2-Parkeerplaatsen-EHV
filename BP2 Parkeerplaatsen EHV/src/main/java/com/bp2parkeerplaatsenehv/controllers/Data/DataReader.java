package com.bp2parkeerplaatsenehv.controllers.Data;

import com.bp2parkeerplaatsenehv.Model.ParkingData;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DataReader {
    public ParkingData readData(){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url
                    = new URL("https://data.eindhoven.nl/api/explore/v2.1/catalog/datasets/parkeerplaatsen/records?limit=100");
            HttpURLConnection connection
                    = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responsecode = connection.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("ResponseCode: " + responsecode);
            } else {
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    stringBuilder.append(scanner.nextLine());
                }
                scanner.close();
            }
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Raw JSON Response: " + stringBuilder.toString()); // debugging statement

        Gson gson = new Gson();
        return gson.fromJson(stringBuilder.toString(), ParkingData.class);
    }
}
