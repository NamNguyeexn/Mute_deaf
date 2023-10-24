package com.business.controller;

import com.business.beans.User;
import com.business.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(@ModelAttribute("user")User user, Model modelSol, HttpServletRequest httpServletRequest) {
        try {
            user = userService.getUserByUsernameAndPassword(user.getUsername(),user.getPassword());
            if (user != null) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("user", user);
                return "redirect:/home";
            }
        } catch(Exception e) {
            modelSol.addAttribute("loi");
            return "login";
        }
        modelSol.addAttribute("loi");
        return "login";
    }
    @RequestMapping(path = {"/logout", "/login"})
    public ModelAndView returnLogin(HttpSession session) {
        session.invalidate();
        return new ModelAndView("login", "user", new User());
    }
}
