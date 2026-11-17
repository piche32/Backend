package com.ssafy.live.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ControllerAdvice
public class MvcExceptionHandler {

    // Spring Security 예외는 ExceptionTranslationFilter가 처리할 수 있도록 다시 던짐
    @ExceptionHandler({ org.springframework.security.access.AccessDeniedException.class,
            org.springframework.security.authorization.AuthorizationDeniedException.class })
    public void handleAccessDeniedException(RuntimeException e) {
        throw e;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handle500(Exception e) {
        log.info("[@Controller에서 발생한 오류 처리]: {}", e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.setViewName("error/500");
        return mav;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ModelAndView handle405(Exception e) {
        log.info("[@Controller에서 발생한 오류 처리]: {}", e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.setViewName("error/405");
        return mav;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handle404(Exception e) {
        log.info("[@Controller에서 발생한 오류 처리]: {}", e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.setViewName("error/404");
        return mav;
    }
}
