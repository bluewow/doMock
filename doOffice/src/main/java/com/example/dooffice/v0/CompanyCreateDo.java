package com.example.dooffice.v0;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyCreateDo {

    @PostMapping("/addDo")
    public String addCompany() {
        return "saas";
    }
}
