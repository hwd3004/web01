package com.cos.web01.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.web01.domain.boards.Boards;
import com.cos.web01.dto.user.UserSaveRequestDto;
import com.cos.web01.service.BoardService;
import com.cos.web01.service.UserSecurityService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {

	@Autowired
	private UserSecurityService userSecurityService;

	@Autowired
	private BoardService boardService;

	@PostMapping("/users/signup")
	public ResponseEntity<Map<String, Object>> saveUsers(@RequestBody UserSaveRequestDto dto) {

		userSecurityService.accountUser(dto);

		Map<String, Object> map = new HashMap<>();

		map.put("msg", "save");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/user/userName")
	public ResponseEntity<Map<String, Object>> getUserName(Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();

		// UserSecurityService에서 laodUserByUsername 메소드에
		// 리턴 값으로 전달한 User 객체에서 설정한 Id 값을 가져옴
		map.put("userName", principal.getName());

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@PostMapping("/user/changeName")
	public ResponseEntity<Map<String, Object>> changeUserName(@RequestBody Map<String, Object> map,
			Principal principal) {

		String changeName = map.get("changeName").toString();
		String userId = principal.getName();

		userSecurityService.changeUserName(userId, changeName);

		Map<String, Object> responseMap = new HashMap<String, Object>();

		responseMap.put("msg", "success");

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);

	}

	@DeleteMapping("/user")
	public ResponseEntity<Map<String, Object>> deleteUser(Principal principal) {
		String userId = principal.getName();

		userSecurityService.deleteUser(userId);

		Map<String, Object> responseMap = new HashMap<String, Object>();

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}

	@PostMapping("/post/save")
	public ResponseEntity<Map<String, Object>> save(@RequestBody Boards boards, Principal principal) {

		String userId = principal.getName();

		System.out.println(boards);

		boardService.savePost(boards, userId);

		Map<String, Object> responseMap = new HashMap<>();

		responseMap.put("msg", "save");

		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@DeleteMapping("/post/{id}")
	public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Long id, Principal principal) {
		String userId = principal.getName();

		boardService.deletePost(id, userId);

		Map<String, Object> respoMap = new HashMap<>();

		respoMap.put("msg", "success");

		return new ResponseEntity<>(respoMap, HttpStatus.OK);
	}

}
