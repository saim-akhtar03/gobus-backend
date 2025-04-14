package com.tickets.GoBus.repository;

import com.tickets.GoBus.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {

        VerificationCode findByEmail(String email);
        VerificationCode findByOtp(String otp);
    }


