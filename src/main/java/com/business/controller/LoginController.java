package com.business.controller;

import com.business.beans.User;
import com.business.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class LoginController {
    private User tempUser;
    @Autowired
    private JavaMailSender javaMailSender;
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
    @RequestMapping("/register")
    public String registerForm(Model modelSol) {
        modelSol.addAttribute("user", new User());
        return "register";
    }
    @RequestMapping("/register/save")
    public String confirmRegistration(@ModelAttribute("user") User user) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setSubject("Xác thực email");
        mail.setText("Bạn đã đăng ký vào hệ thống của chúng tôi với các thông tin sau : \n"
                + "Name : " + user.getName() + "\n"
                + "Username : " + user.getUsername() + "\n"
                + "Password : " + user.getPassword() + "\n"
                + "Email : " + user.getEmail() + "\n"
        );
        javaMailSender.send(mail);
        userService.save(user);
        return "redirect:/login";
    }
}
