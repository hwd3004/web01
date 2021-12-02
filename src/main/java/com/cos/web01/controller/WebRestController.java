package com.cos.web01.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.web01.domain.dto.UserSaveRequestDto;
import com.cos.web01.domain.user.UserRepository;
import com.cos.web01.service.UserSecurityService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {

	@Autowired
	private UserSecurityService userSecurityService;

	@PostMapping("/users/signup")
	public ResponseEntity<Map<String, Object>> saveUsers(@RequestBody UserSaveRequestDto dto) {
		
		userSecurityService.accountUser(dto);
		
		Map<String, Object> map = new HashMap<>();

		map.put("msg", "save");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
