package com.ssafy.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// TODO: 05-2. @Controller로 변경하고 /index, / 요청에 대한 handler 메서드를 추가하세요.
@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
    	return "index";
    }
}
