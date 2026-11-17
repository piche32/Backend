package com.ssafy.live.restcontroller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/etc")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "EtcRestController", description = "기타 유틸리티성 기능 제공")
public class EtcRestController {

        @Value("${api.key_vworld}")
        private String vworldKey;

        @GetMapping("/sidos")
        @Operation(summary = "시/도조회", description = "국토교통부 디지털트윈 국도에서 시/도 정보 조회")
        public ResponseEntity<?> getSido() {

                UriComponentsBuilder builder = UriComponentsBuilder
                                .fromUriString("https://api.vworld.kr/ned/data/admCodeList")
                                .queryParam("format", "json")
                                .queryParam("domain", "localhost")
                                .queryParam("numOfRows", "{numOfRows}") // 필요 시 바인딩 처리 가능
                                .queryParam("key", "{key}")
                                .encode();

                URI uri = builder.buildAndExpand(10, vworldKey) // 바인딩 변수에 대해 순차적으로 값을 대입
                                .toUri();

                RestClient client = RestClient.create(uri);
                Map<String, Object> resultMap = client.get() // Http Method 별 처리
                                .retrieve() // 요청
                                .body(new ParameterizedTypeReference<Map<String, Object>>() {
                                });// 결과 포멧
                return ResponseEntity.ok(resultMap);
        }
}
