package com.example.doas.v0;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class doasController {

    @PostMapping("/addDoas")
    public String addDoas() {
        return "success";
    }
}
