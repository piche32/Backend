package com.ssafy.live.model.service;

import java.util.List;

import com.ssafy.live.model.dto.Member;

public interface MemberService {

    int registMember(Member member);

    void reset();

    List<Member> search();

    Member login(String email, String password);
}
