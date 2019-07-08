package com.fse.user.controller;

import com.fse.user.model.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView userLogout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav =  new ModelAndView("login");
        mav.addObject("login", new Login());

        mav.addObject("msg", "You've been logged out successfully.");
        return mav;
    }
}