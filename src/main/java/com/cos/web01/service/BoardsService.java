package com.cos.web01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.web01.domain.boards.BoardsRepository;
import com.cos.web01.domain.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardsService {

	@Autowired
	private BoardsRepository boardsRepository;

	@Autowired
	private UserRepository userRepository;

}
