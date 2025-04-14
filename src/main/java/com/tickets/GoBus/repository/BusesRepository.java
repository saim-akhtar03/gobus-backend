package com.tickets.GoBus.repository;

import com.tickets.GoBus.domain.AccountStatus;
import com.tickets.GoBus.model.BusOperators;
import com.tickets.GoBus.model.Buses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BusesRepository extends JpaRepository<Buses, Long> {
Buses findByBusId(Long id);
Buses findByBusNumber(String busNumber);
}


