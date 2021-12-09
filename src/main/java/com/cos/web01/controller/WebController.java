package com.cos.web01.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.web01.domain.boards.Boards;
import com.cos.web01.domain.user.UserRepository;
import com.cos.web01.domain.user.Users;
import com.cos.web01.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {

	private UserRepository userRepository;

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" })
	public String index() {
		return "contents/index";
	}

	@GetMapping("/board")
	public String board(ModelMap model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

		// System.out.println("데이터 - pageable " + pageable);
		// System.out.println("데이터 - 받아온 페이지 넘버 : " + pageable.getPageNumber());

		// System.out.println("데이터 - pageable.withPage : " +
		// pageable.withPage(pageable.getPageNumber() - 1));

		// Page<Boards> boards = boardService.postList(pageable);

		// dto로 넘겨야하는 작업 필요
		Page<Boards> boards = boardService.postList(pageable.withPage(pageable.getPageNumber() - 1));

		int totalPage = boards.getTotalPages();
		int startPage = totalPage - 10;

		if (startPage < 1) {
			startPage = 1;
		}

		Map<String, Object> page = new HashMap<String, Object>();

		page.put("pageNumber", pageable.getPageNumber());
		page.put("startPage", startPage);
		page.put("totalPage", totalPage);

		model.addAttribute("post", boards);
		model.addAttribute("page", page);

		return "contents/board";
	}

	@GetMapping("/board/{id}")
	public String boardById(@PathVariable Long id, Model model) {
		// System.out.println("param id : " + id);

		// dto로 넘겨야하는 작업 필요
		Boards post = boardService.getPost(id);

		model.addAttribute("post", post);

		return "contents/postDetail";
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

}
