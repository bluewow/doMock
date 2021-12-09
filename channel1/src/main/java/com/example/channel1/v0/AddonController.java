package com.example.channel1.v0;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddonController {

    @PostMapping("/addAddon")
    public String addOn() {
        return "success";
    }
}
