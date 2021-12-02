package com.cos.web01.domain.dto;

import com.cos.web01.domain.user.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserSaveRequestDto {

	private String userId;
	private String password;
	private String userName;

	public Users toEntity() {
		return Users.builder().userId(userId).password(password).userName(userName).build();
	}
}
