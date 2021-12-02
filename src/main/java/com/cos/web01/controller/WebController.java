package com.cos.web01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping({ "", "/" })
	public String index() {
		return "contents/index";
	}

	@GetMapping("/hello")
	public String hello() {
		return "contents/hello";
	}

}
