package com.ssafy.live.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.SearchCondition;

@Mapper
public interface MemberDao {
    int insert(Member member);

    public int update(Member member);

    int updatePassword(@Param("email") String email, @Param("password") String password);

    public int updateProfile(@Param("email") String email, @Param("profile") byte[] profile);

    int updateOauthInfo(@Param("email") String email, @Param("oauthProvider") String oauthProvider, @Param("oauthId") String oauthId);

    int updateRefresh(@Param("email") String email, @Param("refresh") String refresh);

    public int delete(@Param("mno") int mno);

    Member select(@Param("email") String email);

    List<Member> searchAll();

    public Member selectDetail(@Param("email") String email);

    public int getTotalCount(SearchCondition condition);

    public List<Member> search(SearchCondition condition);

    Member selectByOauth(@Param("oauthProvider") String oauthProvider, @Param("oauthId") String oauthId);

}
