package com.anthony.employeeCrud.service.userDetails;

import java.util.ArrayList;
import java.util.Collection;
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
		System.out.println("IM HERE");
		if(user == null)
			System.out.println("IS NULL");
		else {
			System.out.println("ISNT NULL");
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getFirstName());
			System.out.println(user.getLastName());
		}
		if (user == null)
			throw new UsernameNotFoundException("Invalid username or password");
		System.out.println("--------------------GETTING ROLES-----------------------");
		//Error is toruble with getting roles
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		System.out.println("--------------------BUILDING USER-----------------------");
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Collection<Role> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : userRoles)
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		return authorities;
	}
	
	/*
	 * private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	 * Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>(); for (Role role
	 * : userRoles) { roles.add(new SimpleGrantedAuthority(role.getRole())); }
	 * List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles); return
	 * grantedAuthorities; }
	 */

}