package com.example.demo.service;

import javax.servlet.http.HttpSession;
import com.example.demo.message.request.SignUpForm;
import com.example.demo.message.response.JwtResponse;

/**
 * @author Zhe Li
 * @date 2020/05/09
 */
public interface AuthService {

    /**
     * this service allow user to login
     *
     * @param username input username
     * @param password input password
     * @return jwt response for specific user
     */
    JwtResponse userSignIn(String username, String password);

    /**
     * this service allow user to register
     *
     * @param form register form
     * @return a http response entity
     */
    String userSignUp(SignUpForm form);

}
