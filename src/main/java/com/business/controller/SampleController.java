package com.business.controller;

import com.business.beans.Label;
import com.business.beans.Pkg.SampleLabel;
import com.business.beans.Sample;
import com.business.beans.User;
import com.business.services.LabelServiceImpl;
import com.business.services.SampleServiceImpl;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private SampleLabel midSampleLabel;
    @GetMapping("/all")
    public String getAllSample(Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        List<Sample> _sample = sampleService.getAll();
        modelSol.addAttribute("samples", _sample);
        if (_sample.isEmpty()) return "redirect:/sample/add";
        else return "sample.all";
    }
    @RequestMapping ("/add")
    public String addSample(Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        modelSol.addAttribute("labels", labelService.getAll());
        return "sample.add";
    }
    @PostMapping("/save")
    public String save(@RequestParam("labelId") int labelId,@RequestParam("file")MultipartFile file,HttpSession session)
            throws IOException {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        Sample sample = new Sample();
        int lId = 0;
        for (var b : sampleService.getAll()) {
            if(lId < b.getId())
                lId = b.getId();
        }
        sample.setId(lId + 1);
        sample.setLink(uploadImageFunc(file));
        sample.setName(labelService.getLabelNameById(labelId));
        LocalDate date = LocalDate.now();
        sample.setValidDate(Date.valueOf(date));
        sampleService.saveSample(sample);
        return "redirect:/sample/all";
    }
    @PostMapping("/filter/{name}")
    public String filterSampleByName(@PathVariable("name") String name, Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        List<Sample> _sample = sampleService.getListSampleByName(name);
        modelSol.addAttribute("samples", _sample);
        return "sample.filter";
    }
    @RequestMapping("/edit/{id}")
    public String editSample(@PathVariable("id") int id, Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        Sample sample =  sampleService.getSampleById(id).get();
        SampleLabel sLabel = new SampleLabel(id, sample.getLink(), labelService.getLabelByName(sample.getName()).getId());
        modelSol.addAttribute("samLabel", sLabel);
        midSampleLabel = sLabel;
        modelSol.addAttribute("labels", labelService.getAll());
        return "sample.edit";
    }
    @RequestMapping("/update")
    public String saveEdited(
                        @ModelAttribute("samLabel") SampleLabel sampleLabel,
                       @RequestParam("labelId") int labelId,
                       Model modelSol,
                       HttpSession session)
            throws IOException {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        if(labelId != midSampleLabel.getLabelId())
            midSampleLabel.setLabelId(labelId);
        Sample sample = new Sample();
        sample.setId(midSampleLabel.getSampleId());
        String label = labelService.getLabelNameById(midSampleLabel.getLabelId());
        sample.setName(label);
        sample.setLink(midSampleLabel.getLink());
        LocalDate date = LocalDate.now();
        sample.setValidDate(Date.valueOf(date));
        sampleService.saveSample(sample);
        midSampleLabel = null;
        return "redirect:/sample/all";
    }
    @RequestMapping("/deleteConfirm/{id}")
    public String deleteSampleForm(@PathVariable("id") int id, Model modelSol, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        Sample sample =  sampleService.getSampleById(id).get();
        SampleLabel sLabel = new SampleLabel(id, sample.getLink(), labelService.getLabelByName(sample.getName()).getId());
        modelSol.addAttribute("samLabel", sLabel);
        midSampleLabel = sLabel;
        modelSol.addAttribute("labelName", labelService.getLabelNameById(midSampleLabel.getLabelId()));
        return "sample.delete.confirm";
    }
    @RequestMapping("/delete")
    public String deleteSample(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        sampleService.deleteSample(midSampleLabel.getSampleId());
        midSampleLabel = null;
        return "redirect:/sample/all";
    }
    private static void checkLog(Object objectInput) {
        System.out.print("\n");
        for (int i = 0; i < 10; i++)
            System.out.print("-");
        System.out.print(" CHECK LOG ");
        for (int i = 0; i < 10; i++)
            System.out.print("-");
        System.out.print("\n");
        System.out.print(objectInput.toString());
        for (int i = 0; i < 31; i++)
            System.out.print("-");
        System.out.print("\n");
    }
    private String uploadImageFunc(MultipartFile file) throws IOException {
        Cloudinary cloudinary = new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
        Map<String, String> uploadImage = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String img = uploadImage.get("url");
        return img;
    }
}
