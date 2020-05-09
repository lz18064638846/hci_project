package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zhe Li
 * @date 2020/05/09
 */
@Controller
public class SwaggerController {
    @RequestMapping("/api/swagger/swagger-resources")
    public String resource() {
        return "forward:/swagger-resources";
    }

    @RequestMapping("/api/swagger/swagger-resources/configuration/ui")
    public String ui() {
        return "forward:/swagger-resources/configuration/ui";
    }

    @RequestMapping("/api/swagger/v2/api-docs")
    public String doc() {
        return "forward:/v2/api-docs";
    }

    @RequestMapping("/api/swagger/swagger-resources/configuration/security")
    public String security() {
        return "forward:/swagger-resources/configuration/security";
    }

    @RequestMapping("/api/swagger/swagger-ui.html")
    public String html(){
        return "forward:/swagger-ui.html";
    }
}
