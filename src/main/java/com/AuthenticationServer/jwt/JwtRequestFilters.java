package com.AuthenticationServer.jwt;

//@Component
public class JwtRequestFilters   {
//	public class JwtRequestFilters extends OncePerRequestFilter {
//	@Autowired
//	private UserService userDetailsService;
//	@Autowired
//	private JwtUtil jwtUtil;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		final String authorizationHeader = request.getHeader("Authorization");
//		System.out.println("Authorization=>"+authorizationHeader);
//		
//		String username = null;
//		String jwt = null;
//		
//		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//			jwt = authorizationHeader.substring(7);
//			username = jwtUtil.extractUsername(jwt);
//			System.out.println("username=> "+username);
//		}
//		System.out.println("sjfljasf=> "+SecurityContextHolder.getContext().getAuthentication());
//		
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			System.out.println("username != null");
//			
//			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//			
//			if (jwtUtil.validToken(jwt, userDetails)) {
//				System.out.println("valid Token=> authority=> "+userDetails.getAuthorities());
//				
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//			
//		}
//		
//		filterChain.doFilter(request, response);
//	}

}
