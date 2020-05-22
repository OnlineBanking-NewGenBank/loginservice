package com.ibm.loginservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.loginservice.model.Login;

@Repository
public interface LoginRepository extends MongoRepository<Login, String>{
		public Login findByusername(String username);
}
