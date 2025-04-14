package com.tickets.GoBus.service;

import com.tickets.GoBus.model.Schedules;
import com.tickets.GoBus.request.CreateScheduleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ScheduleService {
    Schedules createSchedule(CreateScheduleRequest req) throws Exception;
    List<Schedules> getAllSchedules();
    Schedules getScheduleById(Long id);
    void deleteSchedule(Long id);
    List<Schedules> getSchedulesByRouteAndDate(String source, String destination, String travelDate);
}
