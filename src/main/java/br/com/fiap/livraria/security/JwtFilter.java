package br.com.fiap.livraria.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailService jwtUserDetailService;

    public JwtFilter(JwtUserDetailService jwtUserDetailService, JwtTokenUtil jwtTokenUtil){
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailService = jwtUserDetailService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String httpHeaderAuthorization = HttpHeaders.AUTHORIZATION;
        final String authorizationHeaderToken = request.getHeader(httpHeaderAuthorization);

        String username = null;

        if (authorizationHeaderToken != null && authorizationHeaderToken.startsWith("Bearer ")){
            try {
                username = jwtTokenUtil.getUsernameFromToken(authorizationHeaderToken);
            } catch (IllegalArgumentException illegal) {
                logger.info(illegal.getMessage());
            } catch (ExpiredJwtException expired) {
                logger.info(expired.getMessage());
            }
        } else {
            logger.warn(String.format("Token null ou fora do padrao Bearer. httpAutorization: '%s' | Token: '%s'", httpHeaderAuthorization, authorizationHeaderToken));
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }

    }
}
