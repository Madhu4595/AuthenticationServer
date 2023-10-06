package com.AuthenticationServer.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.AuthenticationServer.entity.Role;
import com.AuthenticationServer.entity.Users;
import com.AuthenticationServer.repo.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	@Autowired
	private UserRepository userRepository;

	private String SECRET_KEY = "secret";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		// TODO Auto-generated method stub
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		Users user=null;
		System.out.println("userDetails=>"+userDetails.getUsername());
		try {
		 user = userRepository.findByUsername(userDetails.getUsername());
		 
		claims.put("insCode", user.getIns_code());
		claims.put("username", userDetails.getUsername());
		}catch(Exception e) {
			System.out.println("Exception e"+e);
			e.printStackTrace();
		}
		Set<Role> roles = user.getRoles();
		roles.stream().forEach(a -> 
		claims.put("roleId",  a.getRole_id()));
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean validToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// Customized method
	public Claims getClaims(HttpServletRequest request) {
		final String authorizationHeader = request.getHeader("Authorization");
		Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(authorizationHeader).getBody();
		return claims;
	}

}
