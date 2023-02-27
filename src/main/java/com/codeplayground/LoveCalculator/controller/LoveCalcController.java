package com.codeplayground.LoveCalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeplayground.LoveCalculator.model.Compatability;
import com.codeplayground.LoveCalculator.service.LoveCalcService;

@Controller
@RequestMapping("/")
public class LoveCalcController {

    @Autowired
    private LoveCalcService lcs;

    @GetMapping("/getPercentage")
    public String getPercentage(Model model, @RequestParam String sname, @RequestParam String fname) {
        Compatability loveComp = lcs.getPercentage(sname, fname);
        model.addAttribute("loveComp", loveComp);
        return "result";
    }
}
