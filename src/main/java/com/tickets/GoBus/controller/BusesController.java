package com.tickets.GoBus.controller;

import com.tickets.GoBus.exceptions.BusesException;
import com.tickets.GoBus.model.Buses;
import com.tickets.GoBus.request.CreateBusRequsest;
import com.tickets.GoBus.service.BusesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/buses")
public class BusesController {
    private final BusesService busesService;

    @PostMapping("/addBus")
    public ResponseEntity<Buses> createBus(@RequestHeader("Authorization") String jwt , @RequestBody CreateBusRequsest bus) throws Exception {
        Buses newBus= busesService.createBus(bus,jwt);
        return new ResponseEntity<>(newBus, HttpStatus.CREATED);
    }
    @PatchMapping("/update")
    public ResponseEntity<Buses> updateBus(@RequestBody Buses bus,@RequestParam Long id) throws BusesException {
       Buses updatedBus= busesService.updateBus(bus,id);
       return new ResponseEntity<>(updatedBus,HttpStatus.OK);
    }
    @GetMapping("/getALl")
    public ResponseEntity<List<Buses>> getBusById(){
        List<Buses> buses=busesService.getAllBuses();
        return new ResponseEntity<>(buses,HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@RequestParam Long busId) throws BusesException {
        busesService.deleteBus(busId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("getById")
    public ResponseEntity<Buses> getBusById(@RequestParam Long busId) throws BusesException {
        Buses bus=busesService.findByBusId(busId);
        return new ResponseEntity<>(bus,HttpStatus.OK);
    }

}
