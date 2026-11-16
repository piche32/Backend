package com.ssafy.live.restcontroller;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.live.httpclient.BingClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/etc")
@Slf4j
@Tag(name = "EtcRestController", description = "기타 유틸리티성 기능 제공")
public class EtcRestController {

        @GetMapping("/bing-image")
        @Operation(summary = "빙 바탕화면 조회", description = "빙 바탕화면 정보 조회")
        private ResponseEntity<?> bingImage() {

                UriComponentsBuilder builder = UriComponentsBuilder
                                .fromUriString("https://www.bing.com/HPImageArchive.aspx")
                                .queryParam("format", "js")
                                .queryParam("idx", "0")
                                .queryParam("n", "1")
                                .queryParam("mkt", "ko-KR")
                                .encode();

                // TODO: 10-2. 빙에서 오늘의 이미지를 조회 하는 proxy를 구성 후 조회 결과를 반환하세요.
                //  2 - RestClient 생성 및 GET 요청 처리
                RestClient restClient = RestClient.create(builder.toUriString());
                //ajax가 아닌 일반 http 요청 -> SOP 적용 안됨!
                Map<String, Object> result = restClient.get().retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {});
                
                 return ResponseEntity.status(HttpStatus.OK).body(result);

                // END
        }

        // TODO: 11-3. RestClient를 이용한 BingClient를 주입 받아서 처리해보자.

        // END
}
