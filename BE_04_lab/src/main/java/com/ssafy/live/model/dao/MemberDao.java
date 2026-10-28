package com.ssafy.live.model.dao;

import java.sql.Connection;
import java.util.List;

import com.ssafy.live.model.dto.Member;

/**
 * 회원 정보 관리를 위한 Data Access Object 인터페이스 회원 정보의 추가, 조회, 초기화 기능을 정의합니다.
 */
public interface MemberDao {

    /**
     * 새로운 회원을 추가합니다.
     *
     * @param con    데이터베이스 연결 객체
     * @param member 추가할 회원 정보
     * @return 추가된 회원 수 (성공 시 1, 실패 시 0)
     */
    int insert( Member member);

    public List<Member> search();

    /**
     * 회원 정보를 초기화합니다. 기존 데이터를 삭제하고 테스트용 데이터로 리셋합니다.
     */
    void reset();
}
