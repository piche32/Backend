package com.ssafy.live.restcontroller;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.MemberService;

import lombok.RequiredArgsConstructor;

// TODO: 02-1. MemberController의 내용을 @RestController를 구성해보자.
//  대표 경로는 /api/v1/public/members으로 처리한다. REST API는 세션 관리를 하지 않는다.
@RestController
@RequestMapping("/api/v1/public/members")
@RequiredArgsConstructor
public class MemberRestController {
	private final MemberService mService;
	
	@PostMapping //POST /api/v1/public/members
	public ResponseEntity<?> registMember(@RequestBody Member member){
		mService.registMember(member);
		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("member", member));
	}
	
	@GetMapping("/check-email/{email}")
	private Map<String, ?> checkEmailDuplicate(@PathVariable String email) {
		try {
			Member selected = mService.selectDetail(email);
			return Map.of("canUse", selected == null);
		} catch(DataAccessException e) {
			e.printStackTrace();
			return Map.of("error", e.getMessage());
		}
	}
	
}

// END
