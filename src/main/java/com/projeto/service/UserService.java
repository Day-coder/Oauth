package com.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.entidades.User;
import com.projeto.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
@Autowired
UserRepository repository;

@Autowired
BCryptPasswordEncoder passwordEncoder;

public User salvar(User user){
	user.setSenha(passwordEncoder.encode(user.getSenha()));
	return repository.save(user);
}
 public List<User> consulta(){
	 return repository.findAll();
 }
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	User user= repository.findByEmail(email);
	if(user==null) {
throw new UsernameNotFoundException("Usuário não existe");
	}
	return user;
}

}
