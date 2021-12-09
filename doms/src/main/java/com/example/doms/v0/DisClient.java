package com.example.doms.v0;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "disClient", url = "http://localhost:9004")
public interface DisClient {

    @PostMapping("/addDis")
    String addDis();
}
