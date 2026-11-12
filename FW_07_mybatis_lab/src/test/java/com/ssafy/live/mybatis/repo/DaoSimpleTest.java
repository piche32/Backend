package com.ssafy.live.mybatis.repo;

import java.lang.reflect.Proxy;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.live.model.dao.AddressDao;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
// @MybatisTest
// @AutoConfigureTestDatabase(replace = Replace.NONE) // 기본은 H2 등 메모리 기반 DB

@Slf4j
public class DaoSimpleTest {

    @Autowired
    MemberDao mDao;

    @Autowired
    AddressDao aDao;

    @Autowired
    ApplicationContext ctx;

    @Test
    public void whySliceTest() {
        int cnt = ctx.getBeanDefinitionCount();
        log.debug("bean cnt: {}", cnt);
    }

    @Test
    public void insertMemberTest() {
        // TODO: 03-4. MemberDao의 insert를 테스트해보자.
    	Member member = Member.builder().name("hong2").email("hong2@ssafy.com").password("1234").build();
    	int result = mDao.insert(member);
    	
    	Assertions.assertEquals(1, result);
    	Assertions.assertNotEquals(0, member.getMno());
        // END
    }

    // TODO: 04-2. update, delete, updateProfile의 테스트 결과를 확인하세요.
    @Test
    public void updateMemberTest() {
        Member member = Member.builder().email("001@ssafy.com").name("modified").password("4321").role("ADMIN").build();
        int result = mDao.update(member);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void updateProfileTest() {
        byte[] profile = "Hello".getBytes();
        int result = mDao.updateProfile("admin@ssafy.com", profile);
        Assertions.assertEquals(1, result);
        result = mDao.updateProfile("notExist@ssafy.com", profile);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void deleteMemberTest() {
        int result = mDao.delete(99);
        Assertions.assertEquals(1, result);
        result = mDao.delete(-1);
        Assertions.assertEquals(0, result);
    }

    // TODO: 05-2. Address에 대한 insert, delete, deleteByMno를 테스트 해보세요.
    
    // END

    // TODO: 06-2. select의 테스트 결과를 확인하세요.
    @Test
    public void selectMemberTest() {
        String email = "admin@ssafy.com";
        Member selected = mDao.select(email);
        Assertions.assertEquals("관리자", selected.getName());
        selected = mDao.select("notExist");
        Assertions.assertNull(selected);
    }

    @Test
    // TODO: 06-4. Address에 대한 select를 작성 후 테스트 해보세요.
    public void selectAddressTest() {
        int ano = 1;
        Address address = aDao.select(ano);
        Assertions.assertEquals("기본", address.getTitle());
        Assertions.assertEquals("멀티캠퍼스", address.getDetailAddress());
    }

    // TODO: 07-2. search의 테스트 결과를 확인하세요.
    @Test
    public void searchAllMemberTest() {
        List<Member> members = mDao.searchAll();
        // System.out.println(members.get(0));
        Assertions.assertEquals("admin@ssafy.com", members.get(0).getEmail());
    }

    // TODO: 08-2. Address에 대한 selectDetail를 작성 후 테스트 해보세요.
    @Test
    public void selectAddressDetailTest() {
        int ano = 1;
        Address address = aDao.selectDetail(ano);
        Assertions.assertEquals("멀티캠퍼스", address.getDetailAddress());
        Assertions.assertEquals("admin@ssafy.com", address.getMember().getEmail());
    }

    // TODO: 09-2. searchTest의 테스트 결과를 확인하세요.
    @Test
    public void selectMemberDetailTest() {
        Member member = mDao.selectDetail("admin@ssafy.com");
        Assertions.assertEquals(2, member.getAddresses().size());

        member = mDao.selectDetail("099@ssafy.com");
        Assertions.assertEquals(0, member.getAddresses().size());
    }
}
