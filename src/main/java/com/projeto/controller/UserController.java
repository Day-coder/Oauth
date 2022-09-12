package com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.entidades.User;
import com.projeto.repository.UserRepository;
import com.projeto.service.UserService;

@RestController
@RequestMapping("")
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repository;
	@PostMapping("/user")
	public ResponseEntity<Object> salvar(@RequestBody User user){
		User u= repository.findByEmail(user.getEmail());
		if(u!= null) {
			return ResponseEntity.status(HttpStatus.OK).body("Usuário cadastrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(service.salvar(user));
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> consultar(){
		return ResponseEntity.status(HttpStatus.OK).body(service.consulta());
	}
}
