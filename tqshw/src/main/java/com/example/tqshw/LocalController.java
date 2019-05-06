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
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ricardo
 */

@Controller
@RequestMapping(path = "/local")
public class LocalController {
    
    @Autowired
    private LocalRepository localRep;
    
    @Autowired
    private WeatherStatusRepository weatherStatusRep;
    
    @Autowired
    private CacheRepository cacheRep;
    
    @GetMapping
    @CrossOrigin(origins = "*")
    public @ResponseBody Iterable<Local> findAllLocals() {
        CCache.onRequest(cacheRep.getOne("cache"), cacheRep);
        return localRep.findAll();
    }
    
    @GetMapping(path = "/{id}")
    public Local findLocalById(@PathVariable("id") int id) {
        CCache.onRequest(cacheRep.getOne("cache"), cacheRep);
        Local local = localRep.getOne(id);
        return local;
    }
    
    
    @GetMapping(path = "/{id}/weather")
    public @ResponseBody Iterable<Weather> weatherByLocalId(@PathVariable("id") int id) throws Exception {
        CCache.onRequest(cacheRep.getOne("cache"), cacheRep);
        Local local = localRep.getOne(id);
        List<Weather> weatherList;
        
        if (local.getWeather().isEmpty()) {
            weatherList = getLocalWeather(id, weatherStatusRep);
            System.out.println(weatherList);
            for (Weather weather : weatherList) {
                local.getWeather().add(weather);
            }
            localRep.save(local);
            
            CCache.onMiss(cacheRep.getOne("cache"), cacheRep);
            System.out.println("Refresh");
        } else {
            weatherList = local.getWeather();
            CCache.onHit(cacheRep.getOne("cache"), cacheRep);
            System.out.println("Cache");
        }
        return weatherList;
    }
    
   
    private static List<Weather> getLocalWeather(int globalIdLocal, WeatherStatusRepository weatherStatusRep) throws Exception{
        List<Weather> weatherList = new ArrayList<>();
        URL url = new URL("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/" + globalIdLocal + ".json");
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
                int idWeatherType = (int)obj.get("idWeatherType");
                Weather weather = new Weather((String)obj.get("tMin"), (String)obj.get("tMax"), (String)weatherStatusRep.getOne(globalIdLocal).getDescWeatherTypePT(), (String) obj.get("predWindDir"), (Integer)obj.get("classWindSpeed"), (String)obj.get("forecastDate"));
                weatherList.add(weather);
            }
        }
        
        return weatherList;
    }
    
}
