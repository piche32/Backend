package com.ssafy.live.configure;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// TODO: 11-1. @ControllerAdvice를 이용해서 controller 패키지에서 발생하는 예외 처리
// 예외가 발생하면 로그로 예외 메시지를 출력하고, error/500 페이지로 이동하도록 구현하세요.
 public class MvcExceptionHandler {}

// END
