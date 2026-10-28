package com.ssafy.live.model.service;

import com.ssafy.live.model.dao.FileMemberDao;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;

public class BasicMemberService implements MemberService {
    private MemberDao dao = FileMemberDao.getInstance();

    private static BasicMemberService service = new BasicMemberService();

    private BasicMemberService() {
    }

    public static BasicMemberService getService() {
        return service;
    }

    @Override
    public int registMember(Member member) {
        return dao.insert( member);
    }

    @Override
    public void reset() {
        dao.reset();
    }

    @Override
    public Page<Member> search(SearchCondition condition) {
        Page<Member>  page = dao.search( condition);
        return page;
    }

    @Override
    public Member login(String email, String password) {
        return dao.login( email, password);
    }

    @Override
    public Member findByEmail(String email) {
        return dao.findByEmail( email);
    }

    @Override
    public Member memberModify(Member member) {
        return dao.memberModify( member);
    }

    @Override
    public boolean memberDelete(String email) {
        return dao.memberDelete( email);
    }
}
