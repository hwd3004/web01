package com.cos.web01.service;

import com.cos.web01.domain.boards.Boards;
import com.cos.web01.domain.boards.BoardsRepository;
import com.cos.web01.domain.user.UserRepository;
import com.cos.web01.domain.user.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {

	private BoardsRepository boardsRepository;
	private UserRepository userRepository;

	@Transactional
	public void savePost(Boards boards, String userId) {
		Users users = userRepository.findByUserId(userId).get();

		boards.setAuthor(users);

		boardsRepository.save(boards);
	}

	@Transactional(readOnly = true)
	public Page<Boards> postList(Pageable pageable) {
		return boardsRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Boards getPost(Long id) {
		Boards boards = boardsRepository.findById(id).get();

		return boards;
	}

}
