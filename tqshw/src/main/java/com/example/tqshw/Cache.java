/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tqshw;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ricardo
 */

@Entity
public class Cache {
    
    @Id
    private String name;
    
    private int nRequests;
    private int nHits;
    private int nMisses;
    
    public Cache(){}

    public Cache(String name){
        this.name = name;
        this.nRequests = 0;
        this.nHits = 0;
        this.nMisses = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnRequests() {
        return nRequests;
    }

    public void setnRequests(int nRequests) {
        this.nRequests = nRequests;
    }

    public int getnHits() {
        return nHits;
    }

    public void setnHits(int nHits) {
        this.nHits = nHits;
    }

    public int getnMisses() {
        return nMisses;
    }

    public void setnMisses(int nMisses) {
        this.nMisses = nMisses;
    }

    @Override
    public String toString() {
        return "Cache{" + "name=" + name + ", nRequests=" + nRequests + ", nHits=" + nHits + ", nMisses=" + nMisses + '}';
    }
    
    
    
}
