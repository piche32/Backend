package com.ssafy.live.mybatis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ConfigTest {

    @Autowired
    DataSource ds;

    @Autowired
    SqlSessionTemplate template;

    @Autowired
    TransactionManager tm;

    @Test
    public void dataSourceTest() throws Exception {
        // TODO: 02-2. 데이터소스 관련 설정이 잘 되어있는지 확인하세요.
        assertNotNull(ds);
        log.debug("datasource: {}", ds.getClass().getName());
        if (ds instanceof HikariDataSource hds) {
            Assertions.assertEquals(5, hds.getMaximumPoolSize());
            Assertions.assertEquals(3, hds.getMinimumIdle());
            Assertions.assertEquals(1000 * 60 * 5, hds.getIdleTimeout());
            Assertions.assertEquals(1000 * 60 * 10, hds.getConnectionTimeout());
        }
        assertNotNull(template);
    }

    @Test
    public void infraBeanTest() {
        // TODO: 02-4. SqlSessionTemplate과 TransactionManager를 확인하세요.
        Assertions.assertNotNull(template);
        Assertions.assertNotNull(tm);
        log.debug("SqlSessionTemplate: {}", template.getClass().getName());
        log.debug("TransactionManager: {}", tm.getClass().getName());
    }

}
