package com.tickets.GoBus.controller;

import com.tickets.GoBus.config.JwtProvider;
import com.tickets.GoBus.domain.AccountStatus;
import com.tickets.GoBus.model.BusOperators;
import com.tickets.GoBus.model.VerificationCode;
import com.tickets.GoBus.repository.VerificationCodeRepository;
import com.tickets.GoBus.request.LoginRequest;
import com.tickets.GoBus.response.AuthResponse;
import com.tickets.GoBus.service.AuthService;
import com.tickets.GoBus.service.BusOperatorsService;
import com.tickets.GoBus.service.EmailService;
import com.tickets.GoBus.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/busOperator")
public class BusOperatorsController {

    private final AuthService authService;
    private final BusOperatorsService busOperatorsService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginBusOperators(@RequestBody LoginRequest req) throws Exception {
        String otp= req.getOtp();
        String email= req.getEmail();

//        VerificationCode verificationCode=verificationCodeRepository.findByEmail(email);
//        if(verificationCode==null||!verificationCode.getOtp().equals(req.getOtp())){
//            throw new Exception("wrong otp");
//        }
        req.setEmail("busOperators_"+email);
        AuthResponse authResponse=authService.signin(req);
        return ResponseEntity.ok(authResponse);
    }
    @PatchMapping("/verify/{otp}")
    public ResponseEntity<BusOperators> verifyBusOperatorsEmail(
            @PathVariable String otp
    ) throws Exception {
        VerificationCode verificationCode=verificationCodeRepository.findByOtp(otp);

        if (verificationCode==null || !verificationCode.getOtp().equals(otp)){
            throw new Exception("wrong otp...");
        }
        BusOperators busOperators=busOperatorsService.verifyEmail(verificationCode.getEmail(),otp);
        return new ResponseEntity<>(busOperators, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<BusOperators> createBusOperator(@RequestBody BusOperators busOperators) throws Exception {
        BusOperators savedBusOperators= busOperatorsService.creatBusOperators(busOperators);

        String otp= OtpUtil.generateOtp();
        VerificationCode isExist = verificationCodeRepository.findByEmail(busOperators.getEmail());
        if (isExist != null) {
            verificationCodeRepository.delete(isExist);
        }
        VerificationCode verificationCode=new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(busOperators.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject="gubus login/signup otp";
        String text="your login/signup otp is- "+otp;
        emailService.senderVerificationOtpEmail(busOperators.getEmail(), verificationCode.getOtp(), subject,text);
        return new ResponseEntity<>(busOperators,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusOperators> getBusOperators(@PathVariable Long id) throws Exception {
        BusOperators busOperators= busOperatorsService.getBusOperatorsById(id);
        return new ResponseEntity<>(busOperators,HttpStatus.OK);
    }
    @GetMapping("/profile")
    public ResponseEntity<BusOperators> getBusOperatorsByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        BusOperators busOperators=busOperatorsService.getBusOperatorsByEmail(email);
        return new ResponseEntity<>(busOperators,HttpStatus.OK);
    }

    @GetMapping("/get-BusOperators-byAccounstatus")
    public ResponseEntity<List<BusOperators>> getAllBusOperators(
            @RequestParam(required = false) AccountStatus accountStatus
    ){
        List<BusOperators> busOperators=busOperatorsService.getBusOperatorsByAccountStatus(accountStatus);
        return new ResponseEntity<>(busOperators,HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<BusOperators> updateBusOperators(@RequestHeader("Authorization") String jwt,@RequestBody BusOperators busOperators) throws Exception {
        BusOperators busOperatorsProfile=busOperatorsService.getBusOperatorsProfile(jwt);
        BusOperators updateBusOperators=busOperatorsService.updateBusOperators(busOperatorsProfile.getOperatorId(), busOperators);
        return new ResponseEntity<>(updateBusOperators,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusOperatorsById(@PathVariable Long id) throws Exception {
        busOperatorsService.deleteBusOperators(id);
        return ResponseEntity.noContent().build();
    }
}
