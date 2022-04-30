package com.stefanini.taskmanager.security;

import com.stefanini.taskmanager.response.AuthResponse;
import com.stefanini.taskmanager.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    Logger logger = LogManager.getLogger(JwtProvider.class);

    @Value("$(jwt.secret)")
    private String jwtSecret;

    private final UserService userService;

    public JwtProvider(UserService userService) {
        this.userService = userService;
    }

    public AuthResponse generateToken(Authentication authentication) {
        Date date = Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant());
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = getRoleNames(userDetails.getAuthorities());
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("roles", roles);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return new AuthResponse(token, userDetails.getUsername());
    }

    public boolean validateToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        return !claimsJws.getBody().getExpiration().before(new Date());

    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = UserDetailsFactory.create(userService.showUserByName(getLoginFromToken(token)));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private List<String> getRoleNames(Collection<? extends GrantedAuthority> userRoles) {
        List<String> roleNames = new ArrayList<>();
        userRoles.forEach(role -> roleNames.add(role.getAuthority()));

        return roleNames;
    }
}
