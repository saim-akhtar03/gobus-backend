package com.tickets.GoBus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seats")
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private String seatNumber;
    private boolean isAvailable=true;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Buses buses;
    private double seatPrice;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedules schedule;
}