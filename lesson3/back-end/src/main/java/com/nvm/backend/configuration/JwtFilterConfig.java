package com.nvm.backend.configuration;

import com.nvm.backend.service.Impl.UserDetailServiceImpl;
import com.nvm.backend.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilterConfig extends OncePerRequestFilter {

    private final UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var jwtTokenOptional=getTokenFromRequest(request);
        //validate JWT -> using JWT Utils
        jwtTokenOptional.ifPresent(jwtToken->{
            if(JwtUtils.validateToken(jwtToken)){
                //get user name from token
                var usernameOptional=JwtUtils.getUsernameFromToken(jwtToken);
                usernameOptional.ifPresent(username->{
                    //Fetch user detail with the help of username
                    UserDetails userDetails= userDetailService.loadUserByUsername(username);
                    //Create Authentication Token
                    var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()); // tạo 1 người dùng gồm tài khoản mật khẩu và quyền của người dùng đó.
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //thiết lập chi tiết của yêu cầu HTTP,như địa chỉ IP và thông tin trình duyệt,buildDetails sẽ tạo ra các chi tiết dựa trên đối tượng request được truyền vào
                    //Set Authentication token to Security Context
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken); // lưu trữ người dùng đã được xác thực
                });
            }
        });
        //Pass request and response to next filter
        filterChain.doFilter(request,response); //chạy xác thực các filter chain đã được định nghĩa ở lớp SecurityFilterChainConfig
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
