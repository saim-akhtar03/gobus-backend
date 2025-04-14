package com.tickets.GoBus.service;

import com.tickets.GoBus.domain.AccountStatus;
import com.tickets.GoBus.model.BusOperators;

import java.util.List;

public interface BusOperatorsService {
    BusOperators getBusOperatorsProfile(String jwt) throws Exception;
    BusOperators creatBusOperators(BusOperators busOperators) throws Exception;
    BusOperators getBusOperatorsById(Long id) throws Exception;
    List<BusOperators> getBusOperatorsByAccountStatus(AccountStatus accountStatus);
    BusOperators getBusOperatorsByEmail(String email) throws Exception;
    void deleteBusOperators(Long id) throws Exception;
    BusOperators verifyEmail(String email, String otp) throws Exception;
    BusOperators updateBusOperators(Long BusOperatorsId,BusOperators busOperators) throws Exception;
    BusOperators updateBusOperatorsAccountStatus(Long BusOperatorsId,AccountStatus accountStatus) throws Exception;

}
