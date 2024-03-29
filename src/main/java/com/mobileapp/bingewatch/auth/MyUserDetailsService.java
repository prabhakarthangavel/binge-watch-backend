package com.mobileapp.bingewatch.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobileapp.bingewatch.entity.Users;
import com.mobileapp.bingewatch.repo.UsersRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersRepo usersRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = this.usersRepo.findusername(username);
		MyUserDetails userDetails = null;
		if (user != null) {
			userDetails = new MyUserDetails();
			userDetails.setUser(user);
		} else {
			throw new UsernameNotFoundException("User not exist with name : " + username);
		}
		return userDetails;
	}

}
