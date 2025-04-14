package com.tickets.GoBus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedules")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Routes routes;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Buses buses;
    private String duration;
    private LocalDateTime departureDatetime;
    private LocalDateTime arrivalDatetime;
    private String travelDate;
}
