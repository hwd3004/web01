package com.cos.web01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cos.web01.service.UserSecurityService;

import lombok.AllArgsConstructor;

@Configuration // 이 클래스가 설정 파일임을 스프링에 알려줌
@EnableWebSecurity // 스프링 시큐리티 필터 체인이 자동으로 포함됨
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	WebSecurityConfigurerAdapter 클래스를 상속 받아 시큐리티 설정

	@Autowired
	private UserSecurityService userSecurityService;

	@Bean
	public PasswordEncoder passwordEncoder() {
//		스프링 시큐리티에서 제공하는 암호화 객체
//		Bean으로 등록하여 Service에서 사용할 수 있도록 함

		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
//		WebSecurityConfigurerAdapter 클래스의 configure 메소드들을 오버라이딩하여 시큐리티를 설정

//		static 폴더에 있는 파일들은 인증 과정 무시하기
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http.authorizeRequests().antMatchers("/board").hasRole("USER").antMatchers("/**").permitAll()
//		/board에 대한 요청 처리 설정, 요청을 보내는 대상의 ROLE을 확인하여 USER이면 접근 허락
//		
//		.formLogin().loginPage("/").loginProcessingUrl("/user/login").defaultSuccessUrl("/board", true)
//		.failureHandler(loginFailHandler()).permitAll().and() //
//		formLogin() - form 기반으로 로그인 인증을 하도록 설정
//		loginPage("/") - /login 경로로 접속하면 스프링 시큐리티에서 제공하는 자체 form을 사용하는데, 직접 만든 form을 사용하는 설정
//		loginProcessingUrl("/user/login") - 매개 변수로 전달한 주소에 대해 요청을 보내면 로그인 진행, 직접 만든 form 태그의 action 주소를 매개 변수로 전달한 주소와 맞추어야함
//		defaultSuccessUrl("/board", true) - 로그인이 정상적으로 이루어졌을 때, 어떤 페이지로 이동할 것인지 설정
//		failureHandler(loginFailHandler()) - 로그인에 실패했을 때 어떻게 처리할 것인지 설정, 따로 작성한 loginFailHandler 메소드를 전달
//
//		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/")
//		.invalidateHttpSession(true).and() //
//		logout() - 로그아웃 설정 시작
//		logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) - 로그아웃 설정, 전달한 경로에 대한 요청이 생기면 로그아웃 진행
//		logoutSuccessUrl("/") - 로그아웃 성공시 이동할 페이지 주소 설정
//		invalidateHttpSession(true) - 로그아웃 성공 시 Http 세션 초기화
//		
//		exceptionHandling().accessDeniedPage("/login/error")
//		페이지에 대한 접근 권한이 없을 때 처리 메소드, /login/error 페이지로 이동하게 함

		http.authorizeRequests().antMatchers("/board").hasRole("USER").antMatchers("/**").permitAll().and() //
				.formLogin().loginPage("/").loginProcessingUrl("/user/login").defaultSuccessUrl("/board", true)
				.failureHandler(loginFailHandler()).permitAll().and() //
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/")
				.invalidateHttpSession(true).and() //
				.exceptionHandling().accessDeniedPage("/login/error");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		시큐리티에 대한 로그인 처리 설정

		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationFailureHandler loginFailHandler() {
//		로그인 오류 처리 설정

		return new LoginFailHandler();
	}

}
