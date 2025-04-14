package com.tickets.GoBus.repository;

import com.tickets.GoBus.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoutesRepository extends JpaRepository<Routes, Long> {
    @Query("SELECT r FROM Routes r WHERE LOWER(r.source) = LOWER(:source) AND LOWER(r.destination) = LOWER(:destination)")
    List<Routes> findRoutesBySourceAndDestination(@Param("source") String source, @Param("destination") String destination);
    Routes getRoutesById(Long id);


}
