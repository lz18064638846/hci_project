package com.example.demo.message.response;

/**
 * JwtResponse Class
 *
 * response when sign in successfully
 *
 * @author Zhe Li
 *
 * @date 2020/05/01
 */
public class JwtResponse {
    private String accessToken;
    private String tokenType;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType == null? "Bearer" : tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public JwtResponse() {
    }
}