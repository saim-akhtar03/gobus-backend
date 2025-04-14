package com.tickets.GoBus.repository;

import com.tickets.GoBus.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Seats, Long> {}
