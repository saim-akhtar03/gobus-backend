package com.tickets.GoBus.controller;


import com.tickets.GoBus.domain.USER_ROLE;
import com.tickets.GoBus.repository.UserRepository;
import com.tickets.GoBus.request.LoginOtpRequest;
import com.tickets.GoBus.request.LoginRequest;
import com.tickets.GoBus.response.ApiResponse;
import com.tickets.GoBus.response.AuthResponse;
import com.tickets.GoBus.request.SignupRequest;
import com.tickets.GoBus.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {
        String jwt=authService.createUser(req);
        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("Success");
        res.setRole(USER_ROLE.CUSTOMER);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/signup/login-signup-otp")

    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {

        authService.sendLoginOtp(req.getEmail(),req.getRole());
        ApiResponse res=new ApiResponse();

        res.setMessage("otp send successfully");

        return ResponseEntity.ok(res);
    }
    @PostMapping("/signin")

    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {

        AuthResponse authResponse= authService.signin(req);


        return ResponseEntity.ok(authResponse);
    }
}
