package com.cos.web01.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.web01.domain.boards.BoardsRepository;
import com.cos.web01.domain.user.UserRepository;
import com.cos.web01.domain.user.Users;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

	private UserRepository userRepository;

	private BoardsRepository boardsRepository;

	@GetMapping({ "", "/" })
	public String index() {
		return "contents/index";
	}

	@GetMapping("/board")
	public String hello() {
		return "contents/board";
	}

	@GetMapping("/login/error")
	public String error() {
		return "contents/error";
	}

	@PostMapping("/login/fail")
	public String initPost() {
		return "contents/index";
	}

	@GetMapping("/info")
	public String info(Principal principal, Model model) {
		Optional<Users> users = userRepository.findByUserId(principal.getName());
		Users user = users.get();

		model.addAttribute("userName", user.getUserName());

		return "contents/info";
	}

	// @PathVariable - url에서 {} 부분의 값을 받을 수 있음
	@GetMapping("/post/{id}")
	public String getBoardDetail(@PathVariable int id, ModelMap model) {
//		BoardFIndReponseDto boardDto = boardsService.findPost(id);
//		model.addAttribute("path", id);
//		model.addAttribute("post", boardDto);
		return "contents/postDetail";
	}

	@GetMapping("/posts")
	public String getPosts(@RequestParam("page") int page, ModelMap model) {
//		Page<BoardsFindAllResponseDto> boards = boardsService.findAllPost(page);
//		Pageable pageable = boards.getPageable();
//		model.addAttribute("page", PageUtils.getPages(pageable, boards.getTotalPages()));
//		model.addAttribute("posts", boards);
//		model.addAttribute("nowTime", LocalDateTime.now());
//		model.addAttribute("msg", "success");
		return "common/postList";
	}

}
