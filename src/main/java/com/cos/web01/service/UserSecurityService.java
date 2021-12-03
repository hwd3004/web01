package com.cos.web01.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.web01.domain.dto.UserSaveRequestDto;
import com.cos.web01.domain.user.UserRepository;
import com.cos.web01.domain.user.Users;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Transactional // DB에 접근하는 여러 연산을 하나의 트랜잭션으로 처리하여 오류가 발생한 경우 롤백을 도와줌
	public Long accountUser(UserSaveRequestDto saveDto) {
//		회원가입에 대한 메소드

//		클라이언트에서 전달한 패스워드를 해쉬 암호화 작업
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		saveDto.setPassword(passwordEncoder.encode(saveDto.getPassword()));

		return userRepository.save(saveDto.toEntity()).getId();
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// 로그인 요청이 들어왔을 때 유저 정보의 조회하는 메소드
		// 유저의 ROLE 부여
		// 매개 변수로 userId를 받지만, form의 input 태그에서 username으로 서버에 전달해주어야함

		Optional<Users> userEntity = userRepository.findByUserId(userId);
		Users findUser = userEntity.get();

		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

//		import org.springframework.security.core.userdetails.User;
		return new User(findUser.getUserId(), findUser.getPassword(), authorities);
	}

	@Transactional
	public void changeUserName(String userId, String changeName) {
		Optional<Users> users = userRepository.findByUserId(userId);

		Users user = users.get();

		user.changeUserName(changeName);
	}

}
