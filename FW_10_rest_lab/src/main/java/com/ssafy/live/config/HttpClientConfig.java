package com.ssafy.live.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.ssafy.live.httpclient.BingClient;

@Configuration
public class HttpClientConfig {

    // TODO: 11-2. RestClient를 이용한 BingClient 빈을 등록해보자.
    @Bean
    BingClient bingClient() {
        // 1. 기본 RestClient 생성 (Base URL은 인터페이스의 @HttpExchange에 명시)
        RestClient restClient = RestClient.create();
        // 2. RestClientAdapter 생성
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        // 3. HttpServiceProxyFactory로 프록시 구현체 생성
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        // 4. 인터페이스 구현체 빈 반환
        return factory.createClient(BingClient.class);
    }

}
