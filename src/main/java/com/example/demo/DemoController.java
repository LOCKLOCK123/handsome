package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by linla on 2021/7/28.
 */
@RestController
public class DemoController {

    @PostMapping("/demo")
    public String qifei(){
        return "Demo接口访问成功";
    }

}
