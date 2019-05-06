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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ricardo
 */

@Controller
@RequestMapping(path = "/weatherstatus")
public class WeatherStatusController {
    
    @Autowired
    private WeatherStatusRepository weatherStatusRep;
    
    @CrossOrigin(origins = "*")
    @GetMapping
    public @ResponseBody Iterable<WeatherStatus> findAll() {
        return weatherStatusRep.findAll();
    }
    
    @GetMapping(path = "/{id}/pt")
    public @ResponseBody String descriptionPT(@PathVariable("id") int id) {
        WeatherStatus status = weatherStatusRep.getOne(id);
        return status.getDescWeatherTypePT();
    }
    
}
