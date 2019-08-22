package com.rest.foody.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
	@GetMapping("getstring")
	private ResponseEntity<String> TestMethod() {
		// TODO Auto-generated method stub
		
		return new ResponseEntity<String>("Hello This is Test Response", HttpStatus.OK);

	}

}
