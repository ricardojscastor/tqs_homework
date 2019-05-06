/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tqshw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ricardo
 */

@Controller
public class WebController {
    
    @Autowired
    private LocalRepository localRep;
    
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("locals", localRep.findAll());
        return "index";
    }
    
}
