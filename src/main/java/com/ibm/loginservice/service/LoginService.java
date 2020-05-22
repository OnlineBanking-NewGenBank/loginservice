package com.ibm.loginservice.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.loginservice.model.Login;
import com.ibm.loginservice.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	LoginRepository loginRepo;

	public void createUser(Login login) {
		login.setPassword(hashPassword(login.getPassword()));
		loginRepo.save(login);
	}

	public Login validateUser(Login login) {
		Login user = new Login();
		user = loginRepo.findByusername(login.getUsername());
		if (checkPass(login.getPassword(), user.getPassword()))
			return user;
		else
			return null;
	}

	private String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public Boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else
			return false;
	}
}
