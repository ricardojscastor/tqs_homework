/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tqshw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ricardo
 */

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WeatherStatus {
   
    @Id
    private int idWeatherType;
    
    private String descWeatherTypePT;
    
    public WeatherStatus() {}

    public WeatherStatus(int idWeatherType, String descWeatherTypePT) {
        this.idWeatherType = idWeatherType;
        this.descWeatherTypePT = descWeatherTypePT;
    }

    public int getIdWeatherType() {
        return idWeatherType;
    }

    public void setIdWeatherType(int idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    public String getDescWeatherTypePT() {
        return descWeatherTypePT;
    }

    public void setDescWeatherTypePT(String descWeatherTypePT) {
        this.descWeatherTypePT = descWeatherTypePT;
    }

    @Override
    public String toString() {
        return "WeatherStatus{" + "idWeatherType=" + idWeatherType + ", descWeatherTypePT=" + descWeatherTypePT + '}';
    }
    
    
    
}
