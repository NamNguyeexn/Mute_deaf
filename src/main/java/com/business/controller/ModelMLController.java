package com.business.controller;

import com.business.beans.ModelML;
import com.business.beans.User;
import com.business.services.ModelMLServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/modelml")
public class ModelMLController {
    @Value("${cloudinary.cloud_name}")
    private String cloudName;
    @Value("${cloudinary.api_key}")
    private String apiKey;
    @Value("${cloudinary.api_secret}")
    private String apiSecret;
    @Autowired
    private ModelMLServiceImpl modelMLService;
    @GetMapping("/all")
    public String getAllModel(Model modelSol, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        List<ModelML> modelML = modelMLService.getAll();
        modelSol.addAttribute("modelMLs", modelML);
        return "modelml.all";
    }
}
