package com.ssafy.live.mybatis.repo;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;

import com.ssafy.live.model.dao.AddressDao;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.SearchCondition;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // 기본은 H2 등 메모리 기반 DB

public class DynamicQueryTest {

    @Autowired
    MemberDao mDao;

    @Autowired
    AddressDao aDao;

    // TODO: 10-2. queryStructureChange를 작성 후 테스트 해보세요.
    @Test
    public void queryStructureChangeTest() {
        List<Address> address = aDao.queryStructureChange(Map.of("key", "title", "word", "기본"));
        Assertions.assertEquals(1, address.size());
        address = aDao.queryStructureChange(Map.of("key", "mno", "word", "100"));
        Assertions.assertEquals(0, address.size());
        address = aDao.queryStructureChange(Map.of("key", "1=1 or mno", "word", "100"));
        Assertions.assertEquals(2, address.size());
    }

    // TODO: 11-2. dynamicSelect를 작성 후 테스트 해보세요.
    @Test
    public void dynamicSelectTest() {
        List<Address> address = aDao.dynamicSelect(Map.of("title", "기본"));
        Assertions.assertEquals(1, address.size());

        address = aDao.dynamicSelect(Map.of("mno", "100"));
        Assertions.assertEquals(0, address.size());
        // SQL 삽입 공격 방어
        address = aDao.dynamicSelect(Map.of("title", "기본 or 1==1"));
        Assertions.assertEquals(0, address.size());

        // 하지만 또다른 문제
        // address = aDao.dynamicSelect(Map.of("title", "기본", "mno", "100"));
        // Assertions.assertEquals(0, address.size());
    }

    // TODO: 12-2. selectUseIn를 작성 후 테스트 해보세요.
    @Test
    public void selectUseInTest() {
        List<Address> address = aDao.selectUseIn(List.of("기본", "집"));
        Assertions.assertEquals(2, address.size());
    }

    // TODO: 13-2. dynamicUpdate를 작성 후 테스트 해보세요.
    @Test
    public void dynamicUpdateTest() {
        Address addr = aDao.select(1);
        addr.setTitle("수정됨");
        addr.setDetailAddress(null);
        Assertions.assertEquals(1, aDao.dynamicUpdate(addr));

        Address selected = aDao.select(1);
        Assertions.assertEquals("수정됨", selected.getTitle());
        Assertions.assertEquals("멀티캠퍼스", selected.getDetailAddress());
    }

    // TODO: 15-2. getTotalCount의 테스트 결과를 확인하세요.
    @Test
    public void getTotalCountTest() {
        int cnt = mDao.getTotalCount(new SearchCondition());
        Assertions.assertEquals(101, cnt);

        cnt = mDao.getTotalCount(new SearchCondition("email", "01", 1));
        Assertions.assertEquals(11, cnt);

        cnt = mDao.getTotalCount(new SearchCondition("name", "01", 0));
        Assertions.assertEquals(11, cnt);
    }

    // TODO: 15-4. search의 테스트 결과를 확인하세요.
    @Test
    public void searchTest() {
        List<Member> members = mDao.search(new SearchCondition());
        Assertions.assertEquals(5, members.size());

        members = mDao.search(new SearchCondition("email", "01", 1));
        Assertions.assertEquals(5, members.size());
        Assertions.assertEquals("019@ssafy.com", members.get(0).getEmail());

        members = mDao.search(new SearchCondition("name", "01", 2));
        Assertions.assertEquals(5, members.size());
        Assertions.assertEquals("086@ssafy.com", members.get(0).getEmail());
    }

    @Test
    public void carefulQueryTest() {
        List<Address> list = aDao.carefulQuery("서울", 100);
        Assertions.assertEquals(1, list.size());
    }

}
