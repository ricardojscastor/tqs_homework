/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tqshw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ricardo
 */

@Entity
public class Local implements Serializable {
    
    @Id
    private int globalIdLocal;
   
    private String local;
    
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Weather> weather = new ArrayList<>();
    
    public Local(){}
    
    public Local(int globalIdLocal, String local) {
        this.globalIdLocal = globalIdLocal;
        this.local = local;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }


    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Local{" + "globalIdLocal=" + globalIdLocal + ", local=" + local + ", weather=" + weather + '}';
    }

    
    
}
