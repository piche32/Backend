package com.ssafy.live.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.live.model.dao.MemberDao;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TxStartService {
    @Autowired
    MemberDao mDao;

    @Autowired
    BasicSimpleService sService;

    @Transactional
    public void start(Propagation prop, int mod, int mno) {
        // 삭제 처리 - 과연 rollback 될까?
        mDao.delete(mno);
        try {
            switch (prop) {
                case REQUIRED -> sService.required(mod);
                case REQUIRES_NEW -> sService.requiresNew(mod);
                case NESTED -> sService.nested(mod);
            }
        } catch (RuntimeException e) {
            log.debug("exception: {}", e.getMessage());
            // 예외 처리를 해버린 경우 - T.X 상태 불일치 - UnexpectedRollbackException 발생 - rollback
            // throw e; // 예외를 던지는 경우 T.X 상태 일치 - e에 의한 rollback
        }
    }
}
