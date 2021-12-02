package com.cos.web01.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

//		AuthenticationFailureHandler 인터페이스를 구현하여 로그인 실패시에 처리할 핸들러 설정
//		
//		로그인에 실패할 시 실패한 내용을 화면에 출력 작업

		String errorMsg = exception.getMessage();
		request.setAttribute("errorMsg", errorMsg);
		
		System.out.println("로그인 에러");

		request.getRequestDispatcher("/login/fail").forward(request, response);

	}

}
