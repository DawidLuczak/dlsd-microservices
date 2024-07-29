package pl.dlsd.profile.system.account.service.domain;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.dlsd.profile.system.common.jwt.JwtUtil;

import java.util.*;

@Getter
@Slf4j
@Service
public class JwtService extends JwtUtil {
    public static final String AUTH_HEADER = "Authorization";
    public static final long EXPIRE_ACCESS_TOKEN = 120000; // 2 min
    public static final long EXPIRE_REFRESH_TOKEN = 300000; // 5 min
    public static final String PREFIX = "Bearer ";
    public static final String REFRESH_TOKEN_ROUTE = "/refreshToken";
    public static final String USER_NAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";

    @Value("${security.jwt.expiration-time}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("Roles", userPrincipal.getAuthorities());
        return Jwts.builder()
                .addClaims(claims)
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getJwtSecret() {
        return super.getJwtSecret();
    }
}
