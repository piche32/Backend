package com.ssafy.live.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.live.model.service.Member;
import com.ssafy.live.model.service.SimpleService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO: 04-1. /simple 요청을 처리하기 위한Controller를 구성하세요.
@Controller
@RequestMapping("/simple")
@Slf4j
@RequiredArgsConstructor

public class SimpleController {
    // TODO: 04-2. 각각 GET: /simple/, GET: /simple/hello 요청을처리하기 위한 요청 처리 메서드를
    // 작성하세요.
	//string: logical한 view name
	@GetMapping({"/", "/hello"})
	public String hello() {
		System.out.println("================================================");
		return "index";
	}
    // END

    // TODO: 05-1. SimpleService를 주입받아서 GET: /simple/echo 요청으로 전달된 msg를 echo
    // 해보자.
    //  화면에서는 request scope에서 msg attribute를 사용한다.
     private final SimpleService service;
     
     @GetMapping("/echo")
     public String echo(@RequestParam String msg, Model model) {
    	 // 비즈니스 로직 처리
    	 String result = service.echo(msg);
    	 // scope에 화면을 위한 데이터 저장
    	 model.addAttribute("msg", result);
    	 //화면 연결
    	 return "index";
     }

    // END

    // TODO: 05-3. POST: /simple/add 요청으로 전달된 파라미터들을 더해서 반환해보자.
     @PostMapping("/add")
     public String add(@RequestParam int num1, @RequestParam int num2, Model model) {
    	 int result = service.add(num1, num2);
    	 model.addAttribute("result", result);
    	 return "index";
     }
    // END

    // TODO: 06-1. GET: /simple/redir 요청이 들어오면 / 로 redirect 처리해보자.

    // END

    // TODO: 06-2. GET: /simple/json 요청이 들어오면 사용자 정보를 map에 담아 반환해보자.

    // END

    // TODO: 08-2. POST: /simple/regist로 전달된 파라미터들을 이용해서 회원 가입 해보자.
    //  Member 객체를 생성하고, 세션에 저장 후 "/"로 리다이렉트 처리

    // END

    // TODO: 09-1. GET: /simple/cookie로 전달된 쿠키에서 name, age을 추출해서 Map으로 반환해보자.

    // END

    // TODO: 10-2. GET: /simple/redir-attr 요청이 들어오면 세션, 모델, 리다이렉트 속성에 값을 담아
    // "/"로 리다이렉트 처리해보자.

    // END

    @GetMapping("/problem")
    public String problem() {
        int i = 1 / 0;
        return "index";
    }

    // TODO: 12-3. file과 desc를 request로 전달받아 파일 업로드를 위한 controller 동작을 확인하세요.
    // upload된 파일의 정보를 model을 통해 전달한다.

    @PostMapping("/fileupload")
    public String fileUpload(@RequestParam MultipartFile file, @RequestParam(required = false) String desc, Model model)
            throws IllegalStateException, IOException {
        model.addAllAttributes(service.uploadFile(file, desc));
        return "index";
    }

    @PostMapping("/fileupload-ajax")
    @ResponseBody
    public Map<String, Object> fileUploadAjax(@RequestParam MultipartFile file,
            @RequestParam(required = false) String desc) throws IllegalStateException, IOException {
        return service.uploadFile(file, desc);
    }
}
