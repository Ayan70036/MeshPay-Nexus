package com.ayan.OfflineUpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controller for the home page
@Controller
public class HomeController {

    // Serves the home page
    @GetMapping("/")
    public String home() {

        return "index";
    }
}