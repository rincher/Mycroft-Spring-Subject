package is.recruit.mycroft.spring.subjects.config;

import io.jsonwebtoken.*;
//import is.recruit.mycroft.spring.subjects.model.user.role.Role;
import is.recruit.mycroft.spring.subjects.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
	private final UserDetailsService userDetailsService;

	@Value("${mycroft.subjects.jwt.secret}")
	private String secretKey;

	private long tokenValidMillisecond = 7 * (1000 * 60 * 60 * 24);

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createdToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", roles);
		Date now = new Date();
		Date expire = new Date(now.getTime() + tokenValidMillisecond);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expire)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public String resolveToken(HttpServletRequest request){
		return request.getHeader("Authorization");
	}

	public boolean validateToken(String jwtToken){
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
			return !claimsJws.getBody().getExpiration().before(new Date());
		} catch ( Exception e) { return false; }
	}

	public String getUserPk(String token){
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public Authentication getAuthentication(String token){
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
}
