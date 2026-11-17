package com.ssafy.live.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.live.restcontroller.MemberRestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackageClasses = MemberRestController.class)
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleRestException(Exception e) {
        log.error("[@RestController에서 발생한 오류] ", e);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
