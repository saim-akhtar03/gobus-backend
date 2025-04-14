package com.tickets.GoBus.service.impl;

import com.tickets.GoBus.model.Buses;
import com.tickets.GoBus.model.Routes;
import com.tickets.GoBus.model.Schedules;
import com.tickets.GoBus.model.Seats;
import com.tickets.GoBus.repository.BusesRepository;
import com.tickets.GoBus.repository.RoutesRepository;
import com.tickets.GoBus.repository.SchedulesRepository;
import com.tickets.GoBus.repository.SeatsRepository;
import com.tickets.GoBus.request.CreateScheduleRequest;
import com.tickets.GoBus.service.BusesService;
import com.tickets.GoBus.service.RoutesService;
import com.tickets.GoBus.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final SchedulesRepository schedulesRepository;
    private final RoutesRepository routesRepository;
    private final BusesRepository busesRepository;
    private final SeatsRepository seatsRepository;
    @Override
    public Schedules createSchedule(CreateScheduleRequest req) throws Exception {
        Routes routes =routesRepository.getRoutesById(req.getRouteId());
        if(routes==null){
            throw new Exception("route not found with id-"+req.getRouteId());
        }
        Buses bus=busesRepository.findByBusNumber(req.getBusNumber());
        if(bus==null){
            throw new Exception("bus not found with bus number-"+req.getBusNumber());
        }
        String duration=this.calculateDuration(req.getDepartureDatetime(),req.getArrivalDatetime());

        Schedules schedules=new Schedules();
        schedules.setArrivalDatetime(req.getArrivalDatetime());
        schedules.setDepartureDatetime(req.getDepartureDatetime());
        schedules.setRoutes(routes);
        schedules.setTravelDate(req.getTravelDate());
        schedules.setDuration(duration);
        schedules.setBuses(bus);

        Schedules schedules1=schedulesRepository.save(schedules);

        List<Seats> seats = new ArrayList<>();

        for (int i = 1; i <= bus.getCapacity(); i++) {
            Seats seat = new Seats();
            seat.setSeatNumber("S" + i);
            seat.setAvailable(true);
            seat.setSchedule(schedules);
            seat.setBuses(bus); // optional
            seat.setSeatPrice(req.getSeatPrice());
            seats.add(seat);
        }

        seatsRepository.saveAll(seats);
        return schedules1;

    }

    @Override
    public List<Schedules> getAllSchedules() {
        return schedulesRepository.findAll();
    }

    @Override
    public Schedules getScheduleById(Long id) {
        return schedulesRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSchedule(Long id) {
        schedulesRepository.deleteById(id);
    }

    @Override
    public List<Schedules> getSchedulesByRouteAndDate(String source, String destination, String travelDate) {
        return schedulesRepository.findByRoutes_SourceAndRoutes_DestinationAndTravelDate(source, destination, travelDate);
    }
    public  String calculateDuration(LocalDateTime departure, LocalDateTime arrival) {
        if (departure == null || arrival == null) return "00:00";

        Duration duration = Duration.between(departure, arrival);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        return String.format("%02d:%02d", hours, minutes);
    }
}
