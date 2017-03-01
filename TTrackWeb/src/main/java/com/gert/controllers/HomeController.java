package com.gert.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gert on 27.02.17.
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(){

        return "home";
    }
}
