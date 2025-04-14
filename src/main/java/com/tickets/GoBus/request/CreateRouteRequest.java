package com.tickets.GoBus.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateRouteRequest {
    private String source;
    private String destination;
    private double distance;
    private String routeName;
    private String busNumber;
}
