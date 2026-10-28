package com.ssafy.live.model.service;

import java.util.List;

import com.ssafy.live.model.dao.FileMemberDao;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;

public class BasicSimpleService implements SimpleService {

    private static BasicSimpleService service = new BasicSimpleService();

    public static BasicSimpleService getService() {
        return service;
    }

    private BasicSimpleService() {
    }

    @Override
    public List<String> getGugu(int dan) {
        if (dan < 1 || dan > 10) {
            throw new RuntimeException("구구단 " + dan + "단");
        }
        List<String> list = new java.util.ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(dan + " * " + i + " = " + (dan * i));
        }
        return list;
    }

}
