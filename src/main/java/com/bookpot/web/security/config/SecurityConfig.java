package com.bookpot.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bookpot.web.join.service.JoinService;
import com.bookpot.web.security.SecuritySuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JoinService userService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 리소스 인증 무시
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// 중복 로그인 방지
		http.sessionManagement()
			.maximumSessions(1)
			// true를 사용한다면, 정상적으로 logout하지않고 브라우저 종료시에, 아무도 로그인 못하는 상황 발생
			// 따라서 action 없는 사용자는 강제로 session을 kill 하는 등의 추가 개발이 필요
			.maxSessionsPreventsLogin(true)
			.expiredUrl("/duplicated-login")
			.sessionRegistry(sessionRegistry());
		
		//테스트 위한 임시로 csrf 설정 막기
		http.csrf().disable();
		
		
		// /login 은 누구나 접근 가능
		// /users/** 은 user의 role을 가질 경우만 접근 가능
		// /admin/** 은 admin의 role을 가질 경우만 접근 가능
		// 위의 두 role이 없으면 formLogin		
		http.authorizeRequests()
		// 테스트위한 임시로 모두 허용
			.antMatchers("/**","/").permitAll()
			.antMatchers("/user/**").access("hasRole('ROLE_USER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated()
			
		//	로그인 설정
			.and()
			.formLogin()
			.loginProcessingUrl("/user/login")
		//	로그인 성공페이지 구현
		//	.successHandler(authenticationSuccessHandler())
			
		//	로그아웃 설정	
			.and()
			.logout()
		//	.logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)
			
			.and()
			.exceptionHandling()
			.accessDeniedPage("/WEB-INF/views/error/403.jsp");
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
	// 비밀번호 암호화 (로그인 시 인코딩 된 비밀번호를 비교)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SecuritySuccessHandler();
	}
}
