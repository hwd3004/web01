package com.cos.web01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.web01.service.domain.dto.UserSaveRequestDto;
import com.cos.web01.service.domain.user.UserRepository;

@RestController
public class WebRestController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping({ "", "/" })
	public String index() {
		return "hello";
	}

	@PostMapping("/user")
	public void saveUser(@RequestBody UserSaveRequestDto dto) {
		userRepository.save(dto.toEntity());
	}
}
