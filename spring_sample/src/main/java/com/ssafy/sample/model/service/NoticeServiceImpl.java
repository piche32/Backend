package com.ssafy.sample.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.sample.exception.CustomBusinessException;
import com.ssafy.sample.model.dao.NoticeDao;
import com.ssafy.sample.model.dto.Notice;

import lombok.RequiredArgsConstructor;

// TODO 3: 스프링 서비스 빈 등록, 의존성 주입 및 트랜잭션/유효성 검증 로직을 구현하시오.
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeDao noticeDao;
	
    @Override
    @Transactional(readOnly = true) //런타임 익셉션, 에러
    public List<Notice> getAllNotices() {
        return noticeDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class) //런타임 익셉션, 에려 =? 체크드 익셉션까지 모두 받아줌 : 예외 시 롤백, 정상 시 커밋
    public Notice createNotice(Notice notice) {
    	if(notice.getTitle()== null || notice.getTitle().trim().isEmpty()) { //유효성 검증
    		//비즈니스 예외 발생시키기
    		throw new CustomBusinessException("공지사항 제목은 필수 입력 항목입니다.");
    	}
    	System.out.println("dao 전: " + notice);
    	noticeDao.insert(notice);
    	System.out.println("dao 후: " + notice);
        return notice;
    }
}