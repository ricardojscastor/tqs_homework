/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tqshw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ricardo
 */

@Controller
@RequestMapping(path = "/stats")
public class CacheController {
    
    @Autowired
    private CacheRepository cacheRep;
    
    
    @CrossOrigin(origins = "*")
    @GetMapping
    public @ResponseBody Iterable<Cache> findAll() {
        return cacheRep.findAll();
    }
    
}
