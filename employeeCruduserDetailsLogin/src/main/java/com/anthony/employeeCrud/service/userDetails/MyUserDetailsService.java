package com.anthony.employeeCrud.service.userDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anthony.employeeCrud.dao.UserRepository;
import com.anthony.employeeCrud.entity.Role;
import com.anthony.employeeCrud.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Collection<Role> userRoles) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : userRoles)
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		List<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>(authorities);
		return userAuthorities;
	}
	
	/*
	 * private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	 * Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>(); for (Role role
	 * : userRoles) { roles.add(new SimpleGrantedAuthority(role.getRole())); }
	 * List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles); return
	 * grantedAuthorities; }
	 */

}