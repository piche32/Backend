package com.ssafy.live.httpclient;

import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

// TODO: 11-1. BingClient 인터페이스를 작성해보자.
@HttpExchange("https://www.bing.com")
public interface BingClient {
    @GetExchange("/HPImageArchive.aspx")
    Map<String, Object> getTodayImage(
            @RequestParam("format") String format,
            @RequestParam("idx") int idx,
            @RequestParam("n") int n,
            @RequestParam("mkt") String mkt);
}

// END
