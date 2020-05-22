package com.ibm.loginservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.loginservice.model.Login;
import com.ibm.loginservice.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/createUser")
	public ResponseEntity<Login> createUser(@RequestBody Login login) {
		loginService.createUser(login);
		return new ResponseEntity<Login>(login,HttpStatus.CREATED);
	}
	
	@GetMapping("/validate")
	public ResponseEntity<Login> validateUser(@RequestBody Login login) {
		Login user=new Login();
		user = loginService.validateUser(login);
		if(null!=user) {
			return new ResponseEntity<Login>(user,HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
		}
		
	}

}
