package com.example.dis.v0;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddDis {

    @PostMapping("/addDis")
    public String addDis() {
        return "addDis";
    }
}
