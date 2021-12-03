package com.cos.web01.domain.boards;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Boards, Long> {

	// 페이지 처리를 위해 Pageable을 매개 변수로 전달
	Page<Boards> findAllBoards(Pageable pageable);
}
