package com.nvm.lesson2.service.serviceImpl;

import com.nvm.lesson2.model.AppUser;
import com.nvm.lesson2.repository.AppUserRepo;
import com.nvm.lesson2.service.AuthService;
import com.nvm.lesson2.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final AppUserRepo appUserRepo;


    @Override
    public String login(String username, String password) {
        try{
            Authentication authToken=new UsernamePasswordAuthenticationToken(username,password); //trả ra 1 đối tượng authentication dựa trên username and password
            Authentication authenticate = authenticationManager.authenticate(authToken); //xác thực dựa trên đối tượng UsernamePasswordAuthenticationToken và nó chạy tới phương thức loadUserByUsername của UserDetailService
            return JwtUtils.generateToken(((UserDetails) (authenticate.getPrincipal())).getUsername());
        }catch (Exception e){
            return "Username or Password was wrong!";
        }
    }

    @Override
    public String signUp(String name, String username, String password) {
        //Check user already exists or not
        if(appUserRepo.existsByUserName(username)!=null){
            throw new RuntimeException("User already exists!");
        }
        //Encode Password
        var encoderPassword=passwordEncoder.encode(password);
        //Authorities
        var authorities= new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //CreateApp User không sử dụng từ khóa new
        var appUser= AppUser.builder()
                .name(name)
                .userName(username)
                .passWord(encoderPassword)
                .authorities(authorities)
                .build();
        //save user
        appUserRepo.save(appUser);
        //GenerateToken
        return JwtUtils.generateToken(username);
    }
}
