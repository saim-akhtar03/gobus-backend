package com.tickets.GoBus.service.impl;

import com.tickets.GoBus.config.JwtProvider;
import com.tickets.GoBus.domain.USER_ROLE;
import com.tickets.GoBus.model.BusOperators;
import com.tickets.GoBus.model.User;
import com.tickets.GoBus.model.VerificationCode;
import com.tickets.GoBus.repository.BusOperatorsRepository;
import com.tickets.GoBus.repository.UserRepository;
import com.tickets.GoBus.repository.VerificationCodeRepository;
import com.tickets.GoBus.request.LoginRequest;
import com.tickets.GoBus.response.AuthResponse;
import com.tickets.GoBus.request.SignupRequest;
import com.tickets.GoBus.service.AuthService;
import com.tickets.GoBus.service.EmailService;
import com.tickets.GoBus.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserService;
    private final BusOperatorsRepository busOperatorsRepository;

    @Override
public void sendLoginOtp(String email,USER_ROLE role) throws Exception {
    String SIGNING_PRIFIX ="signin_";

    if(email.startsWith(SIGNING_PRIFIX)) {
        email=email.substring(SIGNING_PRIFIX.length());
        if(role==USER_ROLE.CUSTOMER) {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new Exception("user does not exist with this email");
            }
        }
        else{
            BusOperators busOperators= busOperatorsRepository.findByEmail(email);
            if (busOperators == null) {
                throw new Exception("user does not exist with this email");
            }
        }
    }
    VerificationCode isExist = verificationCodeRepository.findByEmail(email);
    if (isExist != null) {
        verificationCodeRepository.delete(isExist);
    }
    String otp= OtpUtil.generateOtp();

    VerificationCode verificationCode=new VerificationCode();
    verificationCode.setOtp(otp);
    verificationCode.setEmail(email);
    verificationCodeRepository.save(verificationCode);

    String subject="GoBus signin/login otp";
    String text ="your login/sign otp is- "+otp;
    emailService.senderVerificationOtpEmail(email,otp,subject,text);


}


    @Override
public String createUser(SignupRequest req) throws Exception {

    VerificationCode verificationCode=verificationCodeRepository.findByEmail(req.getEmail());

    if (verificationCode==null || !verificationCode.getOtp().equals(req.getOtp())){
        throw new Exception("wrong otp...");
    }
    User user=userRepository.findByEmail(req.getEmail());
    if(user==null){
        User createdUser=new User();
        createdUser.setEmail(req.getEmail());
        createdUser.setFullName(req.getFullName());
        createdUser.setRole(USER_ROLE.CUSTOMER);
        createdUser.setMobile("9584200321");
        createdUser.setPassword(passwordEncoder.encode(req.getOtp()));

        user=userRepository.save(createdUser);

    }
    List<GrantedAuthority> authorities=new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(USER_ROLE.CUSTOMER.toString()));

    Authentication authentication=new UsernamePasswordAuthenticationToken(req.getEmail(),authorities);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return jwtProvider.generateToken(authentication);
}

@Override
public AuthResponse signin(LoginRequest req) {

    AuthResponse authResponse =new AuthResponse();

    String username=req.getEmail();
    String otp=req.getOtp();


    Authentication authentication=authenticate(username,otp);


    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token=jwtProvider.generateToken(authentication);

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    String roleName=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

    authResponse.setJwt(token);
    authResponse.setMessage("login success");
    authResponse.setRole(USER_ROLE.valueOf(roleName));
    return authResponse;
}

private Authentication authenticate(String username, String otp) {
    UserDetails userDetails=customUserService.loadUserByUsername(username);
    String BUSOPERATORS_PREFIX="busOperators_";

    if (username.startsWith(BUSOPERATORS_PREFIX)) {
        username = username.substring(BUSOPERATORS_PREFIX.length());

    }
    if(userDetails==null){
        throw new BadCredentialsException("Bad Credentials");
    }
    VerificationCode verificationCode=verificationCodeRepository.findByEmail(username);
    if(verificationCode==null|| !verificationCode.getOtp().equals(otp)){
        throw new BadCredentialsException("Bad Credentials");
    }
    return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities() );
}

}
