package com.ssafy.live.mybatis;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicSimpleService  {

    @Transactional(propagation = Propagation.REQUIRED)
    public void required(int mod) {
        int i = 1 / mod;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew(int mod) {
        int i = 1 / mod;
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested(int mod) {
        int i = 1 / mod;
    }
}
