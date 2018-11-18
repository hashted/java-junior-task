package com.mcb.javajuniortask.controller;

import com.mcb.javajuniortask.model.User;
import com.mcb.javajuniortask.service.ClientService;
import com.mcb.javajuniortask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("thymeleaf/login");

        return modelAndView;
    }

    @RequestMapping(value={"/admin"}, method = RequestMethod.GET)
    public ModelAndView admin(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByLogin(auth.getName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", user.getLogin());
        modelAndView.addObject("clientsList", clientService.showAllClients());
        modelAndView.setViewName("thymeleaf/admin");

        return modelAndView;
    }
}
