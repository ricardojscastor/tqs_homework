/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tqshw;

/**
 *
 * @author ricardo
 */
public class CCache {
    
    public static void createCache(CacheRepository cacheRep, String name) {
        cacheRep.save(new Cache(name));
    }
    
    public static void onRequest(Cache cache, CacheRepository cacheRep) {
        cache.setnRequests(cache.getnRequests()+1);
        cacheRep.save(cache);
    }
    
    public static void onHit(Cache cache, CacheRepository cacheRep) {
        cache.setnHits(cache.getnHits()+1);
        cacheRep.save(cache);
    }
    
    public static void onMiss(Cache cache, CacheRepository cacheRep) {
        cache.setnMisses(cache.getnMisses()+1);
        cacheRep.save(cache);
    }
    
}
