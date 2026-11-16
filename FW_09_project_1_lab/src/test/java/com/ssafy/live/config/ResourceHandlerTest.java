package com.ssafy.live.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

// TODO: 03-2. 가상 경로 요청 시 실제 파일 내용이 반환되는지 확인하는 테스트 코드를 작성해보자.
public class ResourceHandlerTest {
    @Autowired
    private MockMvc mockMvc;
    // application.properties에 설정된 물리 업로드 경로
    @Value("${spring.servlet.multipart.location}")
    private String filePath;
    private File tempFile;

    @BeforeEach
    void setUp() throws Exception {
        // 1. 임시 저장 디렉토리가 없을 시 생성
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 2. 물리 경로에 임시 텍스트 파일(test.txt) 생성
        tempFile = new File(dir, "test.txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Hello Resource Handler!");
        }
    }

    @AfterEach
    void tearDown() {
        // 3. 테스트 끝난 후 임시 파일 제거
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    @DisplayName("가상경로 /upload/test.txt 요청 시 물리 파일 내용이 정상 반환되는지 확인")
    void testUploadResourceMapping() throws Exception {
        mockMvc.perform(get("/upload/test.txt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Resource Handler!"));
    }
}
