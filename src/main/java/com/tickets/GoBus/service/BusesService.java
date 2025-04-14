package com.tickets.GoBus.service;

import com.tickets.GoBus.exceptions.BusesException;
import com.tickets.GoBus.model.Buses;
import com.tickets.GoBus.request.CreateBusRequsest;

import java.util.List;


public interface BusesService  {
    Buses createBus(CreateBusRequsest req, String jwt) throws Exception;
    void deleteBus(Long id) throws BusesException;
    Buses updateBus(Buses bus,Long busId) throws BusesException;
    List<Buses> getAllBuses();
    Buses  findByBusId(Long id) throws BusesException;
}
