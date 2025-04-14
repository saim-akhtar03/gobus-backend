package com.tickets.GoBus.controller;

import com.tickets.GoBus.model.Schedules;
import com.tickets.GoBus.request.CreateScheduleRequest;
import com.tickets.GoBus.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody CreateScheduleRequest request) {
        try {
            Schedules createdSchedule = scheduleService.createSchedule(request);
            return ResponseEntity.ok(createdSchedule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Schedules>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedules> getScheduleById(@PathVariable Long id) {
        Schedules schedule = scheduleService.getScheduleById(id);
        if (schedule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok("Schedule deleted successfully.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Schedules>> getSchedulesByRouteAndDate(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam String travelDate
    ) {
        List<Schedules> schedules = scheduleService.getSchedulesByRouteAndDate(source, destination, travelDate);
        return ResponseEntity.ok(schedules);
    }
}

