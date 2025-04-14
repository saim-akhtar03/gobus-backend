package com.tickets.GoBus.model;

import com.tickets.GoBus.domain.STATUS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "waiting_list")
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waitId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    private LocalDateTime requestTime;
    @Enumerated(EnumType.STRING)
    private STATUS status;
    private int priorityLevel;
}