package com.tickets.GoBus.controller;

import com.tickets.GoBus.model.Routes;
import com.tickets.GoBus.request.CreateRouteRequest;
import com.tickets.GoBus.request.SearchRoutesRequest;
import com.tickets.GoBus.service.RoutesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
@AllArgsConstructor
public class RoutesController {

    private final RoutesService routesService;

    @PostMapping("/add")
    public ResponseEntity<Routes> addRoute(@RequestBody CreateRouteRequest request) {
        Routes createdRoute = routesService.createRoute(request);
        return ResponseEntity.ok(createdRoute);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Routes> updateRoute(@RequestBody Routes route, @PathVariable Long id) {
        Routes updated = routesService.updateRoute(route, id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routesService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Routes>> getAllRoutes() {
        List<Routes> routes = routesService.getAllRoute();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Routes>> searchRoutes(
            @RequestBody SearchRoutesRequest req
            ) throws Exception {
        List<Routes> result = routesService.searchRoutes(req.getSource(), req.getDestination());
        return ResponseEntity.ok(result);
    }
}