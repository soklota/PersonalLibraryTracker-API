package com.simulationgame.CancelledSimulator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class CancelledSimController {

    @GetMapping("/home-page")
    public String simulationHome() {
        return "home-page";
    }
}
