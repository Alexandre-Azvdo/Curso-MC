package com.alexandreazevedo.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alexandreazevedo.cursomc.domain.Cliente;
import com.alexandreazevedo.cursomc.repositories.ClienteRepository;
import com.alexandreazevedo.cursomc.security.UserSS;

@Service
public class UserDetailsServicempl implements UserDetailsService{

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = repo.findByEmail(email);
		if (cliente == null) {
			throw new UsernameNotFoundException(email);			
		}
		
		return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
