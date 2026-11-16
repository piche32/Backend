package com.ssafy.live.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

// TODO: 05-1. setupCookie를 제외한 불필요한 메서드를 제거하세요.
public interface ControllerHelper {
    public default Cookie setupCookie(String name, String value, int maxAge, String path, HttpServletResponse resp) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        if (path != null) {
            cookie.setPath(path);
        }
        resp.addCookie(cookie);
        return cookie;
    }
}
