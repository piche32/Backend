package com.ssafy.live.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BootMainController implements ControllerHelper {

    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        return "index";
    }

    // chrome devtools용 빈 json 응답(그냥 오류 방지)
    @GetMapping("/.well-known/appspecific/com.chrome.devtools.json")
    @ResponseBody
    public Map<String, Object> chromeDevTools() {
        return Map.of();
    }

    @GetMapping({ "/problem" })
    public String problem() {
        int i = 1 / 0; // 고의로 예외 발생
        return "index";
    }
}
