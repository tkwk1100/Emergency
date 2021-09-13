package com.greenart.controller;

import java.util.List;

import com.greenart.service.TCLService;
import com.greenart.vo.TCLVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TCLController {
    @Autowired
    TCLService service;
    @GetMapping("/TCL")
    public String TCL(Model model) {
        List<TCLVO> tcl = service.selectTCLAll();
        model.addAttribute("tcl", tcl);
        return "TCL/TCL";
    }
}
