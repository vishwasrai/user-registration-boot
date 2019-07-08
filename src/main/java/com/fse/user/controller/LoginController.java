package com.fse.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.fse.user.captcha.RecaptchaService;
import com.fse.user.model.Login;
import com.fse.user.model.User;
import com.fse.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("login")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RecaptchaService captchaService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "error", required = false) String error) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());

        if (error != null) {
            mav.addObject("error", "Invalid username and password!");
        }
        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpSession session, HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("login") Login login,
                                     BindingResult result, @RequestParam(name = "g-recaptcha-response") String recaptchaResponse) {
        ModelAndView mav = null;
        if (result.hasErrors()) {
            return new ModelAndView("login");
        }
        String ip = request.getRemoteAddr();
        String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, recaptchaResponse);

        if (captchaVerifyMessage != null) {
            mav = new ModelAndView("login");
            mav.addObject("message", captchaVerifyMessage);
            return mav;
        }
        User user = userService.validateUser(login.getUsername());

        if (null != user) {
            mav = new ModelAndView("userportal");
            mav.addObject("username", user.getName());
        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!!");
        }
        return mav;
    }
}