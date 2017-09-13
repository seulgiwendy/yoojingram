package com.gallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/user")
	public String returnHomepage() {
		return "index";
	}

}
