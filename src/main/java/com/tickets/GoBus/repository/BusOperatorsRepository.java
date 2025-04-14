package com.tickets.GoBus.repository;

import com.tickets.GoBus.domain.AccountStatus;
import com.tickets.GoBus.model.BusOperators;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusOperatorsRepository extends JpaRepository<BusOperators, Long> {

    BusOperators findByEmail(String actualUsername);
    List<BusOperators> findByAccountStatus(AccountStatus accountStatus);
}