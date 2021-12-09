package com.example.doms.v0;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "doasClient", url = "http://localhost:9002")
public interface DoasClient {
    @PostMapping("/addDoas")
    String addDoas();
}
