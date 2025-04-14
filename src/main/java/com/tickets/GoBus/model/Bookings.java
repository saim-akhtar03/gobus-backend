package com.tickets.GoBus.model;

import com.tickets.GoBus.domain.BOOKING_STATUS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    private LocalDateTime bookingDate;
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    private BOOKING_STATUS status;
    private boolean prioritySeat;
}
