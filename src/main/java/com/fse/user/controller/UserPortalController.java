package com.fse.user.controller;

import com.fse.user.model.Login;
import com.fse.user.model.User;
import com.fse.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserPortalController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView showUpdate(HttpServletRequest request, HttpServletResponse response, @SessionAttribute("login") Login login, @ModelAttribute("user") User user) {
        user.setUsername(login.getUsername());


        ModelAndView mav = new ModelAndView("update");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/updateProcess", method = RequestMethod.POST)
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
        ModelAndView mav = null;
        if(userService.accountUpdate(user)==null){
            mav.addObject("message", "User account update failed!!");
        }
        mav = new ModelAndView("userportal");
        mav.addObject("username", user.getUsername());
        return mav;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView showAdmin(HttpServletRequest request, HttpServletResponse response, @SessionAttribute("login") Login login) {
        User user = new User();
        user.setUsername(login.getUsername());

        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("username", user.getName());
        return mav;
    }

    @RequestMapping(value = "/tutorials", method = RequestMethod.GET)
    public ModelAndView showTutorials(HttpServletRequest request, HttpServletResponse response, @SessionAttribute("login") Login login) {

        ModelAndView mav = new ModelAndView("tutorials");
        return mav;
    }

    @RequestMapping(value = "/userportal", method = RequestMethod.GET)
    public ModelAndView userPortal(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
        ModelAndView mav = null;
        mav = new ModelAndView("userportal");
        mav.addObject("username", user.getName());
        return mav;
    }
}
