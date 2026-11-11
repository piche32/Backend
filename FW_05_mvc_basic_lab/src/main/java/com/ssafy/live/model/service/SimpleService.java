package com.ssafy.live.model.service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SimpleService {

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    public String echo(String data) {
        return "echo: " + data;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public Map<String, Object> uploadFile(MultipartFile file, String desc) throws IOException {
        String fileName = file.getOriginalFilename();
        File localFile = new File(filePath, fileName);
        file.transferTo(localFile);

        String base64 = "";
        String mimeType = file.getContentType();
        base64 = "data:%s;base64,%s".formatted(mimeType,
        		Base64.getEncoder().encodeToString(file.getBytes()));
        

        return Map.of("fileName", fileName, "desc", desc != null ? desc : "", "base64", base64);
    }
}
