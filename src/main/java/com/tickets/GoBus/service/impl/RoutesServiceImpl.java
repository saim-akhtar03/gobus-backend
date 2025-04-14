package com.tickets.GoBus.service.impl;

import com.tickets.GoBus.model.Buses;
import com.tickets.GoBus.model.Routes;
import com.tickets.GoBus.repository.BusesRepository;
import com.tickets.GoBus.repository.RoutesRepository;
import com.tickets.GoBus.request.CreateRouteRequest;
import com.tickets.GoBus.service.RoutesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
@AllArgsConstructor
@Service
public class RoutesServiceImpl implements RoutesService {
    private final BusesRepository busRepository;
    private final RoutesRepository routeRepository;
    @Override
    public Routes createRoute(CreateRouteRequest req) {
        Buses bus = busRepository.findByBusNumber(req.getBusNumber());
        Routes route = new Routes();
        route.setSource(req.getSource());
        route.setDestination(req.getDestination());
        route.setDistance(req.getDistance());
        route.setRouteName(req.getRouteName());
        return routeRepository.save(route);

    }

    @Override
    public Routes updateRoute(Routes route, Long routeId) {
        Routes existingRoute=routeRepository.getRoutesById(routeId);
        if(route.getDestination()!=null){
            existingRoute.setDestination(route.getDestination());
        }
        if(route.getRouteName()!=null){
            existingRoute.setRouteName(route.getRouteName());
        }
        if(route.getSource()!=null){
            existingRoute.setSource(route.getSource());
        }
        if(route.getDistance()!=0){
            existingRoute.setDistance(route.getDistance());
        }
    routeRepository.save(existingRoute);
        return existingRoute;
    }

    @Override
    public void deleteRoute(Long routeId) {
        Routes route=routeRepository.getRoutesById(routeId);
        routeRepository.delete(route);

    }

    @Override
    public List<Routes> getAllRoute() {
        return routeRepository.findAll();
    }

    @Override
    public List<Routes> searchRoutes(String source, String destination) throws Exception {
        List<Routes> routes=routeRepository.findRoutesBySourceAndDestination(source,destination);
        if(routes==null){
            throw new Exception("routs not found");
        }
        return routes;
    }
}
