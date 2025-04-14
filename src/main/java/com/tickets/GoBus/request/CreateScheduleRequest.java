package com.tickets.GoBus.request;

import com.tickets.GoBus.model.Buses;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateScheduleRequest {
    private String busNumber;
    private LocalDateTime departureDatetime;
    private LocalDateTime arrivalDatetime;
    private String travelDate;
    private Long routeId;
    private double seatPrice;
}
