package com.tickets.GoBus.service;

import com.tickets.GoBus.domain.USER_ROLE;
import com.tickets.GoBus.request.LoginRequest;
import com.tickets.GoBus.response.AuthResponse;
import com.tickets.GoBus.request.SignupRequest;

public interface AuthService {

    void sendLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse signin(LoginRequest req);
}
