package com.tickets.GoBus.repository;

import com.tickets.GoBus.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {}
