package com.business.controller;

import com.business.beans.Label;
import com.business.beans.Sample;
import com.business.beans.User;
import com.business.services.LabelServiceImpl;
import com.business.services.SampleServiceImpl;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sample")
public class SampleController {
    @Value("${cloudinary.cloud_name}")
    private String cloudName;
    @Value("${cloudinary.api_key}")
    private String apiKey;
    @Value("${cloudinary.api_secret}")
    private String apiSecret;
    @Autowired
    private SampleServiceImpl sampleService;
    @Autowired
    private LabelServiceImpl labelService;
    @GetMapping("/all")
    public String getAllSample(Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            if(user == null) {
                return "redirect:/login";
            } else {
                session.setAttribute("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Sample> _sample = sampleService.getAll();
        modelSol.addAttribute("samples", _sample);
        if (_sample.isEmpty()) return "redirect:/sample/add";
        else return "sample.all";
    }
    @RequestMapping ("/add")
    public String addSample(Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            if(user == null) {
                return "redirect:/login";
            } else {
                session.setAttribute("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelSol.addAttribute("labels", labelService.getAll());
        return "sample.add";
    }
    @PostMapping("/save")
    public String save(@RequestParam("labelId") int labelId,@RequestParam("file")MultipartFile file,HttpSession session)
            throws IOException {
        User user = (User) session.getAttribute("user");
        try {
            if(user == null) {
                return "redirect:/login";
            } else {
                session.setAttribute("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sample sample = new Sample();
        int lId = 0;
        for (var b : sampleService.getAll()) {
            if(lId < b.getId())
                lId = b.getId();
        }
        sample.setId(lId + 1);
        sample.setLink(uploadImageFunc(file));
        sample.setName(labelService.getLabelById(labelId));
        LocalDate date = LocalDate.now();
        sample.setValidDate(Date.valueOf(date));
        sampleService.saveSample(sample);
        return "redirect:/sample/all";
    }
    @PostMapping("/filter/{name}")
    public String filterSampleByName(@PathVariable("name") String name, Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            if(user == null) {
                return "redirect:/login";
            } else {
                session.setAttribute("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Sample> _sample = sampleService.getListSampleByName(name);
        modelSol.addAttribute("samples", _sample);
        return "sample.filter";
    }
    @PostMapping("/delete?confirm/{id}")
    public String confirmDeleteView(@PathVariable("id") int id, Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            if(user == null) {
                return "redirect:/login";
            } else {
                session.setAttribute("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelSol.addAttribute("sample", sampleService.getSampleById(id));
        session.setAttribute("id", id);
        return "sample.delete.confirm";
    }
    @PostMapping("/delete/{id}")
    public String deleteSample(@PathVariable("id") int id, Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            if(user == null) {
                return "redirect:/login";
            } else {
                session.setAttribute("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sampleService.deleteSample(id);
        session.removeAttribute("id");
        return "redirect:/sample/all";
    }
    @RequestMapping("/edit/{id}")
    public String editSample(@PathVariable("id") int id, Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            if(user == null) {
                return "redirect:/login";
            } else {
                session.setAttribute("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelSol.addAttribute("sampleLink", sampleService.getSampleById(id).get().getLink());
        modelSol.addAttribute("sampleName", sampleService.getSampleById(id).get().getName());
        modelSol.addAttribute("id", id);
        modelSol.addAttribute("labels", labelService.getAll());
        return "sample.edit";
    }
    @PostMapping("/saveE")
    public String saveEdited(@RequestParam("id") int id,
                        @RequestParam("sampleLink") String sampleLink,
                        @RequestParam("sampleName") String sampleName,
                        @RequestParam("labelId") int labelId,
                       @RequestParam("file")MultipartFile file,
                       Model modelSol,
                       HttpSession session)
            throws IOException {
        Sample sample = sampleService.getSampleById(id).get();
        if(file == null) sample.setLink(sampleLink);
        else sample.setLink(uploadImageFunc(file));
        sample.setName(sampleName);
        LocalDate date = LocalDate.now();
        sample.setValidDate(Date.valueOf(date));
        sampleService.saveSample(sample);
        return "redirect:/sample/all";
    }
    private String uploadImageFunc(MultipartFile file) throws IOException {
        Cloudinary cloudinary = new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
        Map<String, String> uploadImage = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String img = uploadImage.get("url");
        return img;
    }
}
