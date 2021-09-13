package com.greenart.controller;

import java.util.List;

import com.greenart.service.EmergencyService;
import com.greenart.service.TCLService;
import com.greenart.vo.HospitalVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {
    @Autowired EmergencyService service;
    @Autowired TCLService t_service;
    @GetMapping("/Detail")
    public String Detail(@RequestParam String hpid, Model model) {

        List<HospitalVO> list = service.selectHospital_info(hpid);
        model.addAttribute("list", list);   

        return "/Detail/Detail";
    }
}
