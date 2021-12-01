package com.cos.web01.service.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

// @MappedSuperclass
// 여러 엔티티에서 가져가야할 필요가 있을 때, 코드 중복을 줄이기 위해
// 공통으로 쓰이는 칼럼을 만들어서 추승 클래스에 정의하고,
// 이것을 사용할 엔티티에서는 이것을 상속받아 사용하면 됨

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 자동으로 시간 매핑해주는 기능을 포함시킴
public abstract class BaseTimeEntity {

	@CreatedDate // 엔티티가 생성되어 저장될때 시간이 자동 저장
	private LocalDateTime createdDate;

	@LastModifiedDate // 엔티티의 값이 변경될 때 시간이 자동 저장
	private LocalDateTime updatedDate;
}
