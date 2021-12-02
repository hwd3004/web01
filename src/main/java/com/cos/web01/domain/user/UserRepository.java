package com.cos.web01.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

//	userId로 검색할 수 있는 메소드
//	Optional을 사용하여 null을 사용하지 않고 Optional 인스턴스로 대체하여 값이 없음에 대한 에러를 안전하게 처리
	Optional<Users> findByUserId(String userId);
}
