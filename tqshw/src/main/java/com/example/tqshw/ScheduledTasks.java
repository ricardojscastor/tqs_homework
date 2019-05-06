/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tqshw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author ricardo
 */

@Component
public class ScheduledTasks {
    
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    
    private static Boolean CACHEINIT = false;
    
    @Autowired
    private LocalRepository localRep;
    
    @Autowired
    private WeatherRepository weatherRep;
    
    @Autowired
    private WeatherStatusRepository weatherStatusRep;
    
    @Autowired
    private CacheRepository cacheRep;
    
    @Scheduled(fixedRate=60000)
    public void run() throws Exception {
        getLocals();
                
        if (CACHEINIT==false) {
            getWeatherStatus();
            CCache.createCache(cacheRep, "cache");
            CACHEINIT = true;
        }
    }
    
    public void getLocals() throws Exception {
        URL url = new URL("http://api.ipma.pt/open-data/distrits-islands.json");
        String line = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            
            JSONObject jsonObj = new JSONObject(response.toString());
            JSONArray locals = jsonObj.getJSONArray("data");
            
            for (int i = 0; i < locals.length(); i++) {
                JSONObject obj = locals.getJSONObject(i);
                Local local = new Local((Integer)obj.get("globalIdLocal"), (String)obj.get("local"));
                localRep.save(local);
            }
            
        } else {
            System.out.println("GET DID NOT WORK");
        }
       
    }
    
    public void getWeatherStatus() throws Exception {
        URL url = new URL("http://api.ipma.pt/open-data/weather-type-classe.json");
        String line = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            
            JSONObject jsonObj = new JSONObject(response.toString());
            JSONArray weatherTypes = jsonObj.getJSONArray("data");
            
            for (int i = 0; i < weatherTypes.length(); i++) {
                JSONObject obj = weatherTypes.getJSONObject(i);
                WeatherStatus weather = new WeatherStatus((Integer)obj.get("idWeatherType"), (String)obj.get("descIdWeatherTypePT"));
                weatherStatusRep.save(weather);
            }
        }else {
            System.out.println("GET DID NOT WORK");
        }     
    }
}
