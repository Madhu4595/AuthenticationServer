package com.AuthenticationServer.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AuthenticationServer.jwt.AUthenticationResponse;
import com.AuthenticationServer.jwt.AuthenticationRequest;
import com.AuthenticationServer.jwt.JwtUtil;
import com.AuthenticationServer.model.ResponseObject;

import io.jsonwebtoken.Claims;


@RestController
public class AuthenticationController {
	
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private UserDetailsService userDetailsService;
	@Autowired private JwtUtil jwtUtil;

	@GetMapping("/hello")
	public ResponseEntity<?> hello() {
		System.out.println("hellohellohellohellohellohellohellohello");
		return ResponseEntity.ok("hello from controller");
	}
	 
 

//	@PostMapping(value = "addRole")
//	public Role addRole(@RequestBody Role role) {
//		return roleRepository.save(role);
//
//	}
//
//	@PostMapping(value = "addUser")
//	public Users addRole(@RequestBody Users user) {
	//	return userService.addUser(user);
//	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException{
	    System.out.println("authenticate"+authenticationRequest.toString());
	    try {
	    	System.out.println("dddddddddddddddddddddddddddd");
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(),authenticationRequest.getPassword()));
			System.out.println("authentication=>"+authentication.toString());
			System.out.println("authentication=>"+authentication.isAuthenticated());
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			return ResponseEntity.ok("Bad Credentials");
			
		}
	    
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AUthenticationResponse(jwt));

	}
	
	
	@PostMapping("/validateToken")
	public ResponseEntity<?> createAuthentivationToken(HttpServletRequest request) {
		System.out.println("validateToken");
		
		Claims claims = jwtUtil.getClaims(request);
		
		String username = String.valueOf(claims.get("username"));
		final String authorizationHeader = request.getHeader("Authorization");
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		Boolean valid = jwtUtil.validToken(authorizationHeader, userDetails);
		if(valid) {
			String insCode = String.valueOf(claims.get("insCode"));
			String roleId = String.valueOf(claims.get("roleId"));
			ResponseObject responseObject = new ResponseObject();
			responseObject.setValidToken(valid);
			responseObject.setInsCode(insCode);
			responseObject.setRoleId(roleId);
			
			return new ResponseEntity<>(responseObject,HttpStatus.OK);
		} 
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
	}
	
	
}
