package com.tickets.GoBus.repository;

import com.tickets.GoBus.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
    List<Schedules> findByRoutes_SourceAndRoutes_DestinationAndTravelDate(String source, String destination, String travelDate);
}