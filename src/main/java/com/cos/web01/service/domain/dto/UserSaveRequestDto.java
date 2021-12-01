package com.cos.web01.service.domain.dto;

import com.cos.web01.service.domain.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserSaveRequestDto {

	private String userId;
	private String password;
	private String userName;

	public User toEntity() {
		return User.builder().userId(userId).password(password).userName(userName).build();
	}
}
