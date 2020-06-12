package com.profdinho.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.profdinho.api.model.MyUserPrincipal;
import com.profdinho.api.model.User;
import com.profdinho.api.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = userRepo.findByUsername(username);
		User user = null;
		if(opt.isPresent()){
		    user = opt.get();
		}
		if (user == null) {
		    throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(user);
	
		/*
		if ("username".equals(username)) {
			return new User("username", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		*/
	}
	
	public User save(User usuario) {
		usuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
		return userRepo.save(usuario);
	}

}
