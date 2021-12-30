package com.cos.web01.service;

import java.util.Optional;

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

	public void deletePost(Long postId, String userId) {
		Users users = userRepository.findByUserId(userId).get();

		Boards boards = boardsRepository.findById(postId).get();

		// System.out.println("데이터 : " + users.getUserId());
		// System.out.println("데이터 : " + boards.getAuthor().getUserId());

		if (users.getUserId().equals(boards.getAuthor().getUserId())) {
			boardsRepository.deleteById(postId);
		}

	}

	// @Transactional
	// public void deletePost(Long postId, String userId) {
	// Users user = usersRepository.findByUserId(userId).get();
	// Boards board = boardRepository.findById(postId).get();

	// if (user.getUserId().equals(board.getAuthor().getUserId()) ) {
	// board.deletePost();
	// }
	// }

}
