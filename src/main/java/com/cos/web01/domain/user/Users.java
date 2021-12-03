package com.cos.web01.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.cos.web01.domain.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성 방지
@Data
@Entity
@DynamicUpdate // JPA 어노테이션, 실제 값이 변경된 칼럼으로만 업데이트 쿼리를 만듬
public class Users extends BaseTimeEntity {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 값 생성이 데이터베이스 설정을 따라감, mysql을 쓰고 있으므로 auto_increment로 됨
	private Long id;

	@Column(length = 20, unique = true, nullable = false)
	private String userId;

	@Column(length = 255, unique = true, nullable = false)
	private String password;

	private String userName;

	@Builder
	public Users(String userId, String password, String userName) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
	}

	public void changeUserName(String userName) {
		// 엔티티의 값을 수정할 수 있는 메소드

		this.userName = userName;
	}
}
