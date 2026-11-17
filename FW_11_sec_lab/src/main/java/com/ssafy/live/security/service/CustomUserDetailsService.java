package com.ssafy.live.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.security.dto.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// TODO: 07-3. UserDetailsService를 확장해서 CustomUserDetails를 반환하도록 하자.
 public class CustomUserDetailsService implements UserDetailsService {
	
	private final MemberDao mDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Member member = mDao.select(email);
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new CustomUserDetails(member);
	}
}

// END
