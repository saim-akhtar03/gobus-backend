package com.tickets.GoBus.service.impl;

import com.tickets.GoBus.config.JwtProvider;
import com.tickets.GoBus.domain.AccountStatus;
import com.tickets.GoBus.domain.USER_ROLE;
import com.tickets.GoBus.model.BusOperators;
import com.tickets.GoBus.repository.BusOperatorsRepository;
import com.tickets.GoBus.service.BusOperatorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BusOperatorsServiceImpl implements BusOperatorsService {
    private final JwtProvider jwtProvider;
    private final BusOperatorsRepository busOperatorsRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public BusOperators getBusOperatorsProfile(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        return  this.getBusOperatorsByEmail(email);

    }

    @Override
    public BusOperators creatBusOperators(BusOperators busOperators) throws Exception {
        BusOperators busOperatorsExist=busOperatorsRepository.findByEmail(busOperators.getEmail());
        if(busOperatorsExist!=null){
            throw new Exception("seller already exists, use differnt email");
        }

        BusOperators newbusOperators=new BusOperators();
        newbusOperators.setOperatorName(busOperators.getOperatorName());
        newbusOperators.setContactNumber(busOperators.getContactNumber());
        newbusOperators.setEmail(busOperators.getEmail());
        newbusOperators.setContactNumber(newbusOperators.getContactNumber());
        newbusOperators.setPassword(passwordEncoder.encode(busOperators.getPassword()));
        newbusOperators.setRole(USER_ROLE.BUS_OPERATOR);
        newbusOperators.setBankDetails(busOperators.getBankDetails());
        newbusOperators.setBuisnessDetails(busOperators.getBuisnessDetails());


        return busOperatorsRepository.save(newbusOperators);
    }

    @Override
    public BusOperators getBusOperatorsById(Long id) throws Exception {

        return busOperatorsRepository.findById(id).orElseThrow(()->new Exception("bus operator not found with id- "+id));
    }

    @Override
    public List<BusOperators> getBusOperatorsByAccountStatus(AccountStatus accountStatus) {
        return busOperatorsRepository.findByAccountStatus(accountStatus);
    }

    @Override
    public BusOperators getBusOperatorsByEmail(String email) throws Exception {
        return busOperatorsRepository.findByEmail(email);
    }

    @Override
    public void deleteBusOperators(Long id) throws Exception {
    busOperatorsRepository.delete(this.getBusOperatorsById(id));
    }

    @Override
    public BusOperators verifyEmail(String email, String otp) throws Exception {
        BusOperators busOperators=getBusOperatorsByEmail(email);
        busOperators.setEmailVerified(true);
        return busOperatorsRepository.save(busOperators);
    }

    @Override
    public BusOperators updateBusOperators(Long busOperatorsId, BusOperators busOperators) throws Exception {
        BusOperators existingBusOperators=this.getBusOperatorsById(busOperatorsId);

        if(busOperators.getEmail()!=null){
            existingBusOperators.setEmail(busOperators.getEmail());

        }
        if(busOperators.getContactNumber()!=null){
            existingBusOperators.setContactNumber(busOperators.getContactNumber());

        }
        if(busOperators.getOperatorName()!=null){
            existingBusOperators.setOperatorName(busOperators.getOperatorName());

        }
        if(busOperators.getBuisnessDetails()!=null&&busOperators.getBuisnessDetails().getBuisnessName()!=null){
            existingBusOperators.getBuisnessDetails().setBuisnessName(busOperators.getBuisnessDetails().getBuisnessName());

        }
        if(busOperators.getBankDetails()!=null
                &&busOperators.getBankDetails().getAccountNumber()!=null
                &&busOperators.getBankDetails().getIfscCode()!=null
                &&busOperators.getBankDetails().getAccountHolderName()!=null){


            existingBusOperators.getBankDetails().setAccountNumber(busOperators.getBankDetails().getAccountNumber());
            existingBusOperators.getBankDetails().setIfscCode(busOperators.getBankDetails().getIfscCode());
            existingBusOperators.getBankDetails().setAccountHolderName(busOperators.getBankDetails().getAccountHolderName());

        }


        return busOperatorsRepository.save(existingBusOperators);
    }

    @Override
    public BusOperators updateBusOperatorsAccountStatus(Long BusOperatorsId, AccountStatus accountStatus) throws Exception {
        return null;
    }
}
