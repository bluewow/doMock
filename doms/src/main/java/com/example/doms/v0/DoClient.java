package com.example.doms.v0;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "doClient", url = "http://localhost:9003")
public interface DoClient {

    @PostMapping("/addDo")
    String addDo();
}
