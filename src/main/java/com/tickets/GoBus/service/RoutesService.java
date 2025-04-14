package com.tickets.GoBus.service;

import com.tickets.GoBus.model.Routes;
import com.tickets.GoBus.request.CreateRouteRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoutesService {
    Routes createRoute(CreateRouteRequest req);
    Routes updateRoute(Routes route,Long routeId);
    void deleteRoute(Long routeId);
    List<Routes> getAllRoute();
    List<Routes> searchRoutes(String source,String destination) throws Exception;
}
