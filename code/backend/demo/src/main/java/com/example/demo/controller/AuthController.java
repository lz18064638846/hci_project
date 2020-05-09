package com.example.demo.controller;

import com.example.demo.message.request.LoginForm;
import com.example.demo.message.request.SignUpForm;
import com.example.demo.message.response.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.services.UserPrinciple;
import com.example.demo.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * AuthController Class
 *
 * log in and sign up controller
 *
 * need refraction
 *
 * @author Zhe Li
 *
 * @date 2020/05/01
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api(tags = "与用户权限有关的控制类")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value = "用户登录", notes = "用户提供用户名和密码用于登录")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Error -> Unauthorized"),
            @ApiResponse(code = 200, message = "sign in successfully")
    })
    @ResponseBody
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        return ResponseEntity.ok(authService.userSignIn(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @ApiOperation(value = "用户注册",notes = "用户填写个人信息注册")
    @ApiResponses({
            @ApiResponse(code = 200, message = "sign up successfully"),
            @ApiResponse(code = 400, message = "sign up unsuccessfully," +
                    "mainly because of existing same username/email/tel,or invalid param"),
    })
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        String response = authService.userSignUp(signUpRequest);
        if(response.contains("Fail")) {
            return ResponseEntity.badRequest().body(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }
}