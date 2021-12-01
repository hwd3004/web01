package com.cos.web01.service.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cos.web01.service.domain.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성 방지
@Data
@Entity
public class User extends BaseTimeEntity {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 값 생성이 데이터베이스 설정을 따라감, mysql을 쓰고 있으므로 auto_increment로 됨
	private Long id;

	@Column(length = 20, unique = true, nullable = false)
	private String userId;

	@Column(length = 50, unique = true, nullable = false)
	private String password;

	private String userName;

	@Builder
	public User(String userId, String password, String userName) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
	}
}
