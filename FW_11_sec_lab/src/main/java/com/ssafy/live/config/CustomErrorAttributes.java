package com.ssafy.live.config;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.webmvc.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
            ErrorAttributeOptions options) {
        Map<String, Object> attributes = super.getErrorAttributes(webRequest, options);
        Throwable error = getError(webRequest);

        if (error != null) {
            // log.error("SpringBoot 기본 예외 처리 로깅: ", error);
            log.error(error.getMessage());
        }
        return attributes;
    }
}
