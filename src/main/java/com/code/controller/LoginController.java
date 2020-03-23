package com.code.controller;

import com.code.model.System;
import com.code.model.User;
import com.code.service.SystemService;
import com.code.service.TelegramBot;
import com.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private TelegramBot bot;

    @Autowired
    SystemService systemService;

    @RequestMapping(value={"", "/", "/login"})
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping({"/home"})
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(auth.getName());
        mv.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
        mv.addObject("adminMessage","Content Available Only for Users with Admin Role");
        mv.setViewName("home");

        /* Send login notification
         */
        String message = user.getName() + " Login";
        System system = systemService.getSystemById(1L);
        bot.sendMessage(message, system.getToken(), system.getChatid());

        return mv;
    }

}
