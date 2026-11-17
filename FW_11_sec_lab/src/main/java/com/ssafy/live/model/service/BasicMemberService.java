package com.ssafy.live.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.live.exception.RecordNotFoundException;
import com.ssafy.live.model.dao.AddressDao;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicMemberService implements MemberService {
    private final MemberDao mDao;
    private final AddressDao aDao;
    private PasswordEncoder pe;

    @Autowired(required = false) // 추후 추가되는 빈을 사용하기 위해서 setter 주입
    public void setPasswordEncoder(PasswordEncoder pe) {
        this.pe = pe;
    }

    @Override
    @Transactional
    public int registMember(Member member) {
        // TODO: 07-4.Member 등록 시 비번을 PasswordEncoder로 encoding 하세요.
    	member.setPassword(pe.encode(member.getPassword()));
        // END
        int result = mDao.insert(member);
        return result;
    }

    @Override
    @Deprecated
    public Member login(String email, String password) {
        // Member member = mDao.select(email);
        // if (member != null && member.getPassword().equals(password)) {
        // return member;
        // } else {
        // throw new RecordNotFoundException("id/pass 확인");
        // }
        throw new RuntimeException("Spring Security의 login process를 사용하세요.");
    }

    @Override
    public Member selectDetail(String email) {
        return mDao.selectDetail(email);
    }

    public List<Member> searchAll() {
        return mDao.searchAll();
    }

    @Override
    public Page<Member> search(SearchCondition condition) {
        // 전체 회원 수 조회
        int totalItems = mDao.getTotalCount(condition);
        // 현재 페이지의 회원 목록 조회
        List<Member> members = mDao.search(condition);
        // 페이지네이션 정보 생성
        Page<Member> page = new Page<>(condition, totalItems, members);
        return page;
    }

    @Override
    @Transactional
    public void delete(int mno) {
        aDao.deleteByMno(mno);
        // int i = 1/new Random().nextInt(2);
        mDao.delete(mno);
    }

    @Override
    @Transactional
    public void update(Member member) {
        mDao.update(member);
    }

    @Override
    @Transactional
    public void updateProfile(String email, byte[] profile) {
        mDao.updateProfile(email, profile);
    }

    @Override
    public Member loginBySocial(String provider, String oauthId) {
        Member member = mDao.selectByOauth(provider, oauthId);
        if (member != null) {
            return member;
        } else {
            throw new RecordNotFoundException("연동된 소셜 계정이 없습니다. 먼저 일반 로그인 후 연동을 진행해주세요.");
        }
    }

    @Override
    @Transactional
    public void linkSocialAccount(String email, String provider, String oauthId) {
        // 이미 해당 소셜 계정으로 다른 회원 레코드가 연동되어 있는지 중복 연동 여부 검사
        if (mDao.selectByOauth(provider, oauthId) != null) {
            throw new IllegalStateException("이미 다른 계정에 연동된 소셜 계정입니다.");
        }
        mDao.updateOauthInfo(email, provider, oauthId);
    }

    @Override
    @Transactional
    public void changePassword(String email, String newPassword) {
        // 비밀번호를 PasswordEncoder로 암호화하여 업데이트
        mDao.updatePassword(email, pe.encode(newPassword));
    }
}
