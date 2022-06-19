package com.yggdrasil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    @CrossOrigin(origins = "*")
    public String index() {
        return "index.html";
    }
}
