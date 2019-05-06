/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tqshw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ricardo
 */

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Weather implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String tMin;
    private String tMax;
    private String idWeatherType;
    private String predWindDir;
    private int classWindSpeed;
    private String forecastDate;
    
    public Weather(){}

    public Weather(String tMin, String tMax, String idWeatherType, String predWindDir, int classWindSpeed, String forecastDate) {
        this.tMin = tMin;
        this.tMax = tMax;
        this.idWeatherType = idWeatherType;
        this.forecastDate = forecastDate;
        this.classWindSpeed = classWindSpeed;
        this.predWindDir = predWindDir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettMin() {
        return tMin;
    }

    public void settMin(String tMin) {
        this.tMin = tMin;
    }

    public String gettMax() {
        return tMax;
    }

    public void settMax(String tMax) {
        this.tMax = tMax;
    }

    public String getIdWeatherType() {
        return idWeatherType;
    }

    public void setIdWeatherType(String idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    public String getPredWindDir() {
        return predWindDir;
    }

    public void setPredWindDir(String predWindDir) {
        this.predWindDir = predWindDir;
    }

    public int getClassWindSpeed() {
        return classWindSpeed;
    }

    public void setClassWindSpeed(int classWindSpeed) {
        this.classWindSpeed = classWindSpeed;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    @Override
    public String toString() {
        return "Weather{" + "id=" + id + ", tMin=" + tMin + ", tMax=" + tMax + ", idWeatherType=" + idWeatherType + ", predWindDir=" + predWindDir + ", classWindSpeed=" + classWindSpeed + ", forecastDate=" + forecastDate + '}';
    }
    
    
    
}
