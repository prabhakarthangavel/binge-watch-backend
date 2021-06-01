package com.mobileapp.bingewatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapp.bingewatch.auth.JwtUtil;
import com.mobileapp.bingewatch.auth.MyUserDetailsService;
import com.mobileapp.bingewatch.exceptions.UserNameAlreadyExist;
import com.mobileapp.bingewatch.modals.Authentication;
import com.mobileapp.bingewatch.modals.Register;
import com.mobileapp.bingewatch.modals.Response;
import com.mobileapp.bingewatch.service.AuthenticationService;

@RestController
@RequestMapping("/public")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationService authService;

	@PostMapping("/authenticate")
	public ResponseEntity<Response> auth(@RequestBody Authentication request) throws Exception {
		String jwt = null;
		try {
			System.out.println("try");
			this.authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			final UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
			jwt = this.jwtUtil.generateToken(userDetails);

		} catch (BadCredentialsException e) {
			return ResponseEntity.ok(new Response(jwt));
		}
		return ResponseEntity.ok(new Response(jwt));
	}

	@PostMapping("/register")
	public ResponseEntity<Response> createUser(@RequestBody Register request) throws Exception {
		try {
			this.authService.createNewUser(request);
			return ResponseEntity.ok().body(new Response("User Created"));
		} catch (UserNameAlreadyExist ex) {
			return ResponseEntity.badRequest().body(new Response(ex.getMessage()));
		}

	}
}
