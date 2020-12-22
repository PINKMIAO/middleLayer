package com.meorient.avaya.controller;

import com.alibaba.fastjson.JSON;
import com.meorient.avaya.pojo.PhoneCache;
import com.meorient.avaya.service.CallService;
import com.meorient.avaya.service.PhoneCacheServiceImpl;
import com.meorient.avaya.service.PhoneRequestLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CallController {

    @Autowired
    CallService callService;
    @Autowired
    PhoneCacheServiceImpl phoneCacheService;
    @Autowired
    PhoneRequestLogServiceImpl phoneRequestLogService;

    // for test
    @GetMapping("/hello")
    public String hello() {
        return "welcome!SpringBoot!";
    }
    @GetMapping("/add/{a}/{b}")
    public int addTest(@PathVariable int a, @PathVariable int b) {
        return a + b;
    }


    @GetMapping("/meorient/phone/{msg}")
    public Map<String, String> getCache(@PathVariable String msg) {
        Map<String, String> map = callService.callA(msg);
        return map;
    }

}
