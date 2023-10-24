package com.business.controller;

import com.business.beans.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getHome(HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            if(user == null) {
                return "redirect:/login";
            } else {
                return "#";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }
}
