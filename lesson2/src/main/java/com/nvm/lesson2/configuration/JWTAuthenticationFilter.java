package com.nvm.lesson2.configuration;

import com.nvm.lesson2.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //fetch token form request
        var jwtTokenOptional=getTokenFromRequest(request);

        //validate JWT -> using JWT Utils
        jwtTokenOptional.ifPresent(jwtToken->{
            if(JwtUtils.validateToken(jwtToken)){
                //get user name from token
                var usernameOptional=JwtUtils.getUsernameFromToken(jwtToken);
                usernameOptional.ifPresent(username->{
                    //Fetch user detail with the help of username
                    var userDetails= userDetailsService.loadUserByUsername(username);
                    //Create Authentication Token
                    var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //Set Authentication token to Security Context
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                });
            }
        });
        //Pass request and response to next filter
        filterChain.doFilter(request,response);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request){
        //Extract Authentication Header
        var authHeader= request.getHeader(HttpHeaders.AUTHORIZATION);
        //Bearer <JWT Token>
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            return Optional.of(authHeader.substring(7));
        }
        return Optional.empty();
    }
}
