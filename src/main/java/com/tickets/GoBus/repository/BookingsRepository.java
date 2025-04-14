package com.tickets.GoBus.repository;

import com.tickets.GoBus.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {}
