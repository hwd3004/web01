package com.cos.web01;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cos.web01.service.domain.user.User;
import com.cos.web01.service.domain.user.UserRepository;

@SpringBootTest
public class UserTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void test100_유저추가() {
		userRepository.save(User.builder().userId("테스트아이디").password("test").userName("테스트네임").build());

		List<User> userList = userRepository.findAll();

		System.out.println(userList.toString());

//		User user = userList.get(0);

		for (User user : userList) {
			System.out.println(user.toString());
		}

	}

}
