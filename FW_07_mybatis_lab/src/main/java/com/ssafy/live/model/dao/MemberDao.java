package com.ssafy.live.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.SearchCondition;

// TODO: 03-1.DAO의 기본적인 형태를 살펴보자.
// 파라미터로 Connection을 받지 않는다.
// 예외는 RuntimeException 계열(DataAccessException)으로 포장되어 던져진다.

@Mapper
public interface MemberDao {
    int insert(Member member);

    // 회원 기본 정보 수정 (이름, 역할 등)
    public int update(Member member);

    // 비밀번호 변경은 독자적인 보안 검증 및 해싱 유스케이스를 가지므로 분리
    int updatePassword(@Param("email") String email, @Param("password") String password);

    // 대용량 BLOB 데이터(프로필 이미지) 수정을 분리하여 리소스 낭비 방지
    public int updateProfile(@Param("email") String email, @Param("profile") byte[] profile);

    // 소셜 연동은 일반 정보 수정과 시점 및 권한이 다른 별개의 비즈니스 흐름
    int updateOauthInfo(@Param("email") String email, @Param("oauthProvider") String oauthProvider, @Param("oauthId") String oauthId);

    // 로그인/로그아웃 등 인증 흐름에서의 단독 갱신 및 유실(Null) 방지를 위해 분리
    int updateRefresh(@Param("email") String email, @Param("refresh") String refresh);

    public int delete(@Param("mno") int mno);

    Member select(@Param("email") String email);

    List<Member> searchAll();

    public Member selectDetail(@Param("email") String email);

    public int getTotalCount(SearchCondition condition);

    public List<Member> search(SearchCondition condition);

    Member selectByOauth(@Param("oauthProvider") String oauthProvider, @Param("oauthId") String oauthId);

}
