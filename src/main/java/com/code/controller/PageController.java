package com.code.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = "/error")
    public String myerror() {
        return "/error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
