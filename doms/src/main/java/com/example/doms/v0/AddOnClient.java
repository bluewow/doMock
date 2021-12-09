package com.example.doms.v0;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "addonClient", url = "http://localhost:9005")
public interface AddOnClient {

    @PostMapping("/addAddon")
    String addAddon();
}
