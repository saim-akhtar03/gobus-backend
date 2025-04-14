package com.tickets.GoBus.model;

import com.tickets.GoBus.domain.STATUS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "priority_seats")
public class PrioritySeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priorityId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    private String seatNumber;
    private LocalDateTime requestTime;
    @Enumerated(EnumType.STRING)
    private STATUS status;
}
