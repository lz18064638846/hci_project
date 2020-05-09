package com.example.demo.serviceimpl;

import javax.servlet.http.HttpSession;
import com.example.demo.message.request.SignUpForm;
import com.example.demo.message.response.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.services.UserPrinciple;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author Zhe Li
 * @date 2020/05/09
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public JwtResponse userSignIn(String username, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken((UserPrinciple) authentication.getPrincipal());
        return new JwtResponse(jwt);
    }

    @Override
    public String userSignUp(SignUpForm form) {
        if(userRepository.existsByUsername(form.getUsername())) {
            return "Fail -> Username is already taken";
        }

        User user = new User(form.getUsername(),
                encoder.encode(form.getPassword()),
                form.getEmail(), Long.parseLong(form.getTel()),
                form.getRealName(), form.getSex());

        userRepository.save(user);
        return "User registered successfully!";
    }
}
