package com.ssafy.live.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// TODO: 03-1. Spring Security를 위한 애너테이션을 설정하세요.
@EnableWebSecurity
@Configuration
// END
public class CustomSecurityConfig {
        // TODO: 03-2. 사용자의 ROLE 계층 구조를 확인하세요.
        @Bean
        RoleHierarchy roleHierachy() {
                return RoleHierarchyImpl.withDefaultRolePrefix() // role의 기본 prefix 설정: ROLE_
                                .role("ADMIN").implies("USER").role("USER").implies("GUEST")
                                .build();
        }

        // TODO: 04-1. 비밀번호 인코딩을 위한 PasswordEncoder를 확인하세요.
        @Bean
        PasswordEncoder passEncoder() {
                // 암호문 앞에 암호 형식을 붙임 - 기본 encoder로 bcrypt 방식
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
                // return new Argon2PasswordEncoder ();
        }

        // TODO: 05-1. InMemoryUserDetailsManager를 통해 admin과 user 사용자를 등록해보자.
        //  다른 UserDetailsService가 등록되어 있지 않을 때에만 동작하도록
        @Bean
        @ConditionalOnMissingBean(UserDetailsService.class)
        UserDetailsService inmemoryUserDetailsManager(PasswordEncoder pe) {
        	String pass = pe.encode("1234");
        	UserDetails admin = User.builder()
        			.username("admin@ssafy.com").password(pass).roles("ADMIN")
        			.build();
        	
        	UserDetails user = User.builder()
        			.username("user@ssafy.com").password(pass).roles("USER")
        			.build();
        	
        	return new InMemoryUserDetailsManager(admin, user);
        }
        // END

        // TODO: 06-1. secured 하위 요청에 대해 지정된 권한이 있는 경우만 접근할 수 있도록
        // SecurityFilterChain을 설정해보자.
        //  /secured/user 하위는 USER권한 필요, /secured/admin 하위는 ADMIN 권한 필요,
        //  /auth 하위는 로그인 필요, 나머지 경로는 그냥 통과
        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) {
        	http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/secured/user/**").hasRole("USER")
        	.requestMatchers("/secured/admin/**").hasRole("ADMIN")
        	.requestMatchers("/auth/**").authenticated()
        	.anyRequest().permitAll()
        	);
        	

        	// TODO: 07-5. TODO_06-1에서 csrf 체크를 무력화 후 회원가입해보세요.
        	http.csrf(csrf->csrf.disable());
        	
        	// TODO: 08-1. TODO_06-1에서 logout을 재정의하세요.
        	http.logout(cust -> cust.logoutUrl("/member/logout")
        			.logoutSuccessUrl("/").invalidateHttpSession(true));

        	// TODO: 09-3. TODO_06-1에서 formLogin을 재정의 하세요.
        	http.formLogin(login -> login.loginPage("/member/login-form")
        			.loginProcessingUrl("/member/login")
        			.usernameParameter("email")
        			.failureUrl("/member/login-form?error")
        			.defaultSuccessUrl("/")
        			.permitAll());

        	// TODO: 12-1. remember me를 활성화 시키세요.
        	http.rememberMe(cust -> cust.tokenValiditySeconds(60 * 1));
        	return http.build();
        }
        // END
}
